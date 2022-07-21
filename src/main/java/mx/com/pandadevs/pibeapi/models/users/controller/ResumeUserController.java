package mx.com.pandadevs.pibeapi.models.users.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.pandadevs.pibeapi.models.resumes.ResumeService;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user/")
@Api( tags = "Resume")
public class ResumeUserController {
    @Autowired
    private ResumeService resumeService;
    @ApiOperation("Get a resume by username")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @GetMapping("/{username}/resume")
    public ResponseEntity<ResumeDto> getResumeByUsername(@PathVariable("username") String username) {
        return resumeService.getByUsername(username)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation("Save a new resume")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK" ),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @PostMapping("/{username}/resume")
    public ResponseEntity<ResumeDto> save(@RequestBody ResumeDto entity) {
        return resumeService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation("Update a resume")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @PutMapping("/{username}/resume")
    public ResponseEntity<ResumeDto> update(@RequestBody ResumeDto entity) {
        return resumeService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation("Partial Update resume")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @PatchMapping("/{username}/resume/{id}")
    public ResponseEntity<ResumeDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return resumeService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation("Delete a Resume by resume id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 404, message = "Resume not found")
    })
    @DeleteMapping("/{username}/resume/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean deleted = resumeService.delete(id);
        if (deleted) return new ResponseEntity(true, HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }
}
