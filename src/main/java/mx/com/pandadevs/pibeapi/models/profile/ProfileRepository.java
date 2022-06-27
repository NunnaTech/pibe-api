package mx.com.pandadevs.pibeapi.models.profile;
// Java
import java.util.Optional;
// Spring
import mx.com.pandadevs.pibeapi.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUsernameAndUserActiveTrue(String username);
}
