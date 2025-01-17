package org.example.filemanagment.controllers;

import org.example.filemanagment.services.FileManagementService;
import org.example.filemanagment.dtos.CreateFileRequest;
import org.example.filemanagment.dtos.CreateFolderRequest;
import org.example.filemanagment.dtos.CreateSpaceRequest;
import org.example.filemanagment.entities.FileEntity;
import org.example.filemanagment.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FileManagementController {
    @Autowired
    private FileManagementService service;

    @PostMapping("/spaces")
    public ResponseEntity<Item> createSpace(@RequestBody CreateSpaceRequest request) {
        Item space = service.createSpace(request.getSpaceName(), request.getGroupName(), request.getUserEmail());
        return ResponseEntity.ok(space);
    }

    @PostMapping("/folders")
    public ResponseEntity<Item> createFolder(@RequestBody CreateFolderRequest request) {
        Item folder = service.createFolder(request.getParentName(), request.getFolderName(), request.getGroupName(), request.getUserEmail());
        return ResponseEntity.ok(folder);
    }

    @PostMapping("/files")
    public ResponseEntity<FileEntity> createFile(@RequestBody CreateFileRequest request) {
        FileEntity file = service.createFile(request.getFolderName(), request.getFileName(), request.getContent(), request.getGroupName(), request.getUserEmail());
        return ResponseEntity.ok(file);
    }

    @GetMapping("/items/{id}/metadata")
    public ResponseEntity<Item> getItemMetaData(@PathVariable Long id, @RequestParam String userEmail) {
        Item item = service.getItemMetadata(id, userEmail);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id, @RequestParam String userEmail) {
        FileEntity file = service.getFile(id, userEmail);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getItem().getName())
                .body(file.getBinary());
    }
}
