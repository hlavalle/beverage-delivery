package com.hlavalle.abichallenge.service;

import com.hlavalle.abichallenge.dto.RankingDto;
import com.hlavalle.abichallenge.model.DeliveryOrder;

import java.util.List;

public interface OrderService {

    DeliveryOrder registerDeliveryOrder(DeliveryOrder deliveryOrder);

    List<RankingDto> getOrderRanking(Long orderId);

}
