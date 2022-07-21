package mx.com.pandadevs.pibeapi.models.schedule;

import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.models.schedule.mapper.ScheduleMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleService implements ServiceInterface<Integer, ScheduleDto> {

    private final ScheduleMapper mapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleDto> getAll() {
        return mapper.toSchedulesDto(scheduleRepository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ScheduleDto> getById(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findByIdAndActiveIsTrue(id);
        return schedule.map(mapper::toScheduleDto);
    }

    @Override
    public ScheduleDto save(ScheduleDto entity) {
        return mapper.toScheduleDto(scheduleRepository.save(mapper.toSchedule(entity)));
    }

    @Transactional
    @Override
    public Optional<ScheduleDto> update(ScheduleDto entity) {
        Optional<Schedule> updatedSchedule = scheduleRepository.findByIdAndActiveIsTrue(entity.getId());
        if (updatedSchedule.isPresent()) return Optional.of(mapper.toScheduleDto(scheduleRepository.save(mapper.toSchedule(entity))));
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ScheduleDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Boolean delete(Integer id) {
        Optional<Schedule> deletedSchedule = scheduleRepository.findByIdAndActiveIsTrue(id);
        if(deletedSchedule.isPresent()){
            deletedSchedule.get().setActive(false);
            scheduleRepository.save(deletedSchedule.get());
            return true;
        }
        return false;
    }

    public void fillInitialData() {
        if (scheduleRepository.count() > 0) return;
        ArrayList<Schedule> modes = new ArrayList<Schedule>() {{
            add(new Schedule("Tiempo completo"));
            add(new Schedule("Medio tiempo"));
            add(new Schedule("Fines de semana"));
            add(new Schedule("Horario flexible"));
        }};
        scheduleRepository.saveAll(modes);
    }

}
