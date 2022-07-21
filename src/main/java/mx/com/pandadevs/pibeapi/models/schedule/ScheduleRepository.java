package mx.com.pandadevs.pibeapi.models.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByActiveIsTrue();
    Optional<Schedule> findByIdAndActiveIsTrue(Integer id);
}
