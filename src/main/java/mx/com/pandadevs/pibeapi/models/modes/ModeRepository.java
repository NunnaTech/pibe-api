package mx.com.pandadevs.pibeapi.models.modes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer> {
    List<Mode> findAllByActiveIsTrue();
    Optional<Mode> findByIdAndActiveIsTrue(Integer id);
}