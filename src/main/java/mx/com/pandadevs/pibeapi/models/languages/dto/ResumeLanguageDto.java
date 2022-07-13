package mx.com.pandadevs.pibeapi.models.languages.dto;
public class ResumeLanguageDto {
    private LanguageDto language;
    private String level;
    // Getter && Setters
    public LanguageDto getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDto language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
