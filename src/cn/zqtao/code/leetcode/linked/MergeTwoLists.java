package cn.zqtao.code.leetcode.linked;

import cn.zqtao.code.leetcode.util.LinkListUtil;
import cn.zqtao.code.leetcode.util.ListNode;

/**
 * @auther: zqtao
 * @description: 合并两个有序链表
 * @version: 1.0
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        int[] arr = {1,2,4};
        int[] arr2 =  {1,3,4,5};

        ListNode l1 = LinkListUtil.arrToListNode(arr);
        ListNode l2 = LinkListUtil.arrToListNode(arr2);

        ListNode node = new MergeTwoLists().mergeTwoLists(l1, l2);

        System.out.println(LinkListUtil.listNodeToString(node));
    }


    /**
     * 递归，每次只比较当前节点值
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        ListNode node;
        if (l1.val <= l2.val) {
            node = l1;
            node.next = mergeTwoLists(l1.next, l2);
        } else {
            node = l2;
            node.next = mergeTwoLists(l1, l2.next);
        }

        return node;
    }
}
