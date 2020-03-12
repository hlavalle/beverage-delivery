package com.hlavalle.abichallenge.controller;

import com.hlavalle.abichallenge.model.DeliveryOrder;
import com.hlavalle.abichallenge.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @PostMapping()
    public DeliveryOrder registerDeliveryOrder(@Valid @RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }
}
