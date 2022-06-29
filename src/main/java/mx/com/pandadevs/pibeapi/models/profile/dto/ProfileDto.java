package mx.com.pandadevs.pibeapi.models.profile.dto;

import mx.com.pandadevs.pibeapi.models.states.RepublicState;
import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import mx.com.pandadevs.pibeapi.utils.enums.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ProfileDto {

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    private String name;

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    private String secondName;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    private Gender sex;

    private Boolean completed;

    private String picture;

    private String phoneNumber;

    private RepublicStateDto state ;

    private String position;

    // Getters && Setters

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

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
