package mx.com.pandadevs.pibeapi.models.benefits;

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
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.benefits.mapper.BenefitMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class BenefitService implements ServiceInterface<Integer, BenefitDto> {

    private final BenefitMapper mapper;
    @Autowired
    private BenefitRepository benefitRepository;

    public BenefitService(BenefitMapper mapper){this.mapper = mapper;}

    @Override
    public List<BenefitDto> getAll() {
        return mapper.toBenefitsDto(benefitRepository.findAll());
    }

    @Override
    public Optional<BenefitDto> getById(Integer id) {
        Optional<Benefit> benefit = benefitRepository.findById(id);
        return benefit.map(entity -> {
            return Optional.of(mapper.toBenefitDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public BenefitDto save(BenefitDto entity) {
        Benefit benefit = mapper.toBenefit(entity);
        return mapper.toBenefitDto(benefitRepository.saveAndFlush(benefit));
    }

    @Override
    public Optional<BenefitDto> update(BenefitDto entity) {
        Optional<Benefit> updatedEntity = benefitRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            benefitRepository.saveAndFlush(updated);
            return Optional.of(mapper.toBenefitDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<BenefitDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Benefit> updatedEntity = benefitRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(User.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toBenefitDto(benefitRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return benefitRepository.findById(id).map(entity -> {
            benefitRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
