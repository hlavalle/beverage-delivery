package com.hlavalle.abichallenge.service;

import com.hlavalle.abichallenge.dto.RankingDto;
import com.hlavalle.abichallenge.exception.OrderNotFoundException;
import com.hlavalle.abichallenge.model.DeliveryOrder;
import com.hlavalle.abichallenge.model.Vehicle;
import com.hlavalle.abichallenge.repository.DeliveryOrderRepository;
import com.hlavalle.abichallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    public DeliveryOrder registerDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public List<RankingDto> getOrderRanking(Long orderId) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(v -> calculateVehicleScore(v, deliveryOrder.getLocation()))
                .sorted(Comparator.comparingInt(RankingDto::getScore).reversed())
                .collect(Collectors.toList());
    }

    private RankingDto calculateVehicleScore(Vehicle vehicle, String deliveryLocation) {
        RankingDto rankingDto = new RankingDto();
        rankingDto.setCapacity(vehicle.getCapacity());
        rankingDto.setLocation(vehicle.getLocation());
        rankingDto.setModel(vehicle.getModel());
        rankingDto.setScore(vehicle.getCapacity());
        return rankingDto;
    }


}
