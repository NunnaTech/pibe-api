package mx.com.pandadevs.pibeapi.models.resumes.dto;

import java.util.List;

import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.certifications.dto.CertificationDto;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.studies.dto.StudyDto;
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResumeDto {
    private Integer id;
    @NotNull
    @NotEmpty(message = "Please provide a curricular title")
    @Size(min = 3, max = 80)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    private String curricularTitle;
    @NotNull
    @NotEmpty(message = "Please provide a short description")
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    private String description;
    @NotNull
    private Boolean completed;
    private Boolean active;
    @NotNull(message = "Please provide a completed profile")
    private ProfileDto profile;
    @NotNull(message = "Please provide a style")
    private StyleDto style;
    private List<AptitudeDto> aptitudes;
    @NotNull(message = "Please provide the languages you communicate")
    private List<ResumeLanguageDto> languages;
    @NotNull(message = "Please provide the languages you communicate")
    private List<StudyDto> studies;
    private List<CourseDto> courses;
    private List<WorkExperienceDto> experiences;
    private List<CertificationDto> certifications;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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

    public List<ResumeLanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<ResumeLanguageDto> languages) {
        this.languages = languages;
    }

    public List<StudyDto> getStudies() {
        return studies;
    }

    public void setStudies(List<StudyDto> studies) {
        this.studies = studies;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }

    public List<WorkExperienceDto> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkExperienceDto> experiences) {
        this.experiences = experiences;
    }

    public List<CertificationDto> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationDto> certifications) {
        this.certifications = certifications;
    }
}
