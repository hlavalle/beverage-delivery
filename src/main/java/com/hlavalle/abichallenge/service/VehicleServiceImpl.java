package com.hlavalle.abichallenge.service;

import com.hlavalle.abichallenge.model.Vehicle;
import com.hlavalle.abichallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    private final Map<String, Integer> vehicleCapacity  = new HashMap<String, Integer>() {{
        put("A", 10);
        put("B", 20);
        put("C", 30);
        put("D", 40);
        put("E", 50);
    }};

    public Vehicle registerVehicle(Vehicle vehicle) {
        vehicle.setCapacity(vehicleCapacity.get(vehicle.getType()));
        return vehicleRepository.save(vehicle);
    }

}
