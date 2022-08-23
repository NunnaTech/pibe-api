package mx.com.pandadevs.pibeapi.models.logs.dto;

import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.utils.enums.Action;

public class LogDto {
    private Long id;
    private String oldData = "{}";
    private String newData = "{}";
    private Action action;
    private UserDto user;
    private TableDto table;

    public LogDto() {
    }

    public LogDto(String oldData, String newData, Action action, UserDto user, TableDto table) {
        this.oldData = oldData;
        this.newData = newData;
        this.action = action;
        this.user = user;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
