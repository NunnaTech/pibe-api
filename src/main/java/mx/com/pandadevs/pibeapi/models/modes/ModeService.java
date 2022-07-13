package mx.com.pandadevs.pibeapi.models.modes;

import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.modes.mapper.ModeMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ModeService implements ServiceInterface<Integer, ModeDto> {

    private final ModeMapper mapper;
    @Autowired
    private ModeRepository modeRepository;

    public ModeService(ModeMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ModeDto> getAll() {
        return mapper.toModesDto(modeRepository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ModeDto> getById(Integer id) {
        Optional<Mode> mode = modeRepository.findByIdAndActiveIsTrue(id);
        return mode.map(mapper::toModeDto);
    }

    @Transactional
    @Override
    public ModeDto save(ModeDto entity) {
        return mapper.toModeDto(modeRepository.save(mapper.toMode(entity)));
    }

    @Transactional
    @Override
    public Optional<ModeDto> update(ModeDto entity) {
        Optional<Mode> update = modeRepository.findByIdAndActiveIsTrue(entity.getId());
        if (update.isPresent()) return Optional.of(mapper.toModeDto(modeRepository.save(mapper.toMode(entity))));
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ModeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Boolean delete(Integer id) {
        Optional<Mode> deletedMode = modeRepository.findByIdAndActiveIsTrue(id);
        if (deletedMode.isPresent()) {
            deletedMode.get().setActive(false);
            modeRepository.save(deletedMode.get());
            return true;
        }
        return false;
    }

    public void fillInitialData() {
        if (modeRepository.count() > 0) return;
        ArrayList<Mode> modes = new ArrayList<Mode>() {{
            add(new Mode("Presencial"));
            add(new Mode("Remoto"));
            add(new Mode("Híbrido"));
        }};
        modeRepository.saveAll(modes);
    }
}
