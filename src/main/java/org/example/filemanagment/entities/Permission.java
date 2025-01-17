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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    public void setGroup(PermissionGroup group) {
        this.group = group;
    }
}
