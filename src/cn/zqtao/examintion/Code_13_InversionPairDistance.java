package cn.zqtao.examintion;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 逆序对距离
 * 例如
 * 1 3 4 2 5
 * 逆序对：
 *      (3,2)距离为2
 *      (4,2)距离为1
 * 逆序对距离：3
 */
public class Code_13_InversionPairDistance {

    /**
     * 记录节点的数值和节点所在数组的下标
     */
    public static class Node{
        public int val;
        public int index;

        public Node(int index, int val) {
            this.val = val;
            this.index = index;
        }
    }

    // 小根堆比较器
    public static class NodeComaprator implements  Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.val - o2.val;
        }
    }

    public static int method(int[] arr) {

        // 小根堆
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComaprator());
        // 辅助小根堆
        PriorityQueue<Node> help = new PriorityQueue<>(new NodeComaprator());

        int len = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            // 小根堆有值，并且当前 arr[i] 的值大于 小根堆堆顶，计算距离，直到不满足
            while (!queue.isEmpty() && arr[i] > queue.peek().val) {
                Node poll = queue.poll();
                help.add(poll);
                len += poll.index - i;
            }
            pollall(help, queue);
            queue.add(new Node(i, arr[i]));
        }
        return len;
    }

    public static void pollall(PriorityQueue<Node> q1, PriorityQueue<Node> q2) {
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        int[] arr2= {9,3,2,1};
        System.out.println(method(arr));
        System.out.println(method(arr2));
    }
}