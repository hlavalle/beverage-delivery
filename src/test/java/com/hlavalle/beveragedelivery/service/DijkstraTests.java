package com.hlavalle.beveragedelivery.service;

import com.hlavalle.beveragedelivery.model.Graph;
import com.hlavalle.beveragedelivery.model.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DijkstraTests {

    Graph graph;

    @Before
    public void initGraph() {
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

        graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
    }

    @Test
    public void shouldGetShortestDistanceBetweenAandF() {
        assertEquals(16, Dijkstra.calculateShortestDistance(graph, "A", "F"));
    }

    @Test
    public void shouldGetShortestDistanceBetweenFandA() {
        assertEquals(16,Dijkstra.calculateShortestDistance(graph, "F", "A"));
    }

    @Test
    public void shouldGetShortestDistanceAetweenAandA() {
        assertEquals(0,Dijkstra.calculateShortestDistance(graph, "A", "A"));
    }

}
