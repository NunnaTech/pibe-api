package mx.com.pandadevs.pibeapi.models.vacants.repository;

import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacantRepository extends JpaRepository<Vacant, Integer> {
}
