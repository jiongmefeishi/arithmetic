package cn.zqtao.examintion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1、层次遍历重建二叉树
 * 2、寻找二叉树的最近祖先
 *
 * 寻祖问宗
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 姓氏是人的符号标志，是家族血脉的传承；族谱是家族血脉传承的文字记载。同姓的两个中国人，根据族谱或许能够查出上面几代内是同一个祖先。
 * 查一下族谱，也许当代某位同姓名人就是你的远房亲戚，惊喜不惊喜，意外不意外！！！
 *
 * 输入
 * 二元查找树（
 * 1.若左子树不空，左子树值都小于父节点；
 * 2.如右子树不空，右子树值都大于父节点；
 * 3.左、右子树都是二元查找树；
 * 4. 没有键值相等的节点）上任意两个节点的值，请找出它们最近的公共祖先。
 * 输入三行行，第一行为树层级，第二行为数节点（其中-1表示为空节点），第
 * 三行为需要查找祖先的两个数。
 * 在例图中（虚线框没有真实节点，为了输入方便对应位置输-1）查找12和20的最近公共祖先输入为：
 * 4
 * 9 6 15 2 -1 12 25 -1 -1 -1 -1 -1 -1 20 37
 * 12 20
 * 输出
 * 输出给出两个数在树上的最近公共祖先数值，如果没有公共祖先，输出-1；如果其中一个节点是另一个节点的祖先，
 * 输出这个祖先点（如例图中找15、20最近公共祖先，输出15）；如果输入无效，输出-1。
 *
 * 样例输入
 * 4
 * 9 6 15 2 -1 12 25 -1 -1 -1 -1 -1 -1 20 37
 * 12 20
 * 样例输出
 * 15
 */
public class Code_05_RebuildBTreeByLevel_And_FindLowestCommonAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 根据层次遍历重建二叉树
     */
    public static Node creatBinTree(int[] arr) {
        LinkedList<Node> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i] == -1 ? null : new Node(arr[i]));
        }
        Node head = list.get(0);
        for (int i = 0; i < arr.length / 2 - 1; i++) {
            if (list.get(i) != null) {
                list.get(i).left = list.get(i * 2 + 1);
                list.get(i).right = list.get(i * 2 + 2);
            }
        }
        int i = arr.length / 2 - 1;
        list.get(i).left = list.get(i * 2 + 1);
        if (arr.length % 2 == 1) {
            list.get(i).right = list.get(i * 2 + 2);
        }
        return head;
    }

    /**
     * 层次遍历二叉树
     * 队列实现
     */
    public static void levelTraverse(Node node) {
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.print(tmp.value + " ");
            if (tmp.left != null) queue.add(tmp.left);
            if (tmp.right != null) queue.add(tmp.right);
        }
    }

    /**
     * 直观打印二叉树
     */
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) return;
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

    /**
     * 二叉树寻找最近祖先
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head.value == o1.value || head.value == o2.value)
            return head;
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null)
            return head;
        return left != null ? left : right;
    }

    public static void main(String[] args) {

        // for test ：层次遍历重建二叉树
        int[] arr = {9, 6, 15, 2, -1, 12, 25, -1, -1, -1, -1, -1, -1, 20, 37};
        Node node = creatBinTree(arr);
        printTree(node);
        levelTraverse(node);
        System.out.println();

        // for test : 二叉树寻找最近祖先
        int[] arr2 = {1,2,3,4,5,6,7};
        Node node2 = creatBinTree(arr2);
        System.out.println(node2.value);
        printTree(node2);
        Node node1 = lowestAncestor(creatBinTree(arr2), new Node(4), new Node(6));
        System.out.println(node1.value);
    }
}