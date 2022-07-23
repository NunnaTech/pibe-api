package mx.com.pandadevs.pibeapi.models.logs.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
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

    public TableService(TableMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TableDto> getAll() {
        return mapper.toTablesDto(tableRepository.findAll());
    }

    @Override
    public Optional<TableDto> getById(String id) {
        Optional<Tables> table = tableRepository.findById(id);
        return table.map(mapper::toTableDto);
    }

    @Override
    public TableDto save(TableDto entity) {
        Tables table = mapper.toTables(entity);
        return mapper.toTableDto(tableRepository.save(table));
    }

    @Override
    public Optional<TableDto> update(TableDto entity) {
        Optional<Tables> updatedEntity = tableRepository.findById(entity.getName());
        if (updatedEntity.isPresent())
            return Optional.of(mapper.toTableDto(tableRepository.save(mapper.toTables(entity))));
        return Optional.empty();
    }

    @Override
    public Optional<TableDto> partialUpdate(String id, Map<Object, Object> fields) {
        try {
            Optional<Tables> updatedEntity = tableRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    Field field = ReflectionUtils.findField(Tables.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toTableDto(tableRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception ignored) {
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

    public void fillInitialData() {
        if (tableRepository.count() > 0) return;
        ArrayList<Tables> tables = new ArrayList<Tables>() {{
            add(new Tables("aptitudes"));
            add(new Tables("benefits"));
            add(new Tables("certifications"));
            add(new Tables("contacts"));
            add(new Tables("courses"));
            add(new Tables("languages"));
            add(new Tables("modes"));
            add(new Tables("notifications"));
            add(new Tables("periods"));
            add(new Tables("processes"));
            add(new Tables("profiles"));
            add(new Tables("republic_states"));
            add(new Tables("resume_aptitudes"));
            add(new Tables("resume_languages"));
            add(new Tables("resumes"));
            add(new Tables("roles"));
            add(new Tables("schedule"));
            add(new Tables("studies"));
            add(new Tables("styles"));
            add(new Tables("users"));
            add(new Tables("users_notifications"));
            add(new Tables("users_roles"));
            add(new Tables("users_vacants"));
            add(new Tables("vacants"));
            add(new Tables("vacants_benefits"));
            add(new Tables("vacants_favorites"));
            add(new Tables("work_experiences"));
        }};
        tableRepository.saveAll(tables);
    }
}
