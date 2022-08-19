package mx.com.pandadevs.pibeapi.models.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Integer> {


    @Modifying
    @Query(value = "UPDATE pibe.courses t SET t.active = ?, t.finished_date = ?, t.hours = ?," +
            "t.name = ?, t.realization_date = ?, t.training_institution = ?, t.resume_id = ? " +
            "WHERE t.id_course = ?;"
            ,nativeQuery = true)
    void updateCourse(Boolean active, LocalDateTime finishedDate,
                      int hours, String name, LocalDateTime realizationDate,
                      String trainingInstitution, Integer resumeId,Integer courseId);
}
