package mx.com.pandadevs.pibeapi.models.logs.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import mx.com.pandadevs.pibeapi.models.users.User;


@Entity
@Table(name = "LOGS")
public class Log extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;
    
    @Column(
        name = "old_data",
        columnDefinition = "json")
    private String oldData;

    @Column(
        name = "new_data",
        nullable = false,
        columnDefinition = "json")
    private String newData;

    @Column(
        name="action",
        nullable = false,
        length = 12 
        )
    @Enumerated(value = EnumType.STRING)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id" )
    private Tables table;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    
}
