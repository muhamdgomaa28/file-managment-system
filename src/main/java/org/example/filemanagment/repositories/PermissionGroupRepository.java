package org.example.filemanagment.repositories;

import org.example.filemanagment.entities.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    PermissionGroup findByGroupName(String name);
}
