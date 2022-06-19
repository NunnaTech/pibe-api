package mx.com.pandadevs.pibeapi.models.tables;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "TABLES")
@Setter
@Getter
public class Tables extends PibeModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
}
