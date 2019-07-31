package cn.zqtao.learn.nowcode_base.day3;

/**
 * @auther: zqtao
 * @description: 两个单链表相交的一系列问题
 * 【题目】 在本题中，单链表可能有环，也可能无环。
 * 给定两个 单链表的头节点 head1和head2，这两个链表可能相交，也可能 不相交。
 * 请实现一个函数， 如果两个链表相交，请返回相交的 第一个节点；
 * 如果不相交，返回null 即可。 要
 *
 * 求：如果链表1 的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外 空间复杂度请达到O(1)
 *
 * 这道题可以拆分为几道题
 *
 * 1、单链表是否有环
 * 2、两个无环单链表是否相交
 * 3、两个有环单链表是否相交
 *
 * 最快做法，需要额外的空间，使用哈希表 HashSet<Node>
 *
 * 1、判断单链表是否有环
 * 将链表head节点循环存入 哈希表，哈希表返回false，成环
 *
 * 2、判断两个无环单链表是否相交
 * 同理 1
 */
public class Code_24_FindFirstIntersectNode {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    private static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);


        System.out.println("loop1: " + loop1);
        System.out.println("loop2: " + loop2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 不存在一个有环一个无环结构相交
        return null;
    }

    /**
     * 双指针判断链表是否有环，并返回第一个环节点
     * @return 第一个环节点
     */
    public static Node getLoopNode(Node head){
        if(head == null && head.next == null && head.next.next == null) // 最少三个节点成环
            return null;

        Node s = head.next;
        Node f = head.next.next;

        while (s != f) { // s等于f时两指针第一次相交，返回
            if (f.next == null || f.next.next == null)
                return null;// 快指针到达了尾节点null，证明不成环
            s = s.next;
            f = f.next.next;
        }

        f = head; // 快指针从头开始遍历，此时跨步和慢指针一样
        while (f != s){
            f = f.next;
            s = s.next;
        }
        return s; // or f
    }

    /**
     * 判断两个无环单链表是否相交
     * @return 第一次相交节点 或者  null
     */
    public static Node noLoop(Node head1, Node head2){
        if (head1 == null || head2 == null)
            return null; // 任意一个单链表null，不存在交点
        Node cur1 = head1;
        Node cur2 = head2;

        int count = 0; // 记录单链表长度

        // 遍历单链表head1，记录链表长度，和尾节点
        while (cur1 != null){
            count++;
            cur1 = cur1.next;
        }

        while (cur2 != null){
            count--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) return null;// 如果两个单链表的尾节点不同，一定不相交

        cur1 = count >= 0 ? head1 : head2; // cur1 指向 big one
        cur2 = cur1 == head1 ? head2 : head1; // cur2 指向 small one

        count = Math.abs(count); // 绝对值

        while (count != 0){ // 让长链先走 count 步
            cur1 = cur1.next;
            count--;
        }

        while (cur1 != cur2){ // 相交停止
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1; // or cur2
    }

    //36

    /**
     * 两个有环单链表是否相交
     * @param head1
     * @param loop1 第一个链表环节点
     * @param head2
     * @param loop2 第二个链表环节点
     * @return 第一个相交节点
     *
     * 两个有环链表有三种拓扑结构：各自成环不相交，环外相交，环内相交
     *
     * loop1 == loop2   true，环外相交
     * false，循环遍历head1 ，从loop1 开始遍历，到再一次经过loop1，
     *      若找到loop2 节点，相交 返回loop1 或者 loop2    ---->  环内
     *      若没找到loop2， 不相交    各自成环
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){

        if (loop1 == loop2) { // 环外

            Node cur1 = head1;
            Node cur2 = head2;

            int count = 0;
            while (cur1 != loop1){
                count++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2){
                count--;
                cur2 = cur2.next;
            }

            cur1 = count >= 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            count = Math.abs(count);

            while (count != 0) {
                cur1 = cur1.next;
                count--;
            }

            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else { // 各自成环不相交或者环内相交

            Node cur = loop1.next;
            while (cur != loop1){
                if (cur == loop2)
                    return loop2;
                cur = cur.next;
            }

        }

        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println("两个无环单链表：" + getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println("两个有环单链表，且是环外结构：" + getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println("两个有环单链表，且是环内结构：" + getIntersectNode(head1, head2).value);

    }
}

