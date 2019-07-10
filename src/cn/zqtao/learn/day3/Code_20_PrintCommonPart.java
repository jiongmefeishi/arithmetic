package cn.zqtao.learn.day3;

/**
 * @auther: zqtao
 * @description: 打印两个有序链表的公共部分
 * 【题目】 给定两个有序链表的头指针head1和head2，打印两个 链表的公共部分。
 * @version: 1.0
 */
public class Code_20_PrintCommonPart {

    public static class List {
        public List next;
        public int val;

        public List(int data) {
            this.val = data;
        }
    }

    // 打印两个有序链表公共部分
    public static void printTowSortedListCommonPart(List head1, List head2) {

        while (head1 != null && head2 != null) {
            if (head1.val > head2.val)
                head2 = head2.next;
            else if (head1.val < head2.val)
                head1 = head1.next;
            else{
                System.out.print(head1.val + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void printLinkedList(List node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List node1 = new List(2);
        node1.next = new List(3);
        node1.next.next = new List(5);
        node1.next.next.next = new List(6);

        List node2 = new List(1);
        node2.next = new List(2);
        node2.next.next = new List(5);
        node2.next.next.next = new List(7);
        node2.next.next.next.next = new List(8);

        printLinkedList(node1);
        printLinkedList(node2);
        printTowSortedListCommonPart(node1, node2);
    }
}
