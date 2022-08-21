package mx.com.pandadevs.pibeapi.models.studies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer> {
    List<Study> findAllByResumeIdAndActiveTrueOrderByCreatedAtAsc(int id);
}
