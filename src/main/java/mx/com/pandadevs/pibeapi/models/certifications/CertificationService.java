package mx.com.pandadevs.pibeapi.models.certifications;

import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitude;
import mx.com.pandadevs.pibeapi.models.aptitudes.AptitudeRepository;
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.models.certifications.dto.CertificationDto;
import mx.com.pandadevs.pibeapi.models.certifications.mapper.CertificationMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CertificationService implements ServiceInterface<Integer, CertificationDto> {
    private final CertificationMapper mapper;

    @Autowired
    private CertificationRepository certificationRepository;

    public CertificationService(CertificationMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<CertificationDto> getAll() {
        return mapper.toCertificationsDto(certificationRepository.findAll());
    }

    @Override
    public Optional<CertificationDto> getById(Integer id) {
        Optional<Certification> aptitude = certificationRepository.findById(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toCertificationDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public CertificationDto save(CertificationDto entity) {
        Certification certification = mapper.toCertification(entity);
        return mapper.toCertificationDto(certificationRepository.saveAndFlush(certification));
    }

    @Override
    public Optional<CertificationDto> update(CertificationDto entity) {
        Optional<Certification> updatedEntity = certificationRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            certificationRepository.saveAndFlush(updated);
            return Optional.of(mapper.toCertificationDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<CertificationDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Certification> updatedEntity = certificationRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(User.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toCertificationDto(certificationRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return certificationRepository.findById(id).map(entity -> {
            certificationRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
