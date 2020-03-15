package com.hlavalle.beveragedelivery.model;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

public class GraphTests {

    @Test
    public void shouldAddNode() {

        Graph graph = new Graph();
        Node node = new Node();
        node.setName("A");

        graph.addNode(node);

        assertEquals(node,graph.getNodesMap().get("A"));
        MatcherAssert.assertThat(graph.getNodes(), contains(node));
    }
}
