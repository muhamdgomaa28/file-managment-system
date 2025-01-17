import jakarta.persistence.*;
import org.example.filemanagment.entities.Item;

import java.util.List;

@Entity
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @OneToMany(mappedBy = "permissionGroup")
    private List<Item> items;
}
