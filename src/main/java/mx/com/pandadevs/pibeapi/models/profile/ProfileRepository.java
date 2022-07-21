package mx.com.pandadevs.pibeapi.models.profile;
// Java
// Spring
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUsernameAndUserActiveTrue(String username);
}
