package cn.zqtao.leetcode.day1;

/**
 * @auther: zqtao
 * @description: 分区 小于区域， 等于区域，大于区域
 * @version: 1.0
 */
public class LeetCode_04_ListPartitionSmallerEqualBiger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 分区 小于区域， 等于区域，大于区域
    public Node partition(Node head, int x) {
        if (head == null) return null;

        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // save next node

        while (head != null) { // 分区
            next = head.next;
            head.next = null; // 断链，独立出当前头结点

            if (head.value > x) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head; // 尾节点指向新节点
                }
            } else if (head.value < x) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }

            head = next;
        }

        // 合链
        // 合并小区域和等区域
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        // 合并等于区域和大区域
        if (eT != null) {
            eT.next = bH;
        }
        // 小区域不为空 ？（返回小区域+等于区域+大区域链总链） ： （返回等于区域+大区域链）
        // 等于区域不为空 ？（返回等于区域+大区域链总链）：（大于区域链）
        return sH != null ? (sH) : (eH != null ? eH : bH);
    }
}
