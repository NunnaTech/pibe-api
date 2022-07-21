package mx.com.pandadevs.pibeapi.models.processes;

import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.processes.mapper.ProcessMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProcessService implements ServiceInterface<Integer, ProcessDto> {

    private final ProcessMapper mapper;

    @Autowired
    private ProcessRepository repository;

    public ProcessService(ProcessMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProcessDto> getAll() {
        return mapper.toProcessesDto(repository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProcessDto> getById(Integer id) {
        Optional<Process> process = repository.findByIdAndActiveIsTrue(id);
        return process.map(mapper::toProcessDto);
    }

    @Transactional
    @Override
    public ProcessDto save(ProcessDto entity) {
        return mapper.toProcessDto(repository.save(mapper.toProcess(entity)));
    }

    @Transactional
    @Override
    public Optional<ProcessDto> update(ProcessDto entity) {
        Optional<Process> updated = repository.findByIdAndActiveIsTrue(entity.getId());
        if (updated.isPresent()) return Optional.of(mapper.toProcessDto(repository.save(mapper.toProcess(entity))));
        return Optional.empty();
    }

    @Override
    public Optional<ProcessDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Boolean delete(Integer id) {
        Optional<Process> deleted = repository.findByIdAndActiveIsTrue(id);
        if (deleted.isPresent()) {
            deleted.get().setActive(false);
            repository.save(deleted.get());
            return true;
        }
        return false;
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<Process> modes = new ArrayList<Process>() {{
            add(new Process("Postulado"));
            add(new Process("CV Visto"));
            add(new Process("Entrevista"));
            add(new Process("Idóneo"));
            add(new Process("Contratado"));
            add(new Process("Finalizado"));
        }};
        repository.saveAll(modes);
    }
}
