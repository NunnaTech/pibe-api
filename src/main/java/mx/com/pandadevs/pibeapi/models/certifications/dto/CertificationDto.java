package mx.com.pandadevs.pibeapi.models.certifications.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CertificationDto {
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
    private String name;

    private String company;

    private Boolean active;

    private LocalDateTime expirationDate;

    private LocalDateTime obtainedDate;

    // Getters && Setters


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getObtainedDate() {
        return obtainedDate;
    }

    public void setObtainedDate(LocalDateTime obtainedDate) {
        this.obtainedDate = obtainedDate;
    }
}
