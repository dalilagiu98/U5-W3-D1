package dalilagiu.U5W3D1.services;

import dalilagiu.U5W3D1.entities.Employee;
import dalilagiu.U5W3D1.exceptions.BadRequestException;
import dalilagiu.U5W3D1.exceptions.NotFoundException;
import dalilagiu.U5W3D1.payloads.EmployeeDTO;
import dalilagiu.U5W3D1.repositories.EmployeeDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    //ATTRIBUTES LIST:
    private EmployeeDAO employeeDAO;

    //METHODS:
    public Page<Employee> findAll(int page, int size, String sort) {
        if(size > 50)  size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return this.employeeDAO.findAll(pageable);
    }

    public Employee findById(long id){
        return this.employeeDAO.findById(id).orElseThrow(() -> new NotFoundException("Id " + id + " not found!"));
    }

    public Employee save(EmployeeDTO payload) {
        if (employeeDAO.existsByEmail(payload.email())) throw new BadRequestException("Email " + payload.email() + " has already taken!");
        else {
            Employee newEmployee = new Employee(payload.username(), payload.name(), payload.surname(), payload.email(), payload.password());
            newEmployee.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
            return this.employeeDAO.save(newEmployee);
        }
    }

    public Employee update(long id, EmployeeDTO payload) {
        Employee found = this.findById(id);
        found.setUsername(payload.username());
        found.setFirstName(payload.name());
        found.setLastName(payload.surname());
        found.setPassword(payload.password());
        return employeeDAO.save(found);
    }

    public void delete(long id){
        Employee found = this.findById(id);
        employeeDAO.delete(found);
    }

    public Employee findByEmail (String email){
        return employeeDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not found."));
    }
}
