package mx.com.pandadevs.pibeapi.models.resumes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Optional<Resume> findResumeByProfileUserUsernameAndActiveTrue(String username);
}
