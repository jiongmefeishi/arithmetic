package cn.zqtao.leetcode.day1;

import java.util.HashSet;

/**
 * @auther: zqtao
 * @description: 两个单链表相交
 * @version: 1.0
 */
public class LeetCode_02_IntersectionNode {
    // 链表数据结构
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 不借用空间
    public static Node getIntersectionNode2(Node headA, Node headB) {
        if (headA == null || headB == null) return null;

        Node curA = headA;
        Node curB = headB;

        int size = 0; // 记录链表长度

        while (curA != null) { // 遍历链表 headA 求尾节点和链表长度
            size++;
            curA = curA.next;
        }

        while (curB != null) { // 遍历链表 headB 求尾节点和链表长度
            size--;
            curB = curB.next;
        }

        if (curA != curB) return null; // 尾节点不同，一定不相交

        curA = size >=0 ? headA : headB; // 默认将最长链给curA
        curB = curA == headA ? headB : headA;

        size = Math.abs(size); // 绝对值
        while (size-- > 0){ // 长链先走 size 步
            curA = curA.next;
        }

        while (curA != curB){ // 相等时相加
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    // 借用空间
    public static Node getIntersectionNode1(Node headA, Node headB) {
        if (headA == null || headB == null) return null;

        Node cur = headA;
        HashSet<Node> set = new HashSet<>();

        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while (cur != null) {
            boolean add = set.add(cur);
            if (!add) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void printList(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.val + " --> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);


        Node head1 = new Node(8);
        head1.next = node1;
        head1.next.next = node2;
        head1.next.next.next = node3;
        head1.next.next.next.next = node4;
        head1.next.next.next.next.next = node5;

        Node head2 = new Node(7);
        head2.next = node4;
        head2.next.next = node5;

        System.out.println("原链结构");
        printList(head1);
        printList(head2);
        System.out.println(node4);
        System.out.println(getIntersectionNode2(head1, head2));

        System.out.println("寻找交点后两链结构");
        printList(head1);
        printList(head2);

    }
}
