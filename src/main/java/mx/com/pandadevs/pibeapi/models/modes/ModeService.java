package mx.com.pandadevs.pibeapi.models.modes;

import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ModeService implements ServiceInterface<Mode> {

    @Autowired
    private ModeRepository modeRepository;

    @Override
    public List<Mode> getAll() {
        return modeRepository.findAll();
    }

    @Override
    public Optional<Mode> getById(Long id) {
        return Optional.of(modeRepository.findById(id));
    }

    @Override
    public Mode save(Mode entity) {
        return null;
    }

    @Override
    public Optional<Mode> update(Mode entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Mode> partialUpdate(Long id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
