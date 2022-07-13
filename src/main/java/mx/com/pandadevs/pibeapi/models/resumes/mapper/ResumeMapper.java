package mx.com.pandadevs.pibeapi.models.resumes.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.models.certifications.mapper.CertificationMapper;
import mx.com.pandadevs.pibeapi.models.courses.mapper.CourseMapper;
import mx.com.pandadevs.pibeapi.models.languages.mapper.ResumeLanguageMapper;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.studies.mapper.StudyMapper;
import mx.com.pandadevs.pibeapi.models.styles.mapper.StyleMapper;
import mx.com.pandadevs.pibeapi.models.work_experiences.mapper.WorkExperienceMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        AptitudeMapper.class,
        StyleMapper.class,
        ResumeLanguageMapper.class,
        StudyMapper.class,
        CourseMapper.class,
        WorkExperienceMapper.class,
        CertificationMapper.class,
        ProfileMapper.class,
})
public interface ResumeMapper {

    ResumeMapper MAPPER = Mappers.getMapper( ResumeMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "curricularTitle", target = "curricularTitle"),
        @Mapping(source = "completed", target = "completed"),
        @Mapping(source = "active", target = "active"),
        @Mapping(source = "profile", target = "profile"),
        @Mapping(source = "style", target = "style"),
        @Mapping(source = "aptitudes", target = "aptitudes"),
        @Mapping(source = "languages", target = "languages"),
        @Mapping(source = "studies", target = "studies"),
        @Mapping(source = "courses", target = "courses"),
        @Mapping(source = "experiences", target = "experiences"),
        @Mapping(source = "certifications", target = "certifications"),
    })
    ResumeDto toResumeDto(Resume resume);

    List<ResumeDto> toResumesDto(List<Resume> resumes);

    @InheritInverseConfiguration
    Resume toResume(ResumeDto resumeDto);

}
