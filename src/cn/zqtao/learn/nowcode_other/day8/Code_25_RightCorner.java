package cn.zqtao.learn.nowcode_other.day8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 最大点集合
 * P为给定的二维平面整数点集。定义P中某点x,如果x满足P中任意点都不在×的右上方区域内(横纵坐标都大于x) ,
 * 则称其为“最大的”。求出所有“最大的”点的集合。
 * (所有点的横坐标和纵坐标都不重复,坐标轴范围在[0, 1e9)内)如下图:实心点为满足条件的点的集合。
 *
 * 请实现代码找到集合P中的所有”最大“点的集合并输出。输入第一行输入点集的个数N,
 * 接下来N行,每行两个数字代表点的x轴和Y轴。
 * 输出输出“最大的” 点集合, 按照 轴从小到大的方式输出,每行两个数字分别代表点的x轴和Y轴。
 * @version: 1.0
 */
public class Code_25_RightCorner {

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 暴力
    public static LinkedList<Node> getRightCornerNodes1(Node[] nodes) {

        if (nodes == null || nodes.length == 0) return null;

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x - o2.x;
            }
        });

        LinkedList<Node> res = new LinkedList<>();

        int index = 0;
        for (int i = 0; i < nodes.length; i++) {
            Node a = nodes[i];
            boolean flag = true;
            for (int j = 0; j < nodes.length; j++) {
                Node b = nodes[j];
                if (b.x > a.x && b.y > a.y) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(a);
            }
        }
        return res;
    }

    // O(N)
    public static LinkedList<Node> getRightCornerNodes2(Node[] nodes) {
        LinkedList<Node> res = new LinkedList<>();

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x - o2.x;
            }
        });

        res.add(nodes[nodes.length - 1]); // 最后一个点，一定满足

        int rightMaxY = nodes[nodes.length - 1].y; // 记录最高 y

        // 从右往左遍历，只要是小于maxY 的一定都不满足
        for (int i = nodes.length - 2; i >= 0; i--) {
            Node node = nodes[i];
            if (rightMaxY < node.y) {
                res.addFirst(node);
            }
            rightMaxY = Math.max(rightMaxY, nodes[i].y);
        }

        return res;
    }

    // O(N) 扩展，点的x , y 可以相同
    public static LinkedList<Node> getRightCornerNodes3(Node[] nodes) {
        LinkedList<Node> res = new LinkedList<>();

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.x != o2.x) {
                    return o1.x - o2.x; // x -> 小到大
                } else {
                    return o2.y - o1.y; // y -> 大到小
                }
            }
        });

        res.addFirst(nodes[nodes.length - 1]);
        int rightMaxY = nodes[nodes.length - 1].y;
        for (int i = nodes.length - 2; i >=0; i--) {
            if (nodes[i].y >= rightMaxY) {
                res.addFirst(nodes[i]);
            }
            rightMaxY = Math.max(rightMaxY, nodes[i].y);
        }
        return res;
    }

    public static void printLinkedList(LinkedList<Node> list) {
        for (Node node : list) {
            System.out.print("(" + node.x + "," + node.y + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int N = in.nextInt();

            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new Node(in.nextInt(), in.nextInt());
            }

            printLinkedList(getRightCornerNodes1(nodes));
            printLinkedList(getRightCornerNodes2(nodes));
            printLinkedList(getRightCornerNodes3(nodes));
        }
    }
}
