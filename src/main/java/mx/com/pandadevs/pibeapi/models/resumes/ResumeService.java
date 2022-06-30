package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ResumeService implements ServiceInterface<ResumeDto> {
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
    public Optional<ResumeDto> getById(Long id) {
        return Optional.empty();

    }
    public Optional<ResumeDto> getByUsername(String username) {
        Optional<Resume> resume = resumeRepository.findResumeByProfileUserUsernameAndActiveTrue(username);
        return resume.map(entity ->{return Optional.of(mapper.toResumeDto(entity));}).orElse(Optional.empty());
    }

    @Override
    public ResumeDto save(ResumeDto entity) {
        return null;
    }

    @Override
    public Optional<ResumeDto> update(ResumeDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<ResumeDto> partialUpdate(Long id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
