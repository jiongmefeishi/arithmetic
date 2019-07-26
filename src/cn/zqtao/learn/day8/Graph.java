package cn.zqtao.learn.day8;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @auther: zqtao
 * @description: 图：就是所有的点集和边集
 * @version: 1.0
 */
public class Graph {

    /**
     * key: 点的编号
     * value: 实际对应的Node
     */
    public HashMap<Integer, Node> nodes; // 点集
    public HashSet<Edge> edges; // 边集

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
