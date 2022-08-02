package mx.com.pandadevs.pibeapi.models.vacants.dto;

import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;

public class VacantProcessDto {
    private Integer id;
    private UserProfileDto user;
    private ProcessDto process;

    public VacantProcessDto() {
    }

    public VacantProcessDto(UserProfileDto user, ProcessDto process) {
        this.user = user;
        this.process = process;
    }

    public VacantProcessDto(Integer id, UserProfileDto user, ProcessDto process) {
        this.id = id;
        this.user = user;
        this.process = process;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserProfileDto getUser() {
        return user;
    }

    public void setUser(UserProfileDto user) {
        this.user = user;
    }

    public ProcessDto getProcess() {
        return process;
    }

    public void setProcess(ProcessDto process) {
        this.process = process;
    }
}
