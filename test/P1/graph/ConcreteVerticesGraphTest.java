/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends P1.graph.GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    //判断两个HashSet是否相等
    private boolean setEqual(Set<String> a,Set<String> b){
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null || a.size() != b.size()
                || a.size() == 0 || b.size() == 0) {
            return false;
        }

        Iterator ite1 = a.iterator();
        Iterator ite2 = b.iterator();

        boolean flag = true;

        while (ite2.hasNext()) {
            if (!a.contains(ite2.next())) {
                flag = false;
            }
        }

        return flag;
    }
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO 按照图为空，图不为空两种情况进行测试，用笛卡尔积的方式尽量覆盖所有情况
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test public void testToString() {
        Graph graph = emptyInstance();
        assertEquals("vertices:" + "\r\n",graph.toString());
        graph.set("a", "b", 5);
        assertEquals("vertices:" + "\r\n"+"name：" + "a"+ "\r\n"+"Source: 以该点为起始的（终结点，权值）集合" + "\r\n"+"(" + "b" + "," + 5 + ")" + "\r\n"+"Target: 以该点为终结的（起始点，权值）集合" + "\r\n"+ "\r\n"+"name：" + "b"+ "\r\n"+"Source: 以该点为起始的（终结点，权值）集合" + "\r\n"+"Target: 以该点为终结的（起始点，权值）集合" + "\r\n"+"(" + "a" + "," + 5 + ")" + "\r\n"+ "\r\n", graph.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO Vertex类测试策略：
    //    getName(): 返回值为String类型的点名称
    //    getSource(): 返回值为String类型的起始结点
    //    getTarget(): 返回值为String类型的终结结点
    //    getWeight(): 返回值为Int类型的权值
    //    remove(): 返回是否正确删除
    //    put(): 返回是否正确添加
    //    toString(): 返回值为指定格式的字符串
    
    // TODO tests for operations of Vertex
    @Test public void testVertexGetTarget(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        v.put(null,"b",2);
        Map<String, Integer> m = new HashMap<>();
        m.put("b",2);
        assertEquals(m,v.getTarget());
    }

    @Test public void testVertexGetSource(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        v.put("b",null,2);
        Map<String, Integer> m = new HashMap<>();
        m.put("b",2);
        assertEquals(m,v.getSource());
    }

    @Test public void testVertexGetName(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        assertEquals("a",v.getName());
    }

    @Test public void testVertexGetWeight(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        v.put("b",null,2);
        v.put("c",null,3);
        v.put(null,"b",2);
        assertEquals(0,v.getWeight(null,null));
        assertEquals(0,v.getWeight("a","b"));
        assertEquals(2,v.getWeight("b",null));
        assertEquals(2,v.getWeight(null,"b"));
        assertEquals(0,v.getWeight("a",null));
    }

    @Test public  void testVertexRemove(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        v.put("b",null,2);
        v.put("c",null,3);
        v.put(null,"b",2);
        assertEquals(false,v.remove("d",null));
        assertEquals(false,v.remove(null,"c"));
        assertEquals(true,v.remove("b",null));
    }

    @Test public  void testVertexPut(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        assertEquals(false,v.put("a",null,5));
        assertEquals(false,v.put(null,"a",5));
        assertEquals(false,v.put("b",null,-1));
        assertEquals(false,v.put(null,null,2));
        assertEquals(false,v.put("b","c",2));
        assertEquals(true,v.put("b",null,2));
        assertEquals(true,v.put(null,"b",2));
    }

    @Test public  void testVertexToString(){
        Vertex v = new Vertex("a",new HashMap<>(),new HashMap<>());
        assertEquals("name：" + "a" + "\r\n"+"Source: 以该点为起始的（终结点，权值）集合" + "\r\n"+"Target: 以该点为终结的（起始点，权值）集合" + "\r\n",v.toString());
        v.put("b",null,2);
        assertEquals("name：" + "a" + "\r\n"+"Source: 以该点为起始的（终结点，权值）集合" + "\r\n"+"Target: 以该点为终结的（起始点，权值）集合" + "\r\n"+"(" + "b" + "," + 2+ ")" + "\r\n",v.toString());
    }
}
