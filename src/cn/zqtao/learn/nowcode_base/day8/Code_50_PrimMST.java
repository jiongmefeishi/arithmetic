package cn.zqtao.learn.nowcode_base.day8;

import cn.zqtao.learn.nowcode_base.day8.graph.Edge;
import cn.zqtao.learn.nowcode_base.day8.graph.Graph;
import cn.zqtao.learn.nowcode_base.day8.graph.GraphGenerator;
import cn.zqtao.learn.nowcode_base.day8.graph.Node;

import java.util.*;

/**
 * @auther: zqtao
 * @description: 图最小生成树：prim算法
 * @version: 1.0
 */
public class Code_50_PrimMST {

    // 最小权重边，小根堆比较器
    public static class MinEdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static Set<Edge> primMST(Graph graph){

        HashSet<Node> set = new HashSet<>(); // 注册节点：哪些节点已经选取过
        PriorityQueue<Edge> queue = new PriorityQueue<>(new MinEdgeComparator()); // 小根堆，每次弹出可选边中最小权重值的边

        Set<Edge> res = new HashSet<>();
        // 任意选取一点  node --> v1
        Node randNode = graph.nodes.get(1); // 这里为了方便选取 节点值为 1 的点
        if (!set.contains(randNode)){
            set.add(randNode);
            for(Edge edge: randNode.edges) { // 解锁该节点的所有可选临边
                queue.add(edge);
            }

            while (!queue.isEmpty()) {
                Edge minEdge = queue.poll();
                Node to = minEdge.to;
                if (!set.contains(to)) {
                    set.add(to);
                    res.add(minEdge);

                    for (Edge nextEdge: to.edges) {
                        queue.add(nextEdge);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[][] graphMatrix = {
                {1,2,6},
                {1,3,1},
                {1,4,5},

                {2,1,6},
                {2,3,5},
                {2,5,3},

                {3,1,1},
                {3,2,5},
                {3,4,5},
                {3,5,6},
                {3,6,4},

                {4,1,5},
                {4,3,5},
                {4,6,2},

                {5,2,3},
                {5,3,6},
                {5,6,6},

                {6,3,4},
                {6,4,2},
                {6,5,6},
        };

        Graph graph = GraphGenerator.createGraph(graphMatrix);

        Set<Edge> primMST = primMST(graph);
        Iterator<Edge> iterator = primMST.iterator();
        while (iterator.hasNext()) {
            Edge next = iterator.next();
            System.out.println("from : " + next.from.value + "  to : " + next.to.value + "  weight : " + next.weight);
        }
    }
}
