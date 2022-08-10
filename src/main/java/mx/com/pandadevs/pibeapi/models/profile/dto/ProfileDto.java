package mx.com.pandadevs.pibeapi.models.profile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ProfileDto {

    private Long id;
    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Special characters are not allowed")
    private String name;

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Special characters are not allowed")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Special characters are not allowed")
    private String secondName;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime birthDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Special characters are not allowed")
    private String gender;

    private Boolean completed;

    private String image;

    @Pattern(regexp = "^[0-9]*$", message = "Special characters are not allowed")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    private RepublicStateDto state ;

    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RepublicStateDto getState() {
        return state;
    }

    public void setState(RepublicStateDto state) {
        this.state = state;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", completed=" + completed +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state=" + state +
                ", position='" + position + '\'' +
                '}';
    }
}
