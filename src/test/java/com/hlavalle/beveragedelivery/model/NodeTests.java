package com.hlavalle.beveragedelivery.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTests {

    @Test
    public void shouldAddDestination() {
        Node sourceNode = new Node();
        Node destinationNode1 = new Node();
        Node destinationNode2 = new Node();

        sourceNode.addDestination(destinationNode1, 5);
        sourceNode.addDestination(destinationNode2, 10);

        assertEquals(2,sourceNode.getAdjacentNodes().size());
        assertEquals(5, sourceNode.getAdjacentNodes().get(destinationNode1).intValue());
        assertEquals(10, sourceNode.getAdjacentNodes().get(destinationNode2).intValue());
    }

}
