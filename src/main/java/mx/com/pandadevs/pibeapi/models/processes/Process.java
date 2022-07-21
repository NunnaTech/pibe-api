package mx.com.pandadevs.pibeapi.models.processes;

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

import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;

@Entity
@Table(name = "PROCESSES")
public class Process extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_process")
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

    @OneToMany(mappedBy = "process", cascade = {CascadeType.ALL})
    private Set<UserVacant> userVacants;

    public Process() {
    }

    public Process(String name) {
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

    public Set<UserVacant> getUserVacants() {
        return userVacants;
    }

    public void setUserVacants(Set<UserVacant> userVacants) {
        this.userVacants = userVacants;
    }
}
