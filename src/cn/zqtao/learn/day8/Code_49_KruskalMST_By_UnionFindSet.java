package cn.zqtao.learn.day8;

import cn.zqtao.learn.day8.graph.Edge;
import cn.zqtao.learn.day8.graph.Graph;
import cn.zqtao.learn.day8.graph.GraphGenerator;
import cn.zqtao.learn.day8.graph.Node;

import java.util.*;

/**
 * @auther: zqtao
 * @description: 并查集解决图的 kruskal 算法
 * @version: 1.0
 */
public class Code_49_KruskalMST_By_UnionFindSet {

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        // make sets
        public void makeSets(Collection<Node> nodes) {
            this.fatherMap.clear();
            this.sizeMap.clear();

            for (Node node : nodes) {
                this.fatherMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        // 是否是同一个个集合
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        // 查找代表节点（父节点等于子节点），同时对结构进行扁平化
        public Node findHead(Node node) {
            Node fatherNode = this.fatherMap.get(node);
            if (fatherNode != node) { // 不是代表节点，继续递归
                fatherNode = findHead(fatherNode);
            }

            this.fatherMap.put(node, fatherNode);// 扁平化处理
            return fatherNode;
        }

        // 合并两个节点所在的集合
        public void union(Node a, Node b) {
            Node headA = findHead(a);
            Node headB = findHead(b);

            if (headA != headB) {
                Integer sizeA = this.sizeMap.get(headA);
                Integer sizeB = this.sizeMap.get(headB);

                if (sizeA >= sizeB) { // 短链挂长链
                    this.fatherMap.put(headB, headA);
                    this.sizeMap.put(headA, sizeA + sizeB);
                } else {
                    this.fatherMap.put(headA, headB);
                    this.sizeMap.put(headB, sizeA + sizeB);
                }
            }
        }
    }

    // 边，小根堆比较器
    public static class MinEdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // kruskal 算法求最小生成树
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFindSet unionFindSet = new UnionFindSet();
        unionFindSet.makeSets(graph.nodes.values()); // 所有节点加入并查集

        // 小根堆
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(new MinEdgeComparator());

        for (Edge edge : graph.edges) { // 所有边，入小根堆
            edgePriorityQueue.add(edge);
        }

        Set<Edge> res = new HashSet<>();
        while (!edgePriorityQueue.isEmpty()) {
            Edge minEdge = edgePriorityQueue.poll();

            if (!unionFindSet.isSameSet(minEdge.from, minEdge.to)) { // 不是同一个集合，即没有形成环路
                res.add(minEdge);
                unionFindSet.union(minEdge.from, minEdge.to);
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

        Set<Edge> kruskalMST = kruskalMST(graph);
        Iterator<Edge> iterator = kruskalMST.iterator();
        while (iterator.hasNext()) {
            Edge next = iterator.next();
            System.out.println("from : " + next.from.value + "  to : " + next.to.value + "  weight : " + next.weight);
        }
    }
}
