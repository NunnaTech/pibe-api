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

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.utils.enums.Gender;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.states.RepublicState;
import mx.com.pandadevs.pibeapi.models.users.User;

@Entity
@Table(name = "PROFIELS")
@Setter
@Getter
public class Profile extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Integer id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    
    @Column(
        name = "first_name",
        nullable = false,
        columnDefinition = "varchar(40)")
    private String fisrtName;

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
    private String imagen;

    @Column(
        name = "phone_number",
        columnDefinition = "varchar(200)")
    private String phoneNumber;

    @Column(
        name="gender",
        nullable = false,
        length = 6 
        )
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(
        nullable = false,
        columnDefinition = "varchar(70)")
    private String position;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private RepublicState state ;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    // Relationships
    
    // Resume
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.ALL})
    private Set<Resume> resumes;
    
}
