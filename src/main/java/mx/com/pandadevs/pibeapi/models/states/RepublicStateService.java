package mx.com.pandadevs.pibeapi.models.states;

import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import mx.com.pandadevs.pibeapi.models.states.mapper.RepublicStateMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RepublicStateService implements ServiceInterface<Integer, RepublicStateDto> {

    private final RepublicStateMapper mapper;

    @Autowired
    private RepublicStateRepository repository;

    public RepublicStateService(RepublicStateMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RepublicStateDto> getAll() {
        return mapper.toRepublicStatesDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RepublicStateDto> getById(Integer id) {
        Optional<RepublicState> state = repository.findById(id);
        return state.map(mapper::toRepublicStateDTO);
    }

    @Override
    public RepublicStateDto save(RepublicStateDto entity) {
        return null;
    }

    @Override
    public Optional<RepublicStateDto> update(RepublicStateDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<RepublicStateDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<RepublicState> list = new ArrayList<RepublicState>() {{
            add(new RepublicState("Aguascalientes"));
            add(new RepublicState("Baja California"));
            add(new RepublicState("Baja California Sur"));
            add(new RepublicState("Campeche"));
            add(new RepublicState("Coahuila"));
            add(new RepublicState("Colima"));
            add(new RepublicState("Chiapas"));
            add(new RepublicState("Chihuahua"));
            add(new RepublicState("Ciudad de México"));
            add(new RepublicState("Durango"));
            add(new RepublicState("Guanajuato"));
            add(new RepublicState("Guerrero"));
            add(new RepublicState("Hidalgo"));
            add(new RepublicState("Jalisco"));
            add(new RepublicState("México"));
            add(new RepublicState("Michoacán"));
            add(new RepublicState("Morelos"));
            add(new RepublicState("Nayarit"));
            add(new RepublicState("Nuevo León"));
            add(new RepublicState("Oaxaca"));
            add(new RepublicState("Puebla"));
            add(new RepublicState("Querétaro"));
            add(new RepublicState("Quintana Roo"));
            add(new RepublicState("San Luis Potosí"));
            add(new RepublicState("Sinaloa"));
            add(new RepublicState("Sonora"));
            add(new RepublicState("Tabasco"));
            add(new RepublicState("Tamaulipas"));
            add(new RepublicState("Tlaxcala"));
            add(new RepublicState("Veracruz"));
            add(new RepublicState("Yucatán"));
            add(new RepublicState("Zacatecas"));
        }};
        repository.saveAll(list);
    }
}
