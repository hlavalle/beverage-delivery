package com.hlavalle.beveragedelivery.service;

import com.hlavalle.beveragedelivery.dto.RankingDto;
import com.hlavalle.beveragedelivery.exception.OrderNotFoundException;
import com.hlavalle.beveragedelivery.model.DeliveryOrder;
import com.hlavalle.beveragedelivery.model.Graph;
import com.hlavalle.beveragedelivery.model.Node;
import com.hlavalle.beveragedelivery.model.Vehicle;
import com.hlavalle.beveragedelivery.repository.DeliveryOrderRepository;
import com.hlavalle.beveragedelivery.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class OrderServiceImpl implements OrderService {

    private DeliveryOrderRepository deliveryOrderRepository;

    private VehicleRepository vehicleRepository;

    public OrderServiceImpl(DeliveryOrderRepository deliveryOrderRepository, VehicleRepository vehicleRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public DeliveryOrder registerDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public List<RankingDto> getOrderRanking(Long orderId) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(v -> getRankingDto(v, deliveryOrder))
                .sorted(Comparator.comparingInt(RankingDto::getScore).reversed())
                .collect(Collectors.toList());
    }

    private RankingDto getRankingDto(Vehicle vehicle, DeliveryOrder deliveryOrder) {
        RankingDto rankingDto = new RankingDto();
        rankingDto.setCapacity(vehicle.getCapacity());
        rankingDto.setLocation(vehicle.getLocation());
        rankingDto.setModel(vehicle.getModel());
        rankingDto.setScore(calculateVehicleScore(vehicle, deliveryOrder));
        rankingDto.setVehicleId(vehicle.getId());
        return rankingDto;
    }

    private int calculateVehicleScore(Vehicle vehicle, DeliveryOrder deliveryOrder) {
        int score;
        int n;
        int nc = vehicle.getCapacity();
        int nb = deliveryOrder.getQuantity();

        int shortestDistance = Dijkstra.calculateShortestDistance(
                initGraph(),
                vehicle.getLocation(),
                deliveryOrder.getLocation());

        int d = 0;

        if (shortestDistance <= 5) {
            d = 100;
        }
        else if (shortestDistance > 5 && shortestDistance <= 10) {
            d = 75;
        }
        else if (shortestDistance > 10 && shortestDistance <= 15) {
            d = 50;
        }
        else if (shortestDistance > 15 && shortestDistance <= 20) {
            d = 25;
        }

        n = 100 - 25 * (abs((nb - nc) / 10));
        score = (n + d) / 2;
        return score;
    }

    private Graph initGraph() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 5);

        nodeB.addDestination(nodeA, 5);
        nodeB.addDestination(nodeC, 7);
        nodeB.addDestination(nodeD, 3);

        nodeC.addDestination(nodeB, 7);
        nodeC.addDestination(nodeE, 4);

        nodeD.addDestination(nodeB, 3);
        nodeD.addDestination(nodeE, 10);
        nodeD.addDestination(nodeF, 8);

        nodeE.addDestination(nodeC, 4);
        nodeE.addDestination(nodeD, 10);

        nodeF.addDestination(nodeD, 8);

        Graph graph = new Graph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        return graph;
    }

}
