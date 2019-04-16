package cn.zqtao.code.leetcode.linked;

import cn.zqtao.code.leetcode.util.LinkListUtil;
import cn.zqtao.code.leetcode.util.ListNode;

/**
 * @auther: zqtao
 * @description: 反转链表
 * @version: 1.0
 */
public class ReverseList {

    /**
     * 迭代
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
     *
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
     * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。
     * 不要忘记在最后返回新的头引用！
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        if(head == null) return null;

        ListNode pre = null;
        ListNode current = head;
        while (current != null){
            ListNode tmpNode = current.next;
            current.next = pre;
            pre = current;
            current = tmpNode;
        }

        return pre;
    }

    /**
     * 递归  -- 还不太理解此方法的使用
     *
     * 递归版本稍微复杂一些，其关键在于反向工作。
     * 假设列表的其余部分已经被反转，现在我该如何反转它前面的部分？
     * 假设列表为：n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     *
     * 若从节点 nk+1 到 nm 已经被反转，而我们正处于 nk。
     *
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     *
     * 我们希望 nk+1 的下一个节点指向 nk。
     *
     * 所以，
     *
     * nk.next.next = nk;
     *
     * 要小心的是 n1 的下一个必须指向 Ø 。
     * 如果你忽略了这一点，你的链表中可能会产生循环。
     * 如果使用大小为 2 的链表测试代码，则可能会捕获此错误。
     * @param head
     * @return
     *
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        ListNode head = LinkListUtil.arrToListNode(arr);
        System.out.println(LinkListUtil.listNodeToString(head));

        ListNode listNode = new ReverseList().reverseList(head);

        System.out.println(LinkListUtil.listNodeToString(listNode));
    }

}
