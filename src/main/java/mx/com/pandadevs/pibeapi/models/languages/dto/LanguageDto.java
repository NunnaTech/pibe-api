package mx.com.pandadevs.pibeapi.models.languages.dto;
// Validations
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LanguageDto {
    private Integer id;
    @NotEmpty(message = "Please provide a language name")
    @Size(min = 2, max = 40)
    private String language;

    @NotEmpty(message = "Please provide a abbreviation language")
    @Size(min = 1, max = 5)
    private String abbreviation;
    // Getters && Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
