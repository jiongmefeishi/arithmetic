package cn.zqtao.learn.nowcode_other.day3;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description: 并查集应用-二叉树任意两个节点寻找最近祖先
 * @version: 1.0
 */
public class Code_08_TarjanAndUnionSetsForQueryTreeNodeAncestorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Query {
        public Node o1;
        public Node o2;

        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    // 主函数
    public static Node[] tarJanQuery(Node head, Query[] quries) {
        Node[] ans = new Tarjan().query(head, quries);
        return ans;
    }

    // Tarjan算法实现处理流程
    public static class Tarjan {
        private HashMap<Node, LinkedList<Node>> queryMap; // 所有的查询，如与4有关的查询(4,8),(4,7)
        private HashMap<Node, LinkedList<Integer>> indexMap; // 结果应该存储在返回数组中的位置，即标识是第几个查询

        // 祖先集 key: 当前并查集的代表节点 value：代表节点的祖先节点
        // 存储所有节点所在的并查集的代表节点，和对应的祖先节点
        private HashMap<Node, Node> ancestorMap;
        private UnionSets sets; // 并查集

        public Tarjan() {
            queryMap = new HashMap<>();
            indexMap = new HashMap<>();

            ancestorMap = new HashMap<>();
            sets = new UnionSets();
        }


        public Node[] query(Node head, Query[] ques) {
            Node[] ans = new Node[ques.length]; // 结果数组
            setQueries(ques, ans);

            sets.makeSets(head);

            setAnswers(head, ans);

            return ans;
        }

        // 初始化查询，将查询放入查询集合中，同时存放反向查询
        public void setQueries(Query[] queries, Node[] ans) {
            Node o1 = null; // 一个查询要查的两个点
            Node o2 = null;

            for (int i = 0; i < ans.length; i++) {
                o1 = queries[i].o1;
                o2 = queries[i].o2;

                // 排除一些可以直接给定查询答案的query
                if (o1 == o2 || o1 == null || o2 == null) {
                    ans[i] = o1 != null ? o1 : o2;
                }

                if (!queryMap.containsKey(o1)) { // 原查询初始化
                    queryMap.put(o1, new LinkedList<>());
                    indexMap.put(o1, new LinkedList<>());
                }

                if (!queryMap.containsKey(o2)) { // 反向查询初始化
                    queryMap.put(o2, new LinkedList<Node>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }

                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i); // 结果应该保存到哪个位置

                queryMap.get(o2).add(o1); // 增加一个反查询，为了方便找答案
                indexMap.get(o2).add(i); // 因为实际上都是一个查询，放在相同位置
            }
        }

        // 递归遍历每一个节点，然后查询
        private void setAnswers(Node head, Node[] ans) {
            if (head == null) return;

            // 左 右
            setAnswers(head.left, ans); // 递归左边部分
            sets.union(head.left, head); // 合并左部分和当前节点为一个并查集
            ancestorMap.put(sets.findFather(head), head); // 设置当前并查集的代表节点的祖先节点为当前节点

            setAnswers(head.right, ans); // 同理右边部分
            sets.union(head.right, head);
            ancestorMap.put(sets.findFather(head), head);
            // 主要逻辑完毕，接下来都是查询结果

            LinkedList<Node> nList = queryMap.get(head); // 取出当前节点的所有查询
            // 如：当前节点是4  和它有关的查询 (4,6) (4,9) 返回的是{6, 9}
            LinkedList<Integer> iList = indexMap.get(head);

            int index = 0;
            Node node = null;
            Node nodeFather = null;

            while (nList != null && !nList.isEmpty()) { // 当前查询集不为空
                node = nList.poll(); // 弹出的是6 --> (4,6) 查询
                index = iList.poll();

                nodeFather = sets.fatherMap.get(node);// 找到 6 所在的并查集代表节点
                if (ancestorMap.containsKey(nodeFather)) {
                    // 祖先集中是否存在代表节点的祖先节点
                    // 这里可以解释前面为什么要添加反向查询，因为有些查询在查询
                    ans[index] = ancestorMap.get(nodeFather);
                }
            }
        }
    }

    // 实现Tarjan类中使用的并查集结构
    public static class UnionSets {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> rankMap;

        public UnionSets() {
            fatherMap = new HashMap<>(); // (B,A)
            rankMap = new HashMap<>();
        }

        public void makeSets(Node head) {
            fatherMap.clear();
            rankMap.clear();
            preOrderMake(head);
        }

        private void preOrderMake(Node head) {
            if (head == null) {
                return;
            }
            fatherMap.put(head, head);
            rankMap.put(head, 0);
            preOrderMake(head.left);
            preOrderMake(head.right);
        }

        public Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank < bFrank) {
                    fatherMap.put(aFather, bFather);
                } else if (aFrank > bFrank) {
                    fatherMap.put(bFather, aFather);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + 1);
                }
            }
        }
    }

    public static class ForTest {
        // for test -- print tree
        public static void printTree(Node head) {
            System.out.println("Binary Tree:");
            printInOrder(head, 0, "H", 17);
            System.out.println();
        }

        public static void printInOrder(Node head, int height, String to, int len) {
            if (head == null) {
                return;
            }
            printInOrder(head.right, height + 1, "v", len);
            String val = to + head.value + to;
            int lenM = val.length();
            int lenL = (len - lenM) / 2;
            int lenR = len - lenM - lenL;
            val = getSpace(lenL) + val + getSpace(lenR);
            System.out.println(getSpace(height * len) + val);
            printInOrder(head.left, height + 1, "^", len);
        }

        public static String getSpace(int num) {
            String space = " ";
            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < num; i++) {
                buf.append(space);
            }
            return buf.toString();
        }

        public static void main(String[] args) {
            Node head = new Node(1);
            head.left = new Node(2);
            head.right = new Node(3);
            head.left.left = new Node(4);
            head.left.right = new Node(5);
            head.right.left = new Node(6);
            head.right.right = new Node(7);
            head.right.right.left = new Node(8);
            printTree(head);
            System.out.println("===============");

            // 生成查询数组
            Query[] qs = new Query[7];
            qs[0] = new Query(head.left.right, head.right.left);
            qs[1] = new Query(head.left.left, head.left);
            qs[2] = new Query(head.right.left, head.right.right.left);
            qs[3] = new Query(head.left.left, head.right.right);
            qs[4] = new Query(head.right.right, head.right.right.left);
            qs[5] = new Query(head, head);
            qs[6] = new Query(head.left, head.right.right.left);

            // Tarjan算法结合并查集解决所有查询问题
            Node[] ans = tarJanQuery(head, qs);

            // 打印答案
            for (int i = 0; i != ans.length; i++) {
                System.out.println("o1 : " + qs[i].o1.value);
                System.out.println("o2 : " + qs[i].o2.value);
                System.out.println("ancestor : " + ans[i].value);
                System.out.println("===============");
            }

        }
    }
}
