package com.hlavalle.beveragedelivery.controller;

import com.hlavalle.beveragedelivery.dto.RankingDto;
import com.hlavalle.beveragedelivery.model.DeliveryOrder;
import com.hlavalle.beveragedelivery.service.OrderServiceImpl;
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
