package com.hlavalle.abichallenge.controller;

import com.hlavalle.abichallenge.dto.RankingDto;
import com.hlavalle.abichallenge.model.DeliveryOrder;
import com.hlavalle.abichallenge.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping()
    public DeliveryOrder registerDeliveryOrder(@Valid @RequestBody DeliveryOrder deliveryOrder) {
        return orderService.registerDeliveryOrder(deliveryOrder);
    }

    @GetMapping(path = "{orderId}/vehicle/ranking")
    public List<RankingDto> getRanking(@PathVariable Long orderId) {
        return orderService.getOrderRanking(orderId);
    }
}
