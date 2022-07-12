package mx.com.pandadevs.pibeapi.models.benefits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Integer> {
    List<Benefit> findAllByActiveIsTrue();
    Optional<Benefit> findByIdAndActiveIsTrue(Integer id);
}
