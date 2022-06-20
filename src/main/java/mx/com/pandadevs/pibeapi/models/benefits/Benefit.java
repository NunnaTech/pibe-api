package mx.com.pandadevs.pibeapi.models.benefits;
// Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "BENEFITS")
@Setter
@Getter
public class Benefit extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_benefit")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name; 

     // Relationships
    
    // VACANTS BENEFITS
    @ManyToMany(mappedBy = "benefits")
    private List<Vacant> vacants = new ArrayList<>();

}
