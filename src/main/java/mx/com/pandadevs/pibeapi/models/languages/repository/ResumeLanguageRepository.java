package mx.com.pandadevs.pibeapi.models.languages.repository;

import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeLanguageRepository extends JpaRepository<ResumeLanguage, Integer> {
    @Modifying
    @Query(
            value = "INSERT INTO resume_languages (level, language_id, resume_id)" +
                    "VALUES (:level, :language, :resume);",
            nativeQuery = true)
    void saveLanguage(
            @Param("level") String level,
            @Param("language") Integer languageId,
            @Param("resume") Integer resumeId);

    List<ResumeLanguage> findAllByResumeId(Integer id);
    List<ResumeLanguage> findAllByResumeIdAndActiveTrueOrderByCreatedAtAsc(Integer id);

}
