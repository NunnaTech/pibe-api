package mx.com.pandadevs.pibeapi.models.logs.dto;

import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.utils.enums.Action;

public class LogDto {
    private Integer id;
    private String oldData;
    private String newData;
    private Action action;
    private UserDto user;
    private TableDto table;

    // Getters && Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TableDto getTable() {
        return table;
    }

    public void setTable(TableDto table) {
        this.table = table;
    }
}
