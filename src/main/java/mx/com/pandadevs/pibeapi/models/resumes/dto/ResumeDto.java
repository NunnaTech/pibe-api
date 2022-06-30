package mx.com.pandadevs.pibeapi.models.resumes.dto;
// Java
import java.util.List;
import java.util.Set;

// Models
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.certifications.dto.CertificationDto;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.studies.dto.StudyDto;
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;

public class ResumeDto {
    private Integer id;
    private String curricularTitle;
    private String description;
    private Boolean completed;
    private ProfileDto profile;
    private StyleDto style;
    private List<AptitudeDto> aptitudes;
    private Set<ResumeLanguageDto> languages;
    private Set<StudyDto> studies;
    private Set<CourseDto> courses;
    private Set<WorkExperienceDto> experiences;
    private Set<CertificationDto> certifications;

    // Getters && Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurricularTitle() {
        return curricularTitle;
    }

    public void setCurricularTitle(String curricularTitle) {
        this.curricularTitle = curricularTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public StyleDto getStyle() {
        return style;
    }

    public void setStyle(StyleDto style) {
        this.style = style;
    }

    public List<AptitudeDto> getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(List<AptitudeDto> aptitudes) {
        this.aptitudes = aptitudes;
    }

    public Set<ResumeLanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<ResumeLanguageDto> languages) {
        this.languages = languages;
    }

    public Set<StudyDto> getStudies() {
        return studies;
    }

    public void setStudies(Set<StudyDto> studies) {
        this.studies = studies;
    }

    public Set<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDto> courses) {
        this.courses = courses;
    }

    public Set<WorkExperienceDto> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<WorkExperienceDto> experiences) {
        this.experiences = experiences;
    }

    public Set<CertificationDto> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<CertificationDto> certifications) {
        this.certifications = certifications;
    }
}
