package dalilagiu.U5W3D1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String status;
    @ManyToOne
    private Employee employee;

    //CONSTRUCTORS:
    public Device(String type, String status, Employee employee){
        this.type = type;
        this.status = status;
        this.employee = employee;
    }

    //when device is available:
    public Device(String type) {
        this.type = type;
        this.status = "AVAILABLE";
        this.employee = null;
    }
}
