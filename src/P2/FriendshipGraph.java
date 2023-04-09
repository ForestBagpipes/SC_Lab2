package P2;

import P1.graph.ConcreteEdgesGraph;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.HashSet;

public class FriendshipGraph {
    private final ConcreteEdgesGraph<Person> friends;
    // Abstraction function:
    //   TODO friends:表示社交网络，为一个有向带权图
    // Representation invariant:
    //   TODO friends: 网络内的Person无重名
    // Safety from rep exposure:
    //   TODO 使用private和final修饰friends，防止表示泄露

    //   TODO constructor
    public FriendshipGraph() {
        friends = new ConcreteEdgesGraph<Person>();
    }

    /**
     * 向图内添加Person顶点
     *
     * @param person 需加入的新的person
     * @return 是否添加成功
     */
    public boolean addVertex(Person person){
        //如果加入的Person和图中的某个Person重名
        if(friends.vertices().contains(person.getName())){
            System.out.println("加入的Person重名");
            return false;
        }
        friends.add(person);
        return true;
    }

    /**
     * 在输入的Person A 和 Person B 之间在途中添加边
     *
     * @param A 需要加边的Person类顶点
     * @param B 需要加边的Person类顶点
     * @return 返回是否添加成功
     */
    public boolean addEdge(Person A, Person B) {
        if(friends.set(A,B,1) >= 0){
            return true;
        }
        return false;
    }

    /**
     * 得到社交网络图
     *
     * @return 返回一个ConcreteEdgesGraph<Person>类的图
     */
    public ConcreteEdgesGraph<Person> getFriend() {
        ConcreteEdgesGraph<Person> f = friends;
        return f;
    }

    /**
     *  得到从p1到p2的在社交网络上的最短距离
     *
     * @param p1 起始的Person类结点
     * @param p2 终结的Person类结点
     * @return 从起始结点到终结结点的最短距离
     */
    public int getDistance(Person p1, Person p2) {
        if (!friends.vertices().contains(p1)) {
            System.out.println("该人物" + p1 + "还未加入社交网络");
            System.exit(0);
            return -1;
        }
        if (!friends.vertices().contains(p2)) {
            System.out.println("该人物" + p2 + "还未加入社交网络");
            System.exit(0);
            return -1;
        }
        int distance = 0; //距离
        HashSet<Person> visited = new HashSet<>(); //记录访问过的顶点
        HashMap<Integer, HashSet<Person>> map = new HashMap<>(); //记录距离p1一定距离的顶点集
        map.put(0, new HashSet<Person>());
        map.get(0).add(p1);
        do {
            if (map.get(distance).contains(p2)) {
                return distance;
            } else {
                visited.addAll(map.get(distance));
                map.put(distance + 1, new HashSet<Person>());
                for (Person i : map.get(distance)) {
                    if (!friends.targets(i).isEmpty()) {
                        for (Person p : friends.targets(i).keySet()) {
                            if (!visited.contains(p)) {
                                map.get(distance + 1).add(p);
                            }
                        }
                    }
                }
            }
            distance++;
        } while (map.get(distance).size() != 0);
        return -1;
    }

}