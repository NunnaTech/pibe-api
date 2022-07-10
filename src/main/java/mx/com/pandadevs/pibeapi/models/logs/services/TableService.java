package mx.com.pandadevs.pibeapi.models.logs.services;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
// Models
import mx.com.pandadevs.pibeapi.models.logs.dto.TableDto;
import mx.com.pandadevs.pibeapi.models.logs.entities.Tables;
import mx.com.pandadevs.pibeapi.models.logs.mapper.TableMapper;
import mx.com.pandadevs.pibeapi.models.logs.repository.TableRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
@Service
public class TableService implements ServiceInterface<String, TableDto> {
    private final TableMapper mapper;

    @Autowired
    private TableRepository tableRepository;

    public TableService(TableMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<TableDto> getAll() {
        return mapper.toTablesDto(tableRepository.findAll());
    }

    @Override
    public Optional<TableDto> getById(String id) {
        Optional<Tables> table = tableRepository.findById(id);
        return table.map(entity -> {
            return Optional.of(mapper.toTableDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public TableDto save(TableDto entity) {
        Tables table = mapper.toTables(entity);
        return mapper.toTableDto(tableRepository.saveAndFlush(table));
    }

    @Override
    public Optional<TableDto> update(TableDto entity) {
        Optional<Tables> updatedEntity = tableRepository.findById(entity.getName());
        return updatedEntity.map(updated -> {
            tableRepository.saveAndFlush(updated);
            return Optional.of(mapper.toTableDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<TableDto> partialUpdate(String id, Map<Object, Object> fields) {
        try {
            Optional<Tables> updatedEntity = tableRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Tables.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toTableDto(tableRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(String id) {
        return tableRepository.findById(id).map(entity -> {
            tableRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
