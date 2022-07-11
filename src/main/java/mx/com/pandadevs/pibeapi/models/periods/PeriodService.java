package mx.com.pandadevs.pibeapi.models.periods;

import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.periods.mapper.PeriodMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeriodService implements ServiceInterface<Integer, PeriodDto> {

    private final PeriodMapper mapper;

    @Autowired private PeriodRepository repository;

    public PeriodService(PeriodMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeriodDto> getAll() {
        return mapper.toPeriodsDto(repository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PeriodDto> getById(Integer id) {
        return Optional.of(mapper.toPeriodDto(repository.findByIdAndActiveIsTrue(id).get()));
    }

    @Transactional
    @Override
    public PeriodDto save(PeriodDto entity) {
        return mapper.toPeriodDto(repository.save(mapper.toPeriod(entity)));
    }

    @Transactional
    @Override
    public Optional<PeriodDto> update(PeriodDto entity) {
        Optional<Period> updated = repository.findByIdAndActiveIsTrue(entity.getId());
        if (updated.isPresent()) {
            return Optional.of(mapper.toPeriodDto(repository.save(mapper.toPeriod(entity))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PeriodDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Boolean delete(Integer id) {
        Optional<Period> deleted = repository.findByIdAndActiveIsTrue(id);
        if (deleted.isPresent()) {
            deleted.get().setActive(false);
            repository.save(deleted.get());
            return true;
        }
        return false;
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<Period> modes = new ArrayList<Period>() {{
            add(new Period("Hora"));
            add(new Period("Día"));
            add(new Period("Semanal"));
            add(new Period("Quincenal"));
            add(new Period("Mensual"));
            add(new Period("Anual"));
        }};
        repository.saveAll(modes);
    }
}
