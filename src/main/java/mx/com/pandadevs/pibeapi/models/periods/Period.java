package mx.com.pandadevs.pibeapi.models.periods;
// Java
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;

@Entity
@Table(name = "PERIODS")
@Setter
@Getter
public class Period extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    
    // Relationships
    
    // Vacants
    @OneToMany(mappedBy = "period", cascade = {CascadeType.ALL})
    private Set<Vacant> vacants;
}
