package cn.zqtao.learn.nowcode_base.day8;

import cn.zqtao.learn.nowcode_base.day8.graph.Graph;
import cn.zqtao.learn.nowcode_base.day8.graph.GraphGenerator;
import cn.zqtao.learn.nowcode_base.day8.graph.Node;

import java.util.*;

/**
 * @auther: zqtao
 * @description: 图的常用排序算法：拓扑排序
 * @version: 1.0
 */
public class Code_48_GraphTopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>(); // 记录点和点的入度
        Queue<Node> zeroInQueue = new LinkedList<>(); // 0 入度队列，弹出顺序就是拓扑排序的顺序

        for (Node node : graph.nodes.values()) { // 取出所有的节点
            inMap.put(node, node.in); // key : node  value: in
            if (node.in == 0) {
                zeroInQueue.add(node);// node的入度为0，如队列
            }
        }

        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node zeroInNode = zeroInQueue.poll(); // 弹出队列中入度为0的节点
            res.add(zeroInNode);
            for (Node next : zeroInNode.nexts) { // 遍历它所有的邻接节点
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) { // 当邻接节点入度为0时，如队
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // {from, to, weight}
        Integer[][] matrix = {
                {2, 1, 3},
                {3, 1, 4},
                {4, 1, 2},
                {3, 2, 1},
                {5, 2, 5},
                {5, 4, 1}
        };

        Graph graph = GraphGenerator.createGraph(matrix);
        List<Node> nodes = sortedTopology(graph);
        for(Node n: nodes)
            System.out.println(n.value);
        // 3 5 2 4 1
    }
}
