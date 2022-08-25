package mx.com.pandadevs.pibeapi.models.vacants.repository;

import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacantRepository extends JpaRepository<Vacant, Integer> {
    List<Vacant> findAllByActiveIsTrueAndIsPublicIsTrue();
    List<Vacant> findAllByActiveIsTrueAndIsPublicIsTrueAndEndDateGreaterThanEqual(LocalDateTime time);
    Optional<Vacant> findByIdAndActiveIsTrue(Integer id);
}
