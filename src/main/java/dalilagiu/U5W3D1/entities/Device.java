package dalilagiu.U5W3D1.entities;

import dalilagiu.U5W3D1.entities.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//LOMBOK:
@Getter
@Setter
@NoArgsConstructor
//DB:
@Entity
public class Device {
    //ATTRIBUTES LIST:
    private long id;
    private String type;
    private String status;
    @ManyToOne
    private Employee employee;

    //CONSTRUCTORS:
    public Device(String type){
        this.type = type;
        this.status= "AVAILABLE";
        this.employee= null;
    }
}
