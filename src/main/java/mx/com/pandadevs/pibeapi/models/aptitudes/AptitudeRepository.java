package mx.com.pandadevs.pibeapi.models.aptitudes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptitudeRepository extends JpaRepository<Aptitude, Integer> {
}
