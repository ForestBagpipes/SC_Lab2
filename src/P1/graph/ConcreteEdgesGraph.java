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
public class ConcreteEdgesGraph<L> implements P1.graph.Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    //   TODO vertices:图的点集合；    edges：图的边的集合；   AF为用vertices和edges表示的有向图
    // Representation invariant:
    //   TODO vertices：集合内的点不能为null    edges：边的权值必须大于0，边的起始结点和终结结点不为空
    // Safety from rep exposure:
    //   TODO vertices和edges这两个成员变量用 private和final限定修饰
    //        sources和targets等方法采用防御式拷贝，从而防止外界随意改动类内的值，有效防止表示泄露

    // TODO constructor
    public ConcreteEdgesGraph() {
    }

    // TODO checkRep
    private void checkRep() {
        for (L s : vertices) {
            assert s != null : "集合内的点不能为空";
        }
        for (Edge e : edges) {
            assert e.getSource() != null : "Edge 的 source 不应该为空";
            assert e.getTarget() != null : "Edge 的 target 不应该为空";
            assert e.getWeight() > 0 : "Edge 的 weight 应该大于0";
        }
    }

    @Override
    public boolean add(L vertex) {
        if (vertex == null) {
            System.out.println("add失败，该结点为空");
            return false;
        }
        if (vertices.contains(vertex)) {
            System.out.println("add失败，结点已经存在");
            return false;
        }
        vertices.add(vertex);
        System.out.println("add成功");
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        int w = 0;
        if (weight > 0) {
            vertices.add(source);
            vertices.add(target);
            checkRep();
            for (Edge<L> e : edges) {
                //在edges中找是否有相同顶点的边，找到了就删原来的边，添加新的边，返回原来的边的权值
                if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                    w = e.getWeight();
                    edges.remove(e);
                    checkRep();
                    break;
                }
            }
            //没有找到相同顶点的边，就直接添加新的边，并且返回0
            edges.add(new Edge<L>(source, target, weight));
            checkRep();
        } else if (weight == 0) {
            if (!vertices.contains(source) || !vertices.contains(target)) {
                //若边的某个点不存在，移除失败
                System.out.println("边不存在，移除失败");
                return 0;
            }
            for (Edge<L> e : edges) {
                //找顶点相同的边，找到就删除
                if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                    w = e.getWeight();
                    edges.remove(e);
                    checkRep();
                    break;
                }
            }
        } else {
            System.out.println("输入的weight<0");
            w = -1;
        }
        return w;
    }

    @Override
    public boolean remove(L vertex) {
        if (!vertices.contains(vertex)) {
            System.out.println("点在图中不存在，移除失败");
            return false;
        } else {
            for (L v : vertices) {
                if (v.equals(vertex)) {
                    vertices.remove(vertex);
                    checkRep();
                    break;
                }
            }
            Iterator<Edge> it = edges.iterator();
            while (it.hasNext()) {
                //若边与该点相关联，移除该边
                Edge<L> e = it.next();
                if (e.getTarget().equals(vertex) || e.getSource().equals(vertex)) {
                    it.remove();
                    checkRep();
                }
            }
            return true;
        }
    }

    @Override
    public Set<L> vertices() {
        //防御式拷贝
        Set<L> v = new HashSet<>();
        Iterator<L> a = vertices.iterator();
        while (a.hasNext()) {
            L it = a.next();
            v.add(it);
        }
        checkRep();
        return v;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> s = new HashMap<>();
        for (Edge<L> e : edges) {
            if (e.getTarget().equals(target)) {
                s.put(e.getSource(), e.getWeight());
            }
        }
        checkRep();
        return s;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> s = new HashMap<>();
        for (Edge<L> e : edges) {
            if (e.getSource().equals(source)) {
                s.put(e.getTarget(), e.getWeight());
            }
        }
        checkRep();
        return s;
    }

    // TODO toString()

    /**
     * 生成字符串表示图中的所有顶点信息和边信息
     *
     * @return 先在字符串中添加vertices中的所有点信息，再添加edges中的所有边信息,返回生成的字符串并打印
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices:" + "\r\n");
        for (L v : vertices) {
            sb.append(v + "\r\n");
        }
        sb.append("Edges:" + "\r\n");
        for (Edge<L> e : edges) {
            sb.append(e.getSource() + "->" + e.getTarget() + "权值为：" + e.getWeight() + "\r\n");
        }
        String s = sb.toString();
        System.out.println(s);
        checkRep();
        return s;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {

    // TODO fields
    private final L source;
    private final L target;
    private final int weight;
    // Abstraction function:
    //   TODO 由起始结点、终结结点、边权值构成的抽象数据类型，表示一条从起始结点到终结结点的有向加权的边
    // Representation invariant:
    //   TODO source和target均不为空，且weight>0
    // Safety from rep exposure:
    //   TODO 成员变量均用 private 和 final 进行限定修饰，保证外界无法直接访问
    //   且Edge类中不设置set方法，使得外界无法改变Edge内部的属性，从而防止表示泄露

    // TODO constructor

    //有参构造
    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    // TODO checkRep

    //检验不变量
    private void checkRep() {
        assert source != null : "Edge 的 source 不应该为空";
        assert target != null : "Edge 的 target 不应该为空";
        assert weight > 0 : "Edge 的 weight 应该大于0";
    }
    // TODO methods

    //返回source的值：起始结点
    public L getSource() {
        return source;
    }

    //返回target的值：终结结点
    public L getTarget() {
        return target;
    }

    //返回weight的值：边权值
    public int getWeight() {
        int w = new Integer(weight);
        checkRep();
        return w;
    }

    // TODO toString()
    //按格式打印边
    public String toString() {
        return (this.source + "->" + this.target + "权值为：" + this.weight);
    }
}
