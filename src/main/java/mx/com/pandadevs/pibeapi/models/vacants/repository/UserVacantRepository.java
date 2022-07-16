package mx.com.pandadevs.pibeapi.models.vacants.repository;

import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVacantRepository extends JpaRepository<UserVacant, Integer> {
    List<UserVacant> findAllByUser_Username(String username);

    List<UserVacant> findAllByVacant_Id(Integer id);

}
