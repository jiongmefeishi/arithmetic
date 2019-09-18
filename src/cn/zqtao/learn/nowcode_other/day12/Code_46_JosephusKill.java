package cn.zqtao.learn.nowcode_other.day12;

/**
 * @auther: zqtao
 * @description: 环形单链表约瑟夫问题
 * @version: 1.0
 */
public class Code_46_JosephusKill {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node kill(Node head, int m) {
        if (head == null || head.next == null || m < 1) return head;

        Node last = head;
        while (last.next != head) { // 找到环尾结点
            last = last.next;
        }

        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    // 数学公式推导方式，根据节点个数 N 和 M 直接推导出哪一个节点最终会活下来
    public static Node killUseMath(Node head, int m){
        if (head == null || head.next == null || m < 1) return head;
        Node cur = head.next;
        int size = 1; // list size
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        size = getLive(size, m);
        while (--size != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    // 公式计算出存活点的坐标
    public static int getLive(int i, int m) {
        if (i == 1) return 1;
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        System.out.println(kill(head1, 3).value);
    }
}
