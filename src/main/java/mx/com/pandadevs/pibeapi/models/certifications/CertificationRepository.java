package mx.com.pandadevs.pibeapi.models.certifications;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Integer> {
    List<Certification> findAllByResumeIdAndActiveTrueOrderByCreatedAtAsc(Integer id);
}
