package cn.zqtao.learn.day8;

/**
 * @auther: zqtao
 * @description: 图边
 * @version: 1.0
 */
public class Edge {
    public int weight; // 当前边的权重
    public Node from; // 当前边起始点
    public Node to; // 当前边尾节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
