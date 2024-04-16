package dalilagiu.U5W3D1.repositories;

import dalilagiu.U5W3D1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    //METHODS CUSTOM:
    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}
