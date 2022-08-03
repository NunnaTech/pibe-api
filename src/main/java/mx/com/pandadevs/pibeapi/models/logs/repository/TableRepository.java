package mx.com.pandadevs.pibeapi.models.logs.repository;

import mx.com.pandadevs.pibeapi.models.logs.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Tables,String > {
    Tables findByNameLikeIgnoreCase(String table);
}
