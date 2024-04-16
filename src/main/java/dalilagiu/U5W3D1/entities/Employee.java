package dalilagiu.U5W3D1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//LOMBOK:
@Getter
@Setter
@NoArgsConstructor
//DB:
@Entity
public class Employee {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //ATTRIBUTES LIST:
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private String avatarUrl;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Device> assignedDevicesList;


    //CONSTRUCTORS:
    public Employee(String username, String firstName, String lastName,String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
