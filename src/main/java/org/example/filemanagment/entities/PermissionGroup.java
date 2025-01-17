package org.example.filemanagment.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

//    @OneToMany(mappedBy = "permissionGroup")
//    private List<schema.graphqls> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
