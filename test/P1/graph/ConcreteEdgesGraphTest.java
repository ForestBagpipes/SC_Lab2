/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Tests for ConcreteEdgesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends P1.graph.GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    //判断两个HashSet是否相等
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO 按照图为空，图不为空两种情况进行测试，用笛卡尔积的方式尽量覆盖所有情况

    // TODO tests for ConcreteEdgesGraph.toString()
    @Test public void testToString() {
        Graph graph = emptyInstance();
        assertEquals("Vertices:" + "\r\n" + "Edges:" + "\r\n", graph.toString());
        graph.set("a", "b", 5);
        graph.set("a", "c", 4);
        graph.set("b", "c", 2);
        assertEquals("Vertices:" + "\r\n"
                + "a" + "\r\n" + "b" + "\r\n" + "c" + "\r\n"
                + "Edges:" + "\r\n"
                + "a" + "->" + "b" + "权值为：" + 5 + "\r\n"
                + "a" + "->" + "c" + "权值为：" + 4 + "\r\n"
                + "b" + "->" + "c" + "权值为：" + 2 + "\r\n", graph.toString());
    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    //   TODO Edge类测试策略：
    //    getSources(): 返回值为String类型的起始结点
    //    getTargets(): 返回值为String类型的终结结点
    //    getWeight(): 返回值为Int类型的权值
    //    toString(): 返回值为格式如：source + "->" + target + "权值为：" + weight 的字符串

    // TODO tests for operations of Edge
    @Test public void testEdgeGetSources(){
        Edge e = new Edge("a","b",5);
        assertEquals("a",e.getSource());
    }

    @Test public void testEdgeGetTargets(){
        Edge e = new Edge("a","b",5);
        assertEquals("b",e.getTarget());
    }

    @Test public void testEdgeGetWeight(){
        Edge e = new Edge("a","b",5);
        assertEquals(5,e.getWeight());
    }

    @Test public void testEdgeToString(){
        Edge e = new Edge("a","b",5);
        assertEquals("a" + "->" + "b" + "权值为：" + 5,e.toString());
    }
}
