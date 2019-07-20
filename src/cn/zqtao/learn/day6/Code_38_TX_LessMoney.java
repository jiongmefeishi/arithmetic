package cn.zqtao.learn.day6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @auther: zqtao
 * @description: 黄金分割
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜 板。
 * 一群人想整分整块金 条，怎么分最省铜板？
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为 10+20+30=60. 金条要分成10,20,30三个部分。
 * 如果， 先把长 度60的金条分成10和50，花费60 再把长度50的金条分成20和30， 花费50 一共花费110铜板。
 * 但是如果， 先把长度60的金条分成30和30，花费60 再把长度30 金条分成10和20，花费30 一共花费90铜板。
 *
 * 输入一个数组，返回分割的最小代价。
 * @version: 1.0
 */
public class Code_38_TX_LessMoney {

    public static int lessMoney(int[] arr) {
        // 堆，小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]); // 如小根堆
        }

        int res = 0;
        int cur = 0;
        while (minHeap.size() > 1) {
            cur = minHeap.poll() + minHeap.poll();
            res += cur;
            minHeap.add(cur);
        }
        return res;
    }

    // 小根堆比较器
    public static class MinHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2; // < 0  o1 < o2  负数
        }
    }

    //大根堆比较器
    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; // <   o2 < o1
        }

    }

    public static void main(String[] args) {
        // solution
        int[] arr = {6, 7, 8, 9};
        System.out.println(lessMoney(arr));

        int[] arrForHeap = {3, 5, 2, 7, 0, 1, 6, 4};


        // 测试大根堆，小根堆，优先级队列（实际就是小根堆）

        // min heap 默认情况下，PriorityQueue 就是小根堆
        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ1.add(arrForHeap[i]);
        }
        while (!minQ1.isEmpty()) {
            System.out.print(minQ1.poll() + " ");
        }
        System.out.println();

        // min heap use Comparator
        System.out.println("min heap use Comparator");
        PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinHeapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ2.add(arrForHeap[i]);
        }
        while (!minQ2.isEmpty()) {
            System.out.print(minQ2.poll() + " ");
        }
        System.out.println();

        // max heap use Comparator
        System.out.println("max heap use Comparator");
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            maxQ.add(arrForHeap[i]);
        }
        while (!maxQ.isEmpty()) {
            System.out.print(maxQ.poll() + " ");
        }
    }
}
