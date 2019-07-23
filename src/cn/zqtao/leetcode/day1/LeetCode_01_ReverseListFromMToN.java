package cn.zqtao.leetcode.day1;

/**
 * @auther: zqtao
 * @description: 将链表从位置m 到n 逆序
 * 要求: 不申请额外空间
 * @version: 1.0
 */
public class LeetCode_01_ReverseListFromMToN {

    // 链表数据结构
    public static class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node reverseListFromMToN(Node head, int m, int n){
        int len = n - m + 1; // 计算需要逆序的节点个数
        Node preHead = null; // 用来记录开始逆序节点的前驱

        Node cur = head;

        while (cur != null && --m > 0) { // 后移 m-1 个节点
            preHead = cur;
            cur = cur.next;
        }

        Node tailNode = cur; // 记录开始逆序的节点，作为逆序尾
        Node pre = null;
        while (cur != null && len > 0) { // 逆序
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            len--;
        }

        tailNode.next = cur; // 当前cur指向 n 之后的第一个节点，拼接逆序链和 当前节点，构成完整链

        if (preHead != null) {
            preHead.next = pre; // pre 指向的是逆序链的头结点，拼接逆序链和 m 之前的节点
        }
        return head;
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
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        System.out.println("原链");
        printList(head);
        Node node = reverseListFromMToN(head, 2, 4);
        System.out.println("逆序后链");
        printList(node);
    }
}
