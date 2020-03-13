package com.hlavalle.abichallenge.service;

import com.hlavalle.abichallenge.controller.Dijkstra;
import com.hlavalle.abichallenge.dto.RankingDto;
import com.hlavalle.abichallenge.exception.OrderNotFoundException;
import com.hlavalle.abichallenge.model.DeliveryOrder;
import com.hlavalle.abichallenge.model.Graph;
import com.hlavalle.abichallenge.model.Node;
import com.hlavalle.abichallenge.model.Vehicle;
import com.hlavalle.abichallenge.repository.DeliveryOrderRepository;
import com.hlavalle.abichallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        int sdbl = getShorterDistanceBetweenLocations(vehicle.getLocation(), deliveryOrder.getLocation());
        int d = 0;

        if (sdbl <= 5) {
            d = 100;
        }
        else if (sdbl > 5 && sdbl <= 10) {
            d = 75;
        }
        else if (sdbl > 10 && sdbl <= 15) {
            d = 50;
        }
        else if (sdbl > 15 && sdbl <= 20) {
            d = 25;
        }

        System.out.println(vehicle.toString());
        System.out.println(deliveryOrder.toString());
        System.out.println("sdbl = "+sdbl);
        System.out.println("d = "+d);

        n = 100 - 25 * ((nb - nc) / 10);
        score = (n + d) / 2;
        return score;
    }

    private int getShorterDistanceBetweenLocations(String n1, String n2) {

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

        Node startNode = graph.getNodesMap().get(n1);

        graph = Dijkstra.calculateShortestPathFromSource(graph, startNode);

        return graph.getNodesMap().get(n2).getDistance();
    }


}
