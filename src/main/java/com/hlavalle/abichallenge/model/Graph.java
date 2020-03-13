package com.hlavalle.abichallenge.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    @Getter @Setter
    private Set<Node> nodes = new HashSet<>();

    @Getter @Setter
    Map<String,Node> nodesMap = new HashMap<>();

    public void addNode(Node node) {
        nodes.add(node);
        nodesMap.put(node.getName(),node);
    }
}
