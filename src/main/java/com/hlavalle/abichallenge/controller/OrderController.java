package com.hlavalle.abichallenge.controller;

import com.hlavalle.abichallenge.model.DeliveryOrder;
import com.hlavalle.abichallenge.model.Vehicle;
import com.hlavalle.abichallenge.repository.DeliveryOrderRepository;
import com.hlavalle.abichallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @PostMapping()
    public DeliveryOrder registerDeliveryOrder(@Valid @RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    @GetMapping(path = "{orderId}/vehicle/ranking")
    public List<Vehicle> getRanking(@PathVariable Long orderId) {

        System.out.println("order id = "+orderId);

        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles;

    }
}
