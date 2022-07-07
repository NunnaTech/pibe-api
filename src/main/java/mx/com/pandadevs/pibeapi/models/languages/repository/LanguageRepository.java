package mx.com.pandadevs.pibeapi.models.languages.repository;

import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
