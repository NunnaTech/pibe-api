package mx.com.pandadevs.pibeapi.models.resumes;
// Java
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitudes;
import mx.com.pandadevs.pibeapi.models.certifications.Certification;
import mx.com.pandadevs.pibeapi.models.courses.Course;
import mx.com.pandadevs.pibeapi.models.languages.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.studies.Study;
import mx.com.pandadevs.pibeapi.models.styles.Style;
import mx.com.pandadevs.pibeapi.models.work_experiences.WorkExperience;

@Entity
@Table(name = "RESUMES")
@Setter
@Getter
public class Resume extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resume")
    private Integer id;
    
    @Column(
        name = "curricular_title",
        nullable = false,
        columnDefinition = "varchar(80)")
    private String curricularTitle;

    @Column(
        nullable = false,
        columnDefinition = "varchar(255)")
    private String description; 

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean completed;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "style_id", insertable = false, updatable = false)
    private Style style;

    // Relationship

    // RESUME APTITUDES
     @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "RESUME_APTITUDES",
            joinColumns = @JoinColumn(name = "aptitude_id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id"))
    private List<Aptitudes> aptitudes;

    public void addToFavorite(Aptitudes aptitude) {
        aptitudes.add(aptitude);
        aptitude.getResumes().add(this);
    }

    public void removeFromFavorite(Aptitudes aptitude) {
        aptitudes.remove(aptitude);
        aptitude.getResumes().remove(this);
    }

    // Resume Lenguage
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<ResumeLanguage> lenguages;

    // Studies
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Study> studies;

    // Courses
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Course> courses;
    
    // Work Experiences
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<WorkExperience> experiences;

    // Certifications
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Certification> certifications;
}
