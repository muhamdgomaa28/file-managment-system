package org.example.filemanagment.entities;

import jakarta.persistence.*;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String permissionLevel; // VIEW, EDIT

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup group;
}
