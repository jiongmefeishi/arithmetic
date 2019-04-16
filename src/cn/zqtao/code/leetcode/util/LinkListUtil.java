package cn.zqtao.code.leetcode.util;

/**
 * @auther: zqtao
 * @description: 链表工具
 * @version: 1.0
 */
public class LinkListUtil {

    /**
     * 数组转为链表
     */
    public static ListNode arrToListNode(int[] nodeValues) {

        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    /**
     * 遍历链表
     */
    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

}
