package cn.zqtao.learn.nowcode_base.day3;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 复制含有随机指针节点的链表
 * 【题目】 一种特殊的链表节点类描述如下：
 * public class Node {
 *      public int value;
 *      public Node next;
 *      public Node rand;
 *
 *      public Node(int data) { this.value = data; }
 * }
 * Node类中的value是节点值，
 * next指针和正常单链表中next指针的意义 一 样，都指向下一个节点，
 * rand指针是Node类中新增的指针，这个指 针可 能指向链表中的任意一个节点，也可能指向null。
 *
 * 给定一个由 Node节点类型组成的无环单链表的头节点head，
 *
 * 请实现一个 函数完成 这个链表中所有结构的复制，并返回复制的新链表的头节点。
 *
 * 进阶： 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N) 内完成原问题要实现的函数。
 * @version: 1.0
 */
public class Code_23_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand; // 随机指针

        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    public static Node copyListWithRand1(Node head) {

        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value)); // copy the cur node
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);

            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) return head;
        Node cur = head;
        Node next = null; // 每次都是指向下一次循环的头结点，即保存下一次循环的头结点位置

        // 复制每个节点，并连接成为一个新链
        while (cur != null) {
            next = cur.next; // 保留下一个头结点位置

            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next; // 重置遍历的头结点
        }

        // 拷贝random 节点
        Node curCopy = null;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

        // 分开copy 链和原链
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next == null ? null : next.next;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }

}
