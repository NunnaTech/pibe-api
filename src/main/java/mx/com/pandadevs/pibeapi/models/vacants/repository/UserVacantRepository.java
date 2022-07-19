package mx.com.pandadevs.pibeapi.models.vacants.repository;

import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVacantRepository extends JpaRepository<UserVacant, Integer> {
    List<UserVacant> findAllByUser_Username(String username);

    List<UserVacant> findAllByVacant_Id(Integer id);

    Optional<UserVacant> findByUser_IdAndVacant_Id(Long idUser, Integer idVacant);

}
