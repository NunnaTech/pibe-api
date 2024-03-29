package mx.com.pandadevs.pibeapi.models.schedule;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;

@Entity
@Table(name = "SCHEDULE")
public class Schedule extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    @Column(
            columnDefinition = "tinyint default 1")
    private Boolean active = true;
    
    @JsonIgnore
    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.ALL})
    private Set<Vacant> vacants;

    public Schedule() {
    }

    public Schedule(String name) {
        this.name = name;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vacant> getVacants() {
        return vacants;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setVacants(Set<Vacant> vacants) {
        this.vacants = vacants;
    }

    
}
