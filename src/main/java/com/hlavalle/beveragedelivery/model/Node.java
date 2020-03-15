package com.hlavalle.beveragedelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class Node {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<Node> shortestPath = new LinkedList<>();

    @Getter @Setter
    private Integer distance = Integer.MAX_VALUE;

    @Getter @Setter
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }
}
