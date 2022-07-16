package mx.com.pandadevs.pibeapi.models.vacants.service;

import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.VacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VacantService implements ServiceInterface<Integer, VacantDto> {


    private Logger logger = LoggerFactory.getLogger(VacantService.class);

    private final VacantMapper mapper;

    @Autowired
    private VacantRepository vacantRepository;

    @Autowired
    private UserRepository userRepository;

    public VacantService(VacantMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VacantDto> getAll() {
        return mapper.toVacantsDto(vacantRepository.findAllByActiveIsTrueAndIsPublicIsTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<VacantDto> getById(Integer id) {
        Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
        return vacant.map(mapper::toVacantDto);
    }

    @Transactional(readOnly = true)
    public List<VacantDto> getByUsername(String username) {
        List<Vacant> list = new ArrayList<>();
        User user = userRepository.findByUsername(username);
        if (user != null) list = user.getVacants();
        return mapper.toVacantsDto(list);
    }

    @Transactional
    @Override
    public VacantDto save(VacantDto entity) {
        Vacant vacant = mapper.toVacant(entity);
        vacant.setUser(userRepository.findByUsername(entity.getCreator().getUsername()));
        return mapper.toVacantDto(vacantRepository.save(vacant));
    }

    @Transactional
    @Override
    public Optional<VacantDto> update(VacantDto entity) {
        Vacant vacant = mapper.toVacant(entity);
        Optional<Vacant> updated = vacantRepository.findByIdAndActiveIsTrue(vacant.getId());
        if (updated.isPresent()) {
            updated.get().setTitle(vacant.getTitle());
            updated.get().setDescription(vacant.getDescription());
            updated.get().setStartDate(vacant.getStartDate());
            updated.get().setEndDate(vacant.getEndDate());
            updated.get().setSalary(vacant.getSalary());
            updated.get().setPublic(vacant.getPublic());
            updated.get().setImage(vacant.getImage());
            updated.get().setSchedule(vacant.getSchedule());
            updated.get().setPeriod(vacant.getPeriod());
            updated.get().setMode(vacant.getMode());
            updated.get().setState(vacant.getState());
            updated.get().setBenefits(vacant.getBenefits());
            return Optional.of(mapper.toVacantDto(vacantRepository.save(updated.get())));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<VacantDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Vacant> updated = vacantRepository.findByIdAndActiveIsTrue(id);
            return updated.map(e -> {
                fields.forEach((updateField, value) -> {
                    Field field = ReflectionUtils.findField(Vacant.class, (String) updateField);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, e, value);
                });
                return Optional.of(mapper.toVacantDto(vacantRepository.saveAndFlush(e)));
            }).orElse(Optional.empty());
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Boolean delete(Integer id) {
        Optional<Vacant> deleted = vacantRepository.findByIdAndActiveIsTrue(id);
        if (deleted.isPresent()) {
            deleted.get().setActive(false);
            vacantRepository.save(deleted.get());
            return true;
        }
        return false;
    }
}
