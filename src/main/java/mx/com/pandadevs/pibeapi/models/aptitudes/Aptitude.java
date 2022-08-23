package mx.com.pandadevs.pibeapi.models.aptitudes;
// Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Persistence
import javax.persistence.*;

import mx.com.pandadevs.pibeapi.models.resumes.Resume;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "APTITUDES")
public class Aptitude extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aptitudes")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;


    @Column(
            columnDefinition = "tinyint default 1")
    private Boolean active;

    // Relationships

    // VACANTS FAVORITES
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "aptitudes")
    private List<Resume> resumes  = new ArrayList<>();


    // Getters & Setters

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

}
