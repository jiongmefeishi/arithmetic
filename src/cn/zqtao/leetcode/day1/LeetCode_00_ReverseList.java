package cn.zqtao.leetcode.day1;

/**
 * @auther: zqtao
 * @description: 单链表逆序，不借用额外空间
 * @version: 1.0
 */
public class LeetCode_00_ReverseList {

    // 链表数据结构
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node reverseList(Node head) {
        if (head == null) return null;

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next; // 备份head.next
            head.next = pre; // 更新head.next
            pre = head;
            head = next;
        }
        return pre;
    }
}
