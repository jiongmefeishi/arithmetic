package cn.zqtao.learn.nowcode_base.day8.graph;

/**
 * @auther: zqtao
 * @description: 图生成器
 * @version: 1.0
 */
public class GraphGenerator {
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) { // 一位数组代表的是点和点直接的具体数据
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];

            if (!graph.nodes.containsKey(from)) { // from 点不存在
                graph.nodes.put(from, new Node(from));
            }

            if (!graph.nodes.containsKey(to)) { // to 点不存在
                graph.nodes.put(to, new Node(to));
            }

            // 取出from点和to点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            // 构建from 点和to 点之间的边， 建立两点之间的联系
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode); // 建立from点和to点的关联
            fromNode.out++; // 更新from点的出度
            toNode.in++; // 更新to点的入度

            fromNode.edges.add(newEdge); // from 点新增边
            graph.edges.add(newEdge); // 整个图新增边
        }

        return graph;
    }
}
