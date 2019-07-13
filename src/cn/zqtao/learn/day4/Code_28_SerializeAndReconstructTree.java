package cn.zqtao.learn.day4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_28_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    // ===================================  {先序} ============================================

    // 通过先序遍历来序列化二叉树
    public static String serialByPre(Node head) {
        if (head == null) return "#!";

        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 通过先序遍历反序列化二叉树
    public static Node reconByPreString(String serialNodeString){
        return reconPreOrder(getQueue(serialNodeString));
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String poll = queue.poll();
        if (poll.equals("#"))
            return null;
        // 不为空，消费这个节点
        Node head = new Node(Integer.parseInt(poll));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    public static Queue<String> getQueue(String serialNodeString) {
        if (serialNodeString == null) return null;
        String[] split = serialNodeString.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < split.length; i++) {
            queue.add(split[i]);
        }
        return queue;
    }

    // ===================================  {中序} ============================================

    // 中序遍历序列化
    public static String serialByIn(Node head) {
        if (head == null) return "#!";

        String res = serialByIn(head.left);
        res += head.value + "!";
        res += serialByIn(head.right);
        return res;
    }

    public static Node reconByInString(String serialNodeString) {
        return reconByIn(getQueue(serialNodeString));
    }

    // 中序遍历反序列化
    public static Node reconByIn(Queue<String> queue) {
        String poll = queue.poll();
        if (poll.equals("#")) return null;

        Node left = reconByIn(queue);
        Node head = new Node(Integer.parseInt(queue.poll()));
        head.left = left;
        head.right = reconByIn(queue);
        return head;
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        head.left.left = new Node(4);
        head.left.right = new Node(5);

        head.right.left = new Node(6);
        head.right.right = new Node(7);
//
//        System.out.println("===========先序序列========================");
//        preOrderRecur(head);
//        System.out.println();
//        System.out.println(serialByPre(head));
//        System.out.println("反序列：");
//
//        Node node = reconByPreString(serialByPre(head));
//        preOrderRecur(node);
//        System.out.println();

        /*
1 2 4 5 3 6 7
1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!
反序列：
1 2 4 5 3 6 7
         */


        System.out.println("===========中序序列========================");
        preOrderRecur(head);
        System.out.println();
        System.out.println(serialByIn(head));
        System.out.println("反序列：");

        Node node2 = reconByInString(serialByIn(head));
        preOrderRecur(node2);
        System.out.println();


    }



    // 前序遍历递归版
    public static void preOrderRecur(Node head) {
        if (head == null)
            return;
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

}
