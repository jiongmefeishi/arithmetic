package cn.zqtao.leetcode.day1;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class LeetCode_05_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand; // 随机指针

        public Node(int data) {
            this.value = data;
        }
    }
    public static Node copy(Node head) {
        if (head == null) return head;

        Node next = null;
        Node cur = head;

        while (cur != null) { // copy 直链
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;

        while (cur != null) { // copy random
            next = cur.next.next;
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

        // split the list

        cur = head;
        Node copyH = head.next;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next; // 重置原链
            curCopy.next = next == null ? null : next.next; // 重置新链
            cur = next;
        }
        return copyH;
    }
}
