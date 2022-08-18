package mx.com.pandadevs.pibeapi.models.profile;
// Java
// Spring
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUsernameAndUserActiveTrue(String username);

    @Modifying
    @Query(value = "UPDATE profiles t SET t.user_id = ? WHERE t.id_profile = ?; "
            ,nativeQuery = true)
    void updateResume(Long userId,Long profileId);
}
