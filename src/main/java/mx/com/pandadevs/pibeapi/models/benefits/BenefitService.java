package mx.com.pandadevs.pibeapi.models.benefits;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import mx.com.pandadevs.pibeapi.models.periods.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.benefits.mapper.BenefitMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class BenefitService implements ServiceInterface<Integer, BenefitDto> {

    private final BenefitMapper mapper;
    @Autowired
    private BenefitRepository repository;

    public BenefitService(BenefitMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BenefitDto> getAll() {
        return mapper.toBenefitsDto(repository.findAllByActiveIsTrue());
    }

    @Override
    public Optional<BenefitDto> getById(Integer id) {
        Optional<Benefit> benefit = repository.findByIdAndActiveIsTrue(id);
        return benefit.map(mapper::toBenefitDto);
    }

    @Override
    public BenefitDto save(BenefitDto entity) {
        Benefit benefit = mapper.toBenefit(entity);
        return mapper.toBenefitDto(repository.saveAndFlush(benefit));
    }

    @Override
    public Optional<BenefitDto> update(BenefitDto entity) {
        Optional<Benefit> updated = repository.findByIdAndActiveIsTrue(entity.getId());
        if (updated.isPresent()) {
            return Optional.of(mapper.toBenefitDto(repository.save(mapper.toBenefit(entity))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<BenefitDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Benefit> updatedEntity = repository.findByIdAndActiveIsTrue(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    Field field = ReflectionUtils.findField(Benefit.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toBenefitDto(repository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception ignored) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.findByIdAndActiveIsTrue(id).map(entity -> {
            entity.setActive(false);
            repository.save(entity);
            return true;
        }).orElse(false);
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<Benefit> list = new ArrayList<Benefit>() {{
            add(new Benefit("Certificaciones"));
            add(new Benefit("Seguro médico"));
            add(new Benefit("Vacaciones"));
            add(new Benefit("Préstamos"));
            add(new Benefit("Cursos"));
            add(new Benefit("Vales de despensa"));
        }};
        repository.saveAll(list);
    }
}
