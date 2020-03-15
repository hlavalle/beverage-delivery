package com.hlavalle.beveragedelivery.service;

import com.hlavalle.beveragedelivery.dto.RankingDto;
import com.hlavalle.beveragedelivery.model.DeliveryOrder;

import java.util.List;

public interface OrderService {

    DeliveryOrder registerDeliveryOrder(DeliveryOrder deliveryOrder);

    List<RankingDto> getOrderRanking(Long orderId);

}
