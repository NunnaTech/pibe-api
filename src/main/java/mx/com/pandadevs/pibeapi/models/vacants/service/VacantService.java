package mx.com.pandadevs.pibeapi.models.vacants.service;

import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.VacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VacantService implements ServiceInterface<VacantDto> {

    private final VacantMapper mapper;
    @Autowired
    private VacantRepository vacantRepository;
    public  VacantService(VacantMapper mapper){ this.mapper = mapper;}
    @Override
    public List<VacantDto> getAll() {
        return mapper.toVacantsDto(vacantRepository.findAll());
    }

    @Override
    public Optional<VacantDto> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public VacantDto save(VacantDto entity) {
        return null;
    }

    @Override
    public Optional<VacantDto> update(VacantDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<VacantDto> partialUpdate(Long id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
