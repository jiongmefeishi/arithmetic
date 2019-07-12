package cn.zqtao.learn.day3;

/**
 * @auther: zqtao
 * @description: 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * 【题目】 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个 整 数pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于 pivot 的节点，
 * 中间部分都是值等于pivot的节点，右部分都是值大于 pivot的节点。
 * 除这个要求外，对调整后的节点顺序没有更多的要求。
 * 例如：链表9->0->4->5>1，pivot=3。 调
 * 整后链表可以是
 * 1->0->4->9->5，
 *
 * 也可以是
 * 0->1->9->5->4。
 *
 * 总 之，满 足左部分都是小于3的节点，中间部分都是等于3的节点（本例中这个部 分为空），
 * 右部分都是大于3的节点即可。对某部分内部的节点顺序不做 要求。
 * 进阶： 在原问题的要求之上再增加如下两个要求。 在左、中、右三个部分的内部也做顺序要求，
 * 要求每部分里的节点从左 到右的 顺序与原链表中节点的先后次序一致。 例如：链表9->0->4->5->1，pivot=3。
 * 调整后的链表是0->1->9->4->5。 在满足原问题要求的同时，左部分节点从左到 右为0、1。
 * 在原链表中也 是先出现0，后出现1；中间部分在本例中为空，不再 讨论；右部分节点 从左到右为9、4、5。
 * 在原链表中也是先出现9，然后出现4， 最后出现5。
 *
 * 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 * @version: 1.0
 */
public class Code_22_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    // 43  58 09
    // 类似荷兰国旗问题，这里使用O（N）的空间复杂度来转换为数组，进行荷兰国旗问题解决，最后回写到原链
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }

        Node[] arr = new Node[i];
        cur = head;
        i = 0;
        // 装载进数组
        while (cur != null){
            arr[i++] = cur;
            cur = cur.next;
        }

        // 按照荷兰国旗方式进行分区
        arrPartition(arr, pivot);
        // 回写
        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }

        arr[i - 1].next = null;
        return arr[0];
    }

    public static void arrPartition(Node[] arr, int pivot){
        int less = -1;
        int more = arr.length;

        int cur = 0;
        while (cur != more){
            if (arr[cur].value < pivot){
                swap(arr, ++less ,cur++);
            } else if (arr[cur].value > pivot){
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // save next node
        // 每次从链表中弹出首节点，存放到这三个区域链表中

        while (head != null) {
            next = head.next;
            head.next = null; // 弹出首节点

            if (head.value > pivot){
                if (bH == null){
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head; // 尾节点指向新节点
                }
            } else if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else {
                if (eH == null){
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            head = next;
        }

        // small and equal reconnect
        // 合并小区域和等区域
        if(sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        // 合并等于区域和大区域
        if (eT != null) {
            eT.next = bH;
        }
        // 小区域不为空 ？（返回小区域+等于区域+大区域链总链） ： （返回等于区域+大区域链）
        // 等于区域不为空 ？（返回等于区域+大区域链总链）：（大于区域链）
        return sH != null ? (sH) :(eH != null ? eH : bH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
         head1 = listPartition1(head1, 5);
//        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }
}
