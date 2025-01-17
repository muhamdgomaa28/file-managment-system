package org.example.filemanagment.repositories;

import org.example.filemanagment.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Permission p WHERE p.group.groupName = :groupName AND p.userEmail = :userEmail AND p.permissionLevel in :permissionLevel")
    boolean existsByGroupIdAndUserEmailAndPermissionLevel(String groupName, String userEmail, List<String> permissionLevel);
}
