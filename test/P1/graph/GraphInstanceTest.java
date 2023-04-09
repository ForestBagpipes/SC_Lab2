/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.*;

import P1.graph.Graph;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 *
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    //   TODO 测试策略
    //   add方法 测试策略：
    //   graph：1、空的graph  2、非空的graph
    //   vertex：1、加入graph中的vertex 2、未加入graph中的vertex
    //
    //   set方法 测试策略：
    //   graph：1、空的graph  2、非空的graph
    //   source：1、已经加入graph中的source 2、未加入graph中的source
    //   target：1、已经加入graph中的target 2、未加入graph中的target
    //   weight：1、weight>0 2、weight=0
    //
    //   remove方法 测试策略：
    //   vertex：1、加入graph中的vertex，且与其他结点相连
    //           2、加入graph中的vertex，且不与其他结点相连
    //           3、未加入graph中的vertex
    //
    //   vertices方法 测试策略：
    //   graph：1、空的graph  2、非空的graph
    //
    //   sources方法 测试策略：
    //   target：1、target不是graph中的结点
    //           2、target是graph中的结点，且有结点指向它
    //           3、target是graph中的结点，且没有结点指向它
    //
    //   targets方法 测试策略：
    //   source：1、source不是graph中的结点
    //           2、source是graph中的结点，且它指向了某些结点
    //           3、source是graph中的结点，且它不指向任何结点

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    private boolean setEqual(Set<String> a, Set<String> b){
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

    @Test public void testAdd(){
        Graph graph = emptyInstance();
        String s = new String();
        assertEquals(true,graph.add(s));
        assertEquals(true,graph.add("a"));
        assertEquals(true,graph.add("b"));
        assertEquals(false,graph.add("a"));
    }

    @Test public void testSet(){
        Graph graph = emptyInstance();
        assertEquals(-1,graph.set("a","b",-1));
        assertEquals(0,graph.set("a","b",5));
        assertEquals(0,graph.set("a", "c", 4));
        assertEquals(0,graph.set("b", "c", 2));
        assertEquals(5,graph.set("a","b",6));
        assertEquals(4,graph.set("a","c",0));
        assertEquals(0,graph.set("a","d",0));
        assertEquals(0,graph.set("a","c",0));
    }

    @Test public void testRemove(){
        Graph graph = emptyInstance();
        graph.set("a", "b", 5);
        graph.set("a", "c", 4);
        graph.set("b", "c", 2);
        graph.add("d");
        assertEquals(false,graph.remove("e"));
        assertEquals(true,graph.remove("d"));
        assertEquals(true,graph.remove("b"));
        assertEquals(true,graph.remove("a"));
    }

    @Test public void testVertices(){
        Graph graph = emptyInstance();
        assertEquals(true,graph.vertices().isEmpty());
        graph.set("a", "b", 5);
        graph.set("a", "c", 4);
        graph.set("b", "c", 2);
        graph.add("d");
        Set<String> g = new HashSet<>();
        g.add("a");
        g.add("b");
        g.add("c");
        g.add("d");
        assertEquals(true,setEqual(g,graph.vertices()));
    }

    @Test public void testTargets(){
        Graph graph = emptyInstance();
        graph.set("a", "b", 5);
        graph.set("a", "c", 4);
        graph.set("b", "c", 2);
        graph.add("d");
        Map<String,Integer> m =new HashMap<>();
        m.put("b",5);
        m.put("c",4);
        assertEquals(true,graph.targets("e").isEmpty());
        assertEquals(true,graph.targets("d").isEmpty());
        assertEquals(true,graph.targets("a").equals(m));
    }

    @Test public void testSources(){
        Graph graph = emptyInstance();
        graph.set("a", "b", 5);
        graph.set("a", "c", 4);
        graph.set("b", "c", 2);
        graph.add("d");
        Map<String,Integer> m =new HashMap<>();
        m.put("a",4);
        m.put("b",2);
        assertEquals(true,graph.sources("e").isEmpty());
        assertEquals(true,graph.sources("a").isEmpty());
        assertEquals(true,graph.targets("d").isEmpty());
        assertEquals(true,graph.sources("c").equals(m));
    }

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph

}
