package cn.zqtao.code.nowcoder.list;

/**
 * @auther: zqtao
 * @description: 单链表
 * @version: 1.0
 */
public class ListNode {

    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}
class Solution{

    /**
     * 反转链表
     * 并返回头结点
     */
    public ListNode ReverseList(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode next = null;

        while (head != null){
            next = head.next;
            //pre始终指向当前head的前一个节点，这样可以反转节点了。
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
