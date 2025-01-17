package org.example.filemanagment.services;

import org.example.filemanagment.entities.FileEntity;
import org.example.filemanagment.entities.Item;
import org.example.filemanagment.entities.PermissionGroup;
import org.example.filemanagment.enums.ItemType;
import org.example.filemanagment.enums.PermissionLevel;
import org.example.filemanagment.repositories.FileRepository;
import org.example.filemanagment.repositories.ItemRepository;
import org.example.filemanagment.repositories.PermissionGroupRepository;
import org.example.filemanagment.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileManagementService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    public Item createSpace(String name, String group, String userEmail) {

        validateUserPermissions(group, userEmail, List.of(PermissionLevel.VIEW.name(), PermissionLevel.EDIT.name()));

        PermissionGroup permissionGroup = permissionGroupRepository.findByGroupName(group);
        Item space = new Item();
        space.setType(ItemType.SPACE.name());
        space.setName(name);
        space.setPermissionGroup(permissionGroup);
        return itemRepository.save(space);
    }

    private void validateUserPermissions(String group, String userEmail, List<String> types) {
        boolean hasAccess = permissionRepository.existsByGroupIdAndUserEmailAndPermissionLevel(
                group,
                userEmail,
                types
        );

        if (!hasAccess) {
            throw new RuntimeException("User does not have permission to access this file.");
        }
    }

    public Item createFolder(String parentName, String folderName, String group, String userEmail) {
        validateUserPermissions(group, userEmail, List.of(PermissionLevel.EDIT.name()));

        PermissionGroup permissionGroup = permissionGroupRepository.findByGroupName(group);

        Item folder = new Item();
        folder.setType(ItemType.FOLDER.name());
        folder.setName(folderName);
        folder.setPermissionGroup(permissionGroup);
        folder.setParentName(parentName);
        return itemRepository.save(folder);
    }

    public FileEntity createFile(String folderName, String fileName, byte[] content, String group, String userEmail) {
        validateUserPermissions(group, userEmail, List.of(PermissionLevel.EDIT.name()));

        PermissionGroup permissionGroup = permissionGroupRepository.findByGroupName(group);


        Item fileItem = new Item();
        fileItem.setType(ItemType.FILE.name());
        fileItem.setName(fileName);
        fileItem.setParentName(folderName);
        fileItem.setPermissionGroup(permissionGroup);
        itemRepository.save(fileItem);

        FileEntity file = new FileEntity();
        file.setItem(fileItem);
        file.setBinary(content);
        return fileRepository.save(file);
    }

    public FileEntity getFile(Long fileId, String userEmail) {
        FileEntity fileEntity = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + fileId));

        validateUserPermissions(fileEntity.getItem().getPermissionGroup().getGroupName(), userEmail, List.of("VIEW"));

        return fileEntity;
    }

    public Item getItemMetadata(Long fileId, String userEmail) {
        return itemRepository.findItemMetadataById(fileId, userEmail)
                .orElseThrow(() -> new RuntimeException("File metadata not found for ID: " + fileId));
    }
}
