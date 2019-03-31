package cn.zqtao.code.nowcoder.offer.list;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 从头到尾打印链表
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * @version: 1.0
 */
public class PrintListFromTailToHead {

    // 栈存储，先入后出
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        // 存入栈
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        // 弹栈
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }

        return res;
    }


    // 递归
    ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {

        if (listNode != null) {
            printListFromTailToHead2(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }

    /**
     * 1、反转链表
     * 2、再遍历链表存储到ArrayList
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 先反转链表
        // 先反转链表
        if (listNode == null) return new ArrayList<>();
        ListNode head = listNode;
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(pre.val);
        while (pre.next != null) {
            pre = pre.next;
            res.add(pre.val);
        }
        return res;
    }

    // 链表
    class ListNode {

        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
