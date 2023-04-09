/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

import P1.graph.Graph;
import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testGraphLong(){
        Graph<Long> graph = Graph.empty();
        long a = 1,b = 2,c = 3,d = 4;
        assertEquals(true,graph.add(a));
        assertEquals(true,graph.add(b));
        assertEquals(true,graph.add(c));
        assertEquals(0,graph.set(a,b,1));
        assertEquals(1,graph.set(a,b,2));
        assertEquals(0,graph.set(b,c,3));
        assertEquals(0,graph.set(c,d,4));
    }

    @Test
    public void testGraphDouble(){
        Graph<Double> graph = Graph.empty();
        double a = 1.1,b = 2.1,c = 3.1,d = 4.1;
        assertEquals(true,graph.add(a));
        assertEquals(true,graph.add(b));
        assertEquals(true,graph.add(c));
        assertEquals(0,graph.set(a,b,1));
        assertEquals(1,graph.set(a,b,2));
        assertEquals(0,graph.set(b,c,3));
        assertEquals(0,graph.set(c,d,4));
    }
}
