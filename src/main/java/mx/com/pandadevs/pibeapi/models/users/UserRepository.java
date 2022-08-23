package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.List;
import java.util.Optional;
// Spring
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndActiveTrue(String username);

    Optional<User> findByEmailAndActiveTrue(String email);

    User findByUsername(String username);

    User findByProfileId(Long id);
    List<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByLinkRestorePasswordAndActiveTrue(String key);
}