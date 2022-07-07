package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import mx.com.pandadevs.pibeapi.models.work_experiences.WorkExperience;
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ResumeService implements ServiceInterface<Integer,ResumeDto> {
    private final ResumeMapper mapper;
    @Autowired
    private ResumeRepository resumeRepository;

    public ResumeService(ResumeMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<ResumeDto> getAll() {
        return mapper.toResumesDto(resumeRepository.findAll());
    }

    @Override
    public Optional<ResumeDto> getById(Integer id) {
        Optional<Resume> resume  = resumeRepository.findById(id);
        return resume.map(entity -> {
            return Optional.of(mapper.toResumeDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<ResumeDto> getByUsername(String username) {
        Optional<Resume> resume = resumeRepository.findResumeByProfileUserUsernameAndActiveTrue(username);
        return resume.map(entity ->{return Optional.of(mapper.toResumeDto(entity));}).orElse(Optional.empty());
    }


    @Override
    public ResumeDto save(ResumeDto entity) {
        Resume resume = mapper.toResume(entity);
        return mapper.toResumeDto(resumeRepository.saveAndFlush(resume));
    }

    @Override
    public Optional<ResumeDto> update(ResumeDto entity) {
        Optional<Resume> updatedEntity = resumeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            resumeRepository.saveAndFlush(updated);
            return Optional.of(mapper.toResumeDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<ResumeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Resume> updatedEntity = resumeRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Resume.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toResumeDto(resumeRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return resumeRepository.findById(id).map(entity -> {
            resumeRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
