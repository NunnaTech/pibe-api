package mx.com.pandadevs.pibeapi.models.users;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Validations
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Lombok
import lombok.Getter;
import lombok.Setter;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "USERS")
@Setter
@Getter
public class User extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(50)")
    private String email;

    @NotNull
    @NotBlank
    @Size(
        min = 5,
        max = 100)
    @Column(
        nullable = false,
        columnDefinition = "varchar(100)")
    private String password;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @Column(
        name = "link_restore_password",
        unique = true, columnDefinition = "varchar(150)")
    private String linkRestorePassword;

    @Column(
        name = "link_activate_email",
        unique = true, columnDefinition = "varchar(150)")
    private String linkActivateEmail;

}
