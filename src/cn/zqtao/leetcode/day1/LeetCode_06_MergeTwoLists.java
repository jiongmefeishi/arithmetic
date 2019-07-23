package cn.zqtao.leetcode.day1;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class LeetCode_06_MergeTwoLists {
    // 链表数据结构
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node mergeTwoLists(Node headA, Node headB) {
        Node curA = headA;
        Node curB = headB;

        Node res = new Node(0); // 临时头节点
        Node merge = res;

        while (curA != null && curB != null) {
            if (curA.val <= curB.val) {
                merge.next = curA;
                curA = curA.next;
            } else {
                merge.next = curB;
                curB = curB.next;
            }
            merge = merge.next;
        }

        if (curA != null && curB == null){
            merge.next = curA;
        }

        if (curA == null && curB != null){
            merge.next = curB;
        }
        return res.next;
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        Node h2 = new Node(2);
        h2.next = new Node(4);

        printList(head);
        printList(h2);
        Node node = mergeTwoLists(head, h2);

        printList(head);
        printList(h2);

        printList(node);



    }
}
