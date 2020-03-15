package com.hlavalle.beveragedelivery.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    @Getter
    private Set<Node> nodes = new HashSet<>();

    @Getter
    private Map<String,Node> nodesMap = new HashMap<>();

    public void addNode(Node node) {
        nodes.add(node);
        nodesMap.put(node.getName(),node);
    }
}
