package dalilagiu.U5W3D1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dalilagiu.U5W3D1.entities.Employee;
import dalilagiu.U5W3D1.exceptions.BadRequestException;
import dalilagiu.U5W3D1.exceptions.NotFoundException;
import dalilagiu.U5W3D1.payloads.EmployeeDTO;
import dalilagiu.U5W3D1.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeService {
    //ATTRIBUTES LIST:
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private Cloudinary cloudinary;

    //METHODS:
    public Page<Employee> findAll(int page, int size, String sort) {
        if (size > 30) size = 30;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return this.employeeDAO.findAll(pageable);
    }

    public Employee findById(long id) {
        return this.employeeDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee save (EmployeeDTO body){
        this.employeeDAO.findByEmail(body.email()).ifPresent(
                employee -> {
                    throw new BadRequestException("Email " + employee.getEmail() + " is already used!");
                }
        );
        Employee newEmployee = new Employee(body.username(), body.firstName(), body.lastName(), body.email());
        newEmployee.setAvatarImage("https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName());
        return this.employeeDAO.save(newEmployee);
    }

    public Employee findByIdAndUpdate(long id, EmployeeDTO modifiedEmployee) {
        Employee found = this.findById(id);
        found.setUsername(modifiedEmployee.username());
        found.setFirstName(modifiedEmployee.firstName());
        found.setLastName(modifiedEmployee.lastName());
        found.setAvatarImage("https://ui-avatars.com/api/?name=" + modifiedEmployee.firstName() + "+" + modifiedEmployee.lastName());
        return this.employeeDAO.save(found);
    }

    public void findByIdAndDelete(long id){
        Employee found = this.findById(id);
        this.employeeDAO.delete(found);
    }

    public Employee changeAvatarImage (long id, MultipartFile image) throws IOException {
        Employee found = this.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarImage(url);
        return this.employeeDAO.save(found);
    }
}
