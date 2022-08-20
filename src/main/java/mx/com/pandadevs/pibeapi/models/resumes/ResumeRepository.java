package mx.com.pandadevs.pibeapi.models.resumes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Optional<Resume> findResumeByProfileUserUsernameAndActiveTrue(String username);
    Optional<Resume> findResumeByProfileId(Long id);
    Optional<Resume> findResumeByIdAndActiveTrue(Integer id);


    @Modifying
    @Query(value = "INSERT INTO resumes (created_at, updated_at, active, completed, curricular_title," +
            "description, profile_id,style_id) VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT, ' ', ' ', ?, 1);"
            ,nativeQuery = true)
    void saveFirstResume(Long profileId);

    @Modifying
    @Query(value = "UPDATE resumes t SET t.style_id = ? WHERE t.id_resume = ?;",nativeQuery = true)
    void changeStyle(int styleId, int resumeId);
}
