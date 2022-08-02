package mx.com.pandadevs.pibeapi.models.profile;
// Java
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Models
import com.fasterxml.jackson.annotation.*;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.utils.enums.Gender;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.states.RepublicState;
import mx.com.pandadevs.pibeapi.models.users.User;

@Entity
@Table(name = "PROFILES")
public class Profile extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Long id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    
    @Column(
        name = "first_name",
        nullable = false,
        columnDefinition = "varchar(40)")
    private String firstName;

    @Column(
        name = "second_name",
        columnDefinition = "varchar(40)")
    private String secondName;

    @Column(
        name = "birth_date",
        nullable = false,
        columnDefinition = "timestamp")
    private LocalDateTime birthDate;
    
    @Column(
        columnDefinition = "varchar(200)")
    private String image;

    @Column(
        name = "phone_number",
        columnDefinition = "varchar(200)")
    private String phoneNumber;

    @Column(
        name="gender",
        nullable = false,
        length = 6 
        )
    private String gender;

    @Column(
        nullable = false,
        columnDefinition = "varchar(70)")
    private String position;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean completed;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "state_id")
    private RepublicState state ;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    // Relationships
    
    // Resume
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.ALL})
    private Set<Resume> resumes;

    // Getters & Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fisrtName) {
        this.firstName = fisrtName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public RepublicState getState() {
        return state;
    }

    public void setState(RepublicState state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
    }

    
    
}
