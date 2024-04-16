package dalilagiu.U5W3D1.controllers;

import dalilagiu.U5W3D1.entities.Employee;
import dalilagiu.U5W3D1.exceptions.BadRequestException;
import dalilagiu.U5W3D1.payloads.EmployeeDTO;
import dalilagiu.U5W3D1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //ATTRIBUTES LIST:
    @Autowired
    private EmployeeService employeeService;

    //CRUD METHODS:
    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return this.employeeService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return this.employeeService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveNewEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return this.employeeService.save(body);
    }

    @PutMapping("/{id}")
    public Employee findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated EmployeeDTO body, BindingResult validation) {
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return this.employeeService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        this.employeeService.findByIdAndDelete(id);
    }

    @PatchMapping("/{id}/upload")
    public Employee uploadAvatar(@PathVariable long id, @RequestParam("avatar")MultipartFile image) throws IOException {
        return this.employeeService.changeAvatarImage(id, image);
    }
}
