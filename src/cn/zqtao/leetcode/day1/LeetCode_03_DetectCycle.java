package cn.zqtao.leetcode.day1;

/**
 * @auther: zqtao
 * @description: 链表是否有环
 * @version: 1.0
 */
public class LeetCode_03_DetectCycle {
    // 链表数据结构
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node detectCycle(Node head) {
        if (head == null || head.next == null) {
            return null; // 单独一个节点无环
        }

        if (head.next.next == head) {
            return head; // 两个节点成环状态
        }

        // 以下处理 >= 3个节点链表
        Node slow = head.next;
        Node fast = head.next.next;

        while (fast != slow) { // 相遇时停止，相遇可以证明有环
            if (fast.next == null || fast.next.next == null) {
                return null;// 保证fast可以走两步，同时不会到达尾节点，不然无环
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head; // 快慢相遇，快指针指向头结点，此时跨步和慢指针一样
        while (fast != slow) { // 第二次相遇，即环节点
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->4...
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        System.out.println("环节点地址: " + head1.next.next.next);
        System.out.println(detectCycle(head1));
    }
}
