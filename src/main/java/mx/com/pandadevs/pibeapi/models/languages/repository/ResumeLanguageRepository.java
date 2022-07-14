package mx.com.pandadevs.pibeapi.models.languages.repository;

import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguageFK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeLanguageRepository extends JpaRepository<ResumeLanguage, Integer> {
}
