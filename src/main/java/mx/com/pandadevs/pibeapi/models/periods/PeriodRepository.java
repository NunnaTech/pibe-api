package mx.com.pandadevs.pibeapi.models.periods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period,  Integer> {
    List<Period> findAllByActiveIsTrue();
    Optional<Period> findByIdAndActiveIsTrue(Integer id);
}
