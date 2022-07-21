package mx.com.pandadevs.pibeapi.models.vacants.dto;

import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;

public class UserVacantDto  {
    private Integer id;
    private VacantDto vacant;
    private ProcessDto process;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VacantDto getVacant() {
        return vacant;
    }

    public void setVacant(VacantDto vacant) {
        this.vacant = vacant;
    }

    public ProcessDto getProcess() {
        return process;
    }

    public void setProcess(ProcessDto process) {
        this.process = process;
    }
}
