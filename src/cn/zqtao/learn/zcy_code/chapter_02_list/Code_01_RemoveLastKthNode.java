package cn.zqtao.learn.zcy_code.chapter_02_list;

/**
 * @auther: zqtao
 * @description: 在单链表和双链表中删除倒数第 K个节点
 * @version: 1.0
 */
public class Code_01_RemoveLastKthNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 单链表移除 lastK
    public static Node removeKth(Node head,int lastK) {
        if (head == null || lastK < 1) return head;

        Node tmp = head;
        while (tmp != null) {
            lastK--;
            tmp = tmp.next;
        }
        if (lastK == 0) head = head.next;
        if (lastK < 0) {
            tmp = head;
            while (++lastK != 0) {
                tmp = tmp.next;
            }
            tmp.next = tmp.next.next;
        }
        return head;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    // 双端链表移除 lastK
    public static DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        DoubleNode cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }
        if (lastKth == 0) {
            head = head.next;
            head.pre = null;
        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if (newNext != null) {
                newNext.pre = cur;
            }
        }
        return head;
    }
}
