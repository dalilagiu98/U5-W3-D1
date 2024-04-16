package dalilagiu.U5W3D1.controllers;

import dalilagiu.U5W3D1.entities.Device;
import dalilagiu.U5W3D1.exceptions.BadRequestException;
import dalilagiu.U5W3D1.payloads.DeviceDTO;
import dalilagiu.U5W3D1.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    //ATTRIBUTES LIST:
    @Autowired
    public DeviceService deviceService;

    @GetMapping
    public Page<Device> getAllDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return this.deviceService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable long id) {
        return this.deviceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveNewDevice(@RequestBody @Validated DeviceDTO body, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.deviceService.save(body);
    }

    @PutMapping("/{id}")
    public Device findDeviceByIdAndUpdate(@PathVariable long id, @RequestBody @Validated DeviceDTO body, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.deviceService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.deviceService.findByIdAndDelete(id);
    }
}
