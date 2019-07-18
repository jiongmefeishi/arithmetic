package cn.zqtao.learn.day5;

import java.util.HashMap;
import java.util.List;

/**
 * @auther: zqtao
 * @description: 并查集
 * @version: 1.0
 */
public class Code_34_UnionFind {

    public static class Node {
        // whatever you like
    }

    // 并查集结构
    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap; // 存储节点的父节点
        public HashMap<Node, Integer> sizeMap; // 存储节点所在的集合的节点数size

        public UnionFindSet(List<Node> nodes) { // 并查集在初始化的时候需要一次性给定所有的数据，不能后期动态加载
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
            this.initSet(nodes);
        }

        private void initSet(List<Node> nodes) {
            // 每次初始化先清空原集合
            this.fatherMap.clear();
            this.sizeMap.clear();

            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);// 初始每一个元素，单成一个集合结构
            }
        }

        // 递归找父节点，同时扁平化
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) { // 不是代表节点
                father = fatherMap.get(father);
            }
            fatherMap.put(node, father);// 扁平化，将递归沿途找到的节点都指向代表节点
            return father;
        }

        // 两个元素是否属于同一个集合
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        // 合并a b 所在的集合
        public void union(Node a, Node b) {
            if (a == null || b == null) return;

            Node headA = findHead(a);
            Node headB = findHead(b);

            if (headA != headB) {
                int sizeA = sizeMap.get(headA);
                int sizeB = sizeMap.get(headB);
                if (sizeA <= sizeB) {
                    fatherMap.put(headA, headB);
                    sizeMap.put(headB, sizeA + sizeB);
                } else {
                    fatherMap.put(headB, headA);
                    sizeMap.put(headA, sizeA + sizeB);
                }
            }
        }
    }
}
