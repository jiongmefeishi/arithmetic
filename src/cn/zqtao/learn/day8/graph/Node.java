package cn.zqtao.learn.day8.graph;

import java.util.ArrayList;

/**
 * @auther: zqtao
 * @description: 图点,所有的考虑都是把当前节点作为 from
 * @version: 1.0
 */
public class Node {
    public int value; // 当前节点值
    public int in; // 入度：有多少个节点指向我（当前节点）
    public int out; // 出度：我指向多少个节点  from  --->  to
    public ArrayList<Node> nexts; // 我作为from，从我出发能到达的下一级的邻接点的集合。简单说：我的所有邻居点
    public ArrayList<Edge> edges; // 我作为from，从我出发能发散的边的集合。简单说：我的所有邻居边

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
