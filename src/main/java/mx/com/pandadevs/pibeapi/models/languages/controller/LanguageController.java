package mx.com.pandadevs.pibeapi.models.languages.controller;

// Java
import java.util.List;
import java.util.Map;
// Spring
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.languages.services.ResumeLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Models
import mx.com.pandadevs.pibeapi.models.languages.dto.LanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.services.LanguageService;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;

@RestController
@RequestMapping("languages/")
public class LanguageController implements ControllerInterface<LanguageDto, Integer> {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ResumeLanguageService resumeLanguage;

    @Override
    @GetMapping("")
    public ResponseEntity<List<LanguageDto>> getAll() {
        return new ResponseEntity(languageService.getAll(), HttpStatus.OK);
    }


    @GetMapping("r")
    public ResponseEntity<List<ResumeLanguageDto>> getAllR() {
        return new ResponseEntity(resumeLanguage.getAll(), HttpStatus.OK);
    }
    @Override
    @GetMapping("{id}")
    public ResponseEntity<LanguageDto> getOne(@PathVariable("id") Integer id) {
        return languageService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<LanguageDto> save(@RequestBody LanguageDto entity) {
        return new ResponseEntity<>(languageService.save(entity), HttpStatus.CREATED);
    }
    @PostMapping("r")
    public ResponseEntity<ResumeLanguageDto> saver(@RequestBody ResumeLanguageDto entity) {
        return new ResponseEntity<>(resumeLanguage.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("")
    public ResponseEntity<LanguageDto> update(@RequestBody LanguageDto entity) {
        return languageService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("{id}")
    public ResponseEntity<LanguageDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return languageService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean deleted = languageService.delete(id);
        if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
        else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
    }
}
