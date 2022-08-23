package mx.com.pandadevs.pibeapi.models.languages.repository;

import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.styles.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    List<Language> findAllByActiveTrueOrderByCreatedAtDesc();
    Optional<Language> findByIdAndActiveTrue(Integer id);
    Optional<Language> findByName(String name);


    @Modifying
    @Query(
            value = "INSERT INTO languages (created_at, updated_at, abbreviation, active, name) "+
                    "VALUES (DEFAULT, DEFAULT, :abbreviation, DEFAULT, :name);",
            nativeQuery = true)
    void saveLanguage(
            @Param("abbreviation") String abbreviation,
            @Param("name") String name);
}
