package org.example.filemanagment.repositories;

import org.example.filemanagment.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(
            value = """
                SELECT i.id, i.type, i.name, i.permission_group_id
                FROM item i
                JOIN permission_group pg ON i.permission_group_id = pg.id
                JOIN permission p ON pg.id = p.group_id
                WHERE i.id = :fileId
                AND p.user_email = :userEmail
                AND p.permission_level = 'VIEW'
                """,
            nativeQuery = true
    )
    Optional<Item> findItemMetadataById(@Param("fileId") Long fileId, @Param("userEmail") String userEmail);
}
