package mx.com.pandadevs.pibeapi.models.languages.repository;

import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import mx.com.pandadevs.pibeapi.models.styles.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    List<Language> findAllByActiveTrueOrderByCreatedAtDesc();
    Optional<Language> findByIdAndActiveTrue(Integer id);
}
