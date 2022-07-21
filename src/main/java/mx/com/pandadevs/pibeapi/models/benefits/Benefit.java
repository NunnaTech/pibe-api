package mx.com.pandadevs.pibeapi.models.benefits;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;

@Entity
@Table(name = "BENEFITS")
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

    @Column(
            nullable = false,
            columnDefinition = "tinyint default 1")
    private Boolean active = true;

    @ManyToMany(mappedBy = "benefits")
    private List<Vacant> vacants = new ArrayList<>();

    public Benefit() {
    }

    public Benefit(String name) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Vacant> getVacants() {
        return vacants;
    }

    public void setVacants(List<Vacant> vacants) {
        this.vacants = vacants;
    }
}
