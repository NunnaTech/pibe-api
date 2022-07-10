package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/resume/")
public class ResumeController implements ControllerInterface<ResumeDto> {
    @Autowired
    private ResumeService resumeService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<ResumeDto>> getAll() {
            return new ResponseEntity(resumeService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResumeDto> getOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResumeDto> save(ResumeDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<ResumeDto> update(ResumeDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<ResumeDto> partialUpdate(Long id, Map<Object, Object> fields) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }
}
