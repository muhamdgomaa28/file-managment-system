package org.example.filemanagment.entities;

import jakarta.persistence.*;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] binary;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
