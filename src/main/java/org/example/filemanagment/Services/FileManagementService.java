package org.example.filemanagment.Services;

import org.example.filemanagment.entities.FileEntity;
import org.example.filemanagment.entities.Item;
import org.example.filemanagment.entities.PermissionGroup;
import org.example.filemanagment.repositories.FileRepository;
import org.example.filemanagment.repositories.ItemRepository;
import org.example.filemanagment.repositories.PermissionGroupRepository;
import org.example.filemanagment.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Item createSpace(String name, PermissionGroup group) {
        Item space = new Item();
        space.setType("Space");
        space.setName(name);
        space.setPermissionGroup(group);
        return itemRepository.save(space);
    }

    public Item createFolder(String parentName, String folderName, PermissionGroup group) {
        // Implement logic to find the parent space and validate permissions
        Item folder = new Item();
        folder.setType("Folder");
        folder.setName(folderName);
        folder.setPermissionGroup(group);
        return itemRepository.save(folder);
    }

    public FileEntity createFile(String folderName, String fileName, byte[] content, PermissionGroup group) {
        // Implement logic to find the folder and validate permissions
        Item fileItem = new Item();
        fileItem.setType("File");
        fileItem.setName(fileName);
        fileItem.setPermissionGroup(group);
        itemRepository.save(fileItem);

        FileEntity file = new FileEntity();
        file.setItem(fileItem);
        file.setBinary(content);
        return fileRepository.save(file);
    }
}
