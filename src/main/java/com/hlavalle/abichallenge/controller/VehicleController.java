package com.hlavalle.abichallenge.controller;

import com.hlavalle.abichallenge.model.Vehicle;
import com.hlavalle.abichallenge.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @PostMapping()
    public Vehicle registerVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.registerVehicle(vehicle);
    }
}
