package mx.com.pandadevs.pibeapi.models.styles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {
    List<Style> findAllByActiveTrueOrderByCreatedAtDesc();
    Optional<Style> findStyleByIdAndActiveTrue(Integer id);
}
