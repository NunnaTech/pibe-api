package mx.com.pandadevs.pibeapi.models.aptitudes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AptitudeRepository extends JpaRepository<Aptitude, Integer> {
    List<Aptitude> findAllByActiveTrueOrderByCreatedAtAsc();
    Optional<Aptitude> findStyleByIdAndActiveTrue(Integer id);
}
