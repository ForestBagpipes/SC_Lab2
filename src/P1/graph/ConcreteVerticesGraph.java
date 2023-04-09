/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements P1.graph.Graph<L> {

    private final List<Vertex> vertices = new ArrayList<>();
    // Abstraction function:
    //   TODO vertices: 图的点的集合
    // Representation invariant:
    //   TODO vertices中的每一个vertex类元素，都要满足：
    //       name： 非空（长度不为0）且不为null
    //       Source：集合的任意一个键与该点的名称不相等，且值均为大于0的整数
    //       Target：集合的任意一个键与该点的名称不相等，且值均为大于0的整数
    // Safety from rep exposure:
    //   TODO 成员变量vertices用 private和final限定修饰
    //      sources和targets等方法采用防御式拷贝，从而防止外界随意改动类内的值，有效防止表示泄露

    // TODO constructor
    public ConcreteVerticesGraph() {

    }

    // TODO checkRep
    private void checkRep() {
        for (Vertex<L> v : vertices) {
            assert v.getName() != null : "Vertex 的 name 应该不为null";
            assert !v.getSource().containsKey(v.getName()) : "Vertex 的 Source 任意一个键与该点的name不相等";
            assert !v.getTarget().containsKey(v.getName()) : "Vertex 的 Target 任意一个键与该点的name不相等";
            Set<Map.Entry<L, Integer>> entrySet = v.getSource().entrySet();
            Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
            while (a.hasNext()) {
                Map.Entry<L, Integer> it = a.next();
                assert it.getValue() > 0 : "Source的值应该均为大于0的整数";
            }
            Set<Map.Entry<L, Integer>> entryset = v.getTarget().entrySet();
            Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
            while (b.hasNext()) {
                Map.Entry<L, Integer> it = b.next();
                assert it.getValue() > 0 : "Target的值应该均为大于0的整数";
            }
        }
    }

    @Override
    public boolean add(L vertex) {
        Map<L, Integer> s = new HashMap<>();
        Map<L, Integer> t = new HashMap<>();
        Vertex<L> v = new Vertex<L>(vertex, s, t);
        if (vertex == null) {
            System.out.println("add失败，该结点为null");
            return false;
        }
        for (Vertex<L> a : vertices) {
            if (a.getName().equals(vertex)) {
                System.out.println("add失败，结点已经存在");
                return false;
            }
        }
        vertices.add(v);
        checkRep();
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        int f1 = 0;
        int f2 = 0;
        int w = 0; //保存之前的权值
        if (source == null || target == null) {
            System.out.println("输入不合法");
            return 0;
        }
        if (weight < 0) {
            System.out.println("输入不合法");
            return -1;
        }
        for (Vertex<L> a : vertices) {
            if (a.getName().equals(source)) {
                f1 = 1;
                break;
            }
        }
        if (f1 == 0) {
            this.add(source);
            checkRep();
        }
        for (Vertex<L> a : vertices) {
            if (a.getName().equals(target)) {
                f2 = 1;
                break;
            }
        }
        if (f2 == 0) {
            this.add(target);
            checkRep();
        }
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(source)) {
                w = v.getWeight(null, target);
                v.put(null, target, weight);
                checkRep();
            }
            if (v.getName().equals(target)) {
                v.put(source, null, weight);
                checkRep();
            }
        }
        return w;
    }

    @Override
    public boolean remove(L vertex) {
        int flag = 0;
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(vertex)) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("点在图中不存在");
            return false;
        }
        Iterator<Vertex> vs = vertices.iterator();
        while (vs.hasNext()) {
            Vertex<L> v = vs.next();
            if (v.getName().equals(vertex)) {
                vs.remove();
                checkRep();
            }
        }
        for (Vertex<L> v : vertices) {
            v.put(null, vertex, 0);
            v.put(vertex, null, 0);
        }
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        Set<L> str = new HashSet<>();
        for (Vertex<L> v : vertices) {
            str.add(v.getName());
        }
        checkRep();
        return str;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> m = new HashMap<>();
        int flag = 0;
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(target)) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("点在图中不存在");
            return m;
        }
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(target)) {
                m = v.getSource();
            }
        }
        checkRep();
        return m;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> m = new HashMap<>();
        int flag = 0;
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(source)) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("点在图中不存在");
            return m;
        }
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(source)) {
                m = v.getTarget();
            }
        }
        checkRep();
        return m;
    }

    // TODO toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("vertices:" + "\r\n");
        for (Vertex<L> v : vertices) {
            sb.append(v.toString() + "\r\n");
        }
        checkRep();
        return sb.toString();
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    // TODO fields
    private final L name;
    private final Map<L, Integer> Source;  //以该点为起始的（终结点，权值）集合
    private final Map<L, Integer> Target; //以该点为终结的（起始点，权值）集合
    // Abstraction function:
    //   TODO 包含点的名字，以该点为起始的（终结点，权值）集合，和以该点为终结的（起始点，权值）集合
    // Representation invariant:
    //   TODO name： 非空（长度不为0）且不为null
    //    Source：集合的任意一个键与该点的名称不相等，且值均为大于0的整数
    //    Target：集合的任意一个键与该点的名称不相等，且值均为大于0的整数
    // Safety from rep exposure:
    //   TODO name，Source和Target这两个成员变量用 private和final限定修饰
    //    sources和targets等方法采用防御式拷贝，从而防止外界随意改动类内的值，有效防止表示泄露

    // TODO constructor
    public Vertex(L name, Map<L, Integer> Source, Map<L, Integer> Target) {
        this.name = name;
        this.Source = Source;
        this.Target = Target;
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        assert name != null : "Vertex 的 name 应该不为null";
        assert !Source.containsKey(name) : "Vertex 的 Source 任意一个键与该点的name不相等";
        assert !Target.containsKey(name) : "Vertex 的 Target 任意一个键与该点的name不相等";
        Set<Map.Entry<L, Integer>> entrySet = Source.entrySet();
        Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
        while (a.hasNext()) {
            Map.Entry<L, Integer> it = a.next();
            assert it.getValue() > 0 : "Source的值应该均为大于0的整数";
        }
        Set<Map.Entry<L, Integer>> entryset = Target.entrySet();
        Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
        while (b.hasNext()) {
            Map.Entry<L, Integer> it = b.next();
            assert it.getValue() > 0 : "Target的值应该均为大于0的整数";
        }
    }

    // TODO methods
    public L getName() {
        return name;
    }

    public Map<L, Integer> getSource() {
        checkRep();
        Map<L, Integer> s = new HashMap<>();
        Set<Map.Entry<L, Integer>> entrySet = Target.entrySet();
        Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
        while (a.hasNext()) {
            Map.Entry<L, Integer> it = a.next();
            s.put(it.getKey(), it.getValue());
        }
        checkRep();
        return s;
    }

    public Map<L, Integer> getTarget() {
        Map<L, Integer> s = new HashMap<>();
        Set<Map.Entry<L, Integer>> entryset = Source.entrySet();
        Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
        while (b.hasNext()) {
            Map.Entry<L, Integer> it = b.next();
            s.put(it.getKey(), it.getValue());
        }
        checkRep();
        return s;
    }

    //from非null时，to为null，表示从from点出发以该点为终结结点的边的权值；反之，表示从该结点起始，以to为终结的边的权值。找不到边或输入不合法，返回0
    public int getWeight(L from, L to) {
        if (from != null && to != null || from == null && to == null) {
            System.out.println("输入不合法");
            return 0;
        }
        if (to != null) {
            Set<Map.Entry<L, Integer>> entrySet = Source.entrySet();
            Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
            while (a.hasNext()) {
                Map.Entry<L, Integer> it = a.next();
                if (it.getKey().equals(to)) {
                    return it.getValue();
                }
            }
            return 0;
        } else if (from != null) {
            Set<Map.Entry<L, Integer>> entryset = Target.entrySet();
            Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
            while (b.hasNext()) {
                Map.Entry<L, Integer> it = b.next();
                if (it.getKey().equals(from)) {
                    return it.getValue();
                }
            }
            return 0;
        }
        return 0;
    }

    //from非null时，to为null，表示从from点出发以该点为终结结点的边；反之，表示从该结点起始，以to为终结的边。
    public boolean remove(L from, L to) {
        if (from != null && to != null || from == null && to == null) {
            System.out.println("输入不合法");
            return false;
        }
        if (to != null) {
            Set<Map.Entry<L, Integer>> entrySet = Source.entrySet();
            Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
            while (a.hasNext()) {
                Map.Entry<L, Integer> it = a.next();
                if (it.getKey().equals(to)) {
                    a.remove();
                    checkRep();
                    return true;
                }
            }
            return false;
        } else if (from != null) {
            Set<Map.Entry<L, Integer>> entryset = Target.entrySet();
            Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
            while (b.hasNext()) {
                Map.Entry<L, Integer> it = b.next();
                if (it.getKey().equals(from)) {
                    b.remove();
                    checkRep();
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean put(L from, L to, int value) {
        if (from != null && to != null || from == null && to == null || value < 0) {
            System.out.println("输入不合法");
            return false;
        }
        if (from == name || to == name) {
            System.out.println("输入不合法");
            return false;
        }
        if (value != 0) {
            if (to != null) {
                Source.put(to, value);
            } else if (from != null) {
                Target.put(from, value);
            }
            checkRep();
        }
        if (value == 0) {
            if (to != null) {
                remove(from, to);
            } else if (from != null) {
                remove(from, to);
            }
            checkRep();
        }
        return true;
    }

    // TODO toString()
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name：" + this.getName() + "\r\n");
        sb.append("Source: 以该点为起始的（终结点，权值）集合" + "\r\n");
        Set<Map.Entry<L, Integer>> entrySet = Source.entrySet();
        Iterator<Map.Entry<L, Integer>> a = entrySet.iterator();
        while (a.hasNext()) {
            Map.Entry<L, Integer> it = a.next();
            sb.append("(" + it.getKey() + "," + it.getValue() + ")" + "\r\n");
        }
        sb.append("Target: 以该点为终结的（起始点，权值）集合" + "\r\n");
        Set<Map.Entry<L, Integer>> entryset = Target.entrySet();
        Iterator<Map.Entry<L, Integer>> b = entryset.iterator();
        while (b.hasNext()) {
            Map.Entry<L, Integer> it = b.next();
            sb.append("(" + it.getKey() + "," + it.getValue() + ")" + "\r\n");
        }
        checkRep();
        return sb.toString();
    }
}
