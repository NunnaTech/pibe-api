package mx.com.pandadevs.pibeapi.models.work_experiences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
    List<WorkExperience> findAllByResumeIdAndActiveTrueOrderByCreatedAtAsc(Integer id);
}
