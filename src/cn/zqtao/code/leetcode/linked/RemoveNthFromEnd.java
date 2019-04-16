package cn.zqtao.code.leetcode.linked;

import cn.zqtao.code.leetcode.util.LinkListUtil;
import cn.zqtao.code.leetcode.util.ListNode;

/**
 * @auther: zqtao
 * @description: 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * @version: 1.0
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode quick = dummy;
        ListNode slow = dummy;
        // 保证在快指针和慢指针之间的是 目标节点
        for (int i = 1; i <= n + 1; i++) {
            quick = quick.next;
        }
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n == 0) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 快指针
        ListNode quick = dummy;
        // 先走n 步，同时排除掉 n > count（总节点数情况）
        for (int i = 0; i <= n; i++) {
            if (quick != null)
                quick = quick.next;
            else
                return head;// 超过总节点，不会删除，返回原链表
        }
        // 慢指针
        ListNode slow = dummy;

        while (quick != null){
            quick = quick.next;
            slow = slow.next;
        }

        // 删除节点
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 快慢指针
     *
     * 快指针比慢指针多走 n + 1 步
     * 当快指针到大链表尾节点，慢指针刚好到达要删除的节点的前一个节点
     */
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        if (head == null || n == 0) return null;
//
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        // 快指针
//        ListNode quick = dummy;
//        // 先走n 步，同时排除掉 n > count（总节点数情况）
//        for (int i = 0; i <= n; i++) {
//            if (quick != null)
//                quick = quick.next;
//            else
//                return head; // 超过总节点，不会删除，返回原链表
//        }
//
//        // 慢指针
//        ListNode slow = dummy;
//
//        while (quick != null){
//            quick = quick.next;
//            slow = slow.next;
//        }
//
//        // 删除节点
//        slow.next = slow.next.next;
//        return dummy.next;
//    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
        int[] arr = {1};

        // 创建链表
        ListNode head = LinkListUtil.arrToListNode(arr);
        System.out.println(LinkListUtil.listNodeToString(head));

        // 打印删除节点后的链表
//        ListNode node = removeNthFromEnd.removeNthFromEnd2(head, 8);
        ListNode node = new RemoveNthFromEnd().removeNthFromEnd2(head, 2);
//        ListNode node = removeNthFromEnd.removeNthFromEnd2(head, 1);
//        ListNode node = removeNthFromEnd.removeNthFromEnd3(head, 1);

        System.out.println(LinkListUtil.listNodeToString(node));

    }

}
