package mx.com.pandadevs.pibeapi.models.processes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer> {
    List<Process> findAllByActiveIsTrue();
    Optional<Process> findByIdAndActiveIsTrue(Integer id);
}
