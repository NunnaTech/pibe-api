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

// Models
import com.fasterxml.jackson.annotation.*;
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
    @JsonIgnore
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<ResumeLanguage> lenguages;

    // Studies
    @JsonIgnore
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Study> studies;

    // Courses
    @JsonIgnore
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Course> courses;
    
    // Work Experiences
    @JsonIgnore
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<WorkExperience> experiences;

    // Certifications
    @JsonIgnore
    @OneToMany(mappedBy = "resume", cascade = {CascadeType.ALL})
    private Set<Certification> certifications;

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurricularTitle() {
        return curricularTitle;
    }

    public void setCurricularTitle(String curricularTitle) {
        this.curricularTitle = curricularTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<Aptitudes> getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(List<Aptitudes> aptitudes) {
        this.aptitudes = aptitudes;
    }

    public Set<ResumeLanguage> getLenguages() {
        return lenguages;
    }

    public void setLenguages(Set<ResumeLanguage> lenguages) {
        this.lenguages = lenguages;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<WorkExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<WorkExperience> experiences) {
        this.experiences = experiences;
    }

    public Set<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<Certification> certifications) {
        this.certifications = certifications;
    }

    
}
