package dalilagiu.U5W3D1.controllers;

import dalilagiu.U5W3D1.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    //CRUD METHODS (without POST because this functionality will be managed in Authorization controller):
    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sort) {
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {

    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody @Validated ){

    }


}

