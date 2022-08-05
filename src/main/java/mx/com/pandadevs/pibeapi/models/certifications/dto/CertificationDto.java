package mx.com.pandadevs.pibeapi.models.certifications.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CertificationDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
    private String name;

    private String company;

    private Boolean active;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime obtainedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
