package P2;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipGraphTest {
    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
//should print 1
        System.out.println(graph.getDistance(rachel, ben));
//should print 2
        System.out.println(graph.getDistance(rachel, rachel));
//should print 0
        System.out.println(graph.getDistance(rachel, kramer));
//should print -1
    }

    // TODO Testing strategy
    //   addVertex()：1、点在关系网之中已经存在；2、点在关系网中不存在
    //   addEdge()：1、要加边的点在关系网之中已经存在；2、要加边的点在关系网中不存在
    //   getDistance()：设计测试用例检测人与人之间的距离与期望是否相等。

    @Test
    public void addVertex() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        graph.addVertex(rachel);
        assertEquals(true,graph.getFriend().vertices().contains(rachel));
        assertEquals(false,graph.getFriend().vertices().contains(ross));
    }

    @Test
    public void addEdge() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        assertEquals(true,graph.getFriend().sources(rachel).containsKey(ross));
        assertEquals(true,graph.getFriend().sources(ross).containsKey(rachel));
        assertEquals(false,graph.getFriend().sources(ross).containsKey(kramer));
    }

    @Test
    public void addEdgeTest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        assertEquals(true,graph.addEdge(rachel, ross));
        assertEquals(true,graph.addEdge(ross, rachel));
        assertEquals(true,graph.addEdge(ross, ben));
        assertEquals(true,graph.addEdge(ben, ross));
    }
    @Test
    public void getDistance() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        assertEquals(1,graph.getDistance(rachel, ross));
        assertEquals(2,graph.getDistance(rachel, ben));
        assertEquals(-1,graph.getDistance(ben,  kramer));
        assertEquals(0,graph.getDistance(rachel, rachel));
    }
}