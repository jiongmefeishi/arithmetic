package cn.zqtao.zcy_chapter.abc;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 单项链表环的长度
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 检查单向链表是否存在环，若存在环则返回环的长度，否则返回0。
 *
 * 要求：算法实现尽可能做到时间、空间复杂度最优。
 *
 * 输入
 * [ [ 1, 2 ], [ 2, 3 ] ]，表示的就是一个单向链表，1->2->3
 *
 * 输出
 * 环的长度
 *
 *
 * 样例输入
 * [[1,2],[2,3],[3,4],[4,5],[5,3]]
 * 样例输出
 * 3
 */
public class MyList2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            int[] arr = getArr(s);
            Node head = getList(arr);
            boolean isloop = isloop(head);
            System.out.println(isloop ? getLoopLength(head) : 0);
        }
    }

    public static Node getList(int[] arr) {
        if (arr.length == 0) return null;
        HashMap<Integer, Node> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Node n = new Node(arr[i]);
            if (!map.containsKey(n)) {
                map.put(arr[i], n);
            }
        }
        for (int i = 0; i < arr.length; i += 2) {
            int a = arr[i];
            int b = arr[i + 1];
            map.get(a).next = map.get(b);
        }
        return map.get(arr[0]);
    }

    public static int[] getArr(String str) {
        if (str == null || str.equals("") || str.equals("[]")) return new int[0];

        String replace1 = str.replace("[", "");
        replace1 = replace1.replace("]", "");

        String[] s = replace1.split(",");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        return arr;
    }

    public static boolean isloop(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static int getLoopLength(Node head) {
        Node fast = head;
        Node slow = head;
        while ((fast != null) && (fast.next != null)) {
            fast = fast.next.next;
            slow = slow.next;
            while (fast == slow) {
                int len = 1;
                fast = fast.next.next;
                slow = slow.next;
                while (fast != slow) {
                    len++;
                    fast = fast.next.next;
                    slow = slow.next;
                }
                return len;
            }
        }
        return 0;
    }

    static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;

        }
    }
}