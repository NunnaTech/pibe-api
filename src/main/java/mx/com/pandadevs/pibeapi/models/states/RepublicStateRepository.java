package mx.com.pandadevs.pibeapi.models.states;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface RepublicStateRepository extends JpaRepository<RepublicState, Integer> {
}
