package dalilagiu.U5W3D1.services;

import dalilagiu.U5W3D1.entities.Device;
import dalilagiu.U5W3D1.exceptions.NotFoundException;
import dalilagiu.U5W3D1.payloads.DeviceDTO;
import dalilagiu.U5W3D1.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    //ATTRIBUTES LIST:
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private EmployeeService employeeService;

    //METHODS:
    public Page<Device> findAll (int page, int size, String sort) {
        if (size > 30) size = 30;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return this.deviceDAO.findAll(pageable);
    }

    public Device findById (long id) {
        return this.deviceDAO.findById(id).orElseThrow( ()-> new NotFoundException(id));
    }

    public Device save(DeviceDTO body) {
        Device newDevice = new Device(body.type(), body.status(), employeeService.findById(body.employeeId()));
        return deviceDAO.save(newDevice);
    }

    public Device findByIdAndUpdate(long id, DeviceDTO body) {
        Device found = this.findById(id);
        found.setType(body.type());
        found.setStatus(body.status());
        found.setEmployee(employeeService.findById(body.employeeId()));
        return this.deviceDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Device found = this.findById(id);
        this.deviceDAO.delete(found);
    }
}
