package cn.zqtao.learn.day0_extra;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @auther: zqtao
 * @description: 随时找到数据流的中位数
 *
 * 【题目】 有一个源源不断地吐出整数的数据流，假设你有足够的空间来 保存吐出的数。
 * 请设计一个名叫MedianHolder的结构， MedianHolder可以随时取得之前吐出所有数的中位数。
 *
 * 【要求】
 * 1．如果MedianHolder已经保存了吐出的N个数，
 *      那么任意时刻 将一个新数加入到MedianHolder的过程，
 *      其时间复杂度是 O(logN)。
 * 2．取得已经吐出的N个数整体的中位数的过程，时间复杂度为 O(1)。
 *
 *
 * 思路：准备两个堆，一个大根堆一个小根堆，争取让两个堆中数都是 N / 2
 *
 * 中位数 = (大根堆堆顶 + 小根堆堆顶) / 2
 *
 * @version: 1.0
 */
public class Code_Extra_00_MedianQuick {

    public static class MedianHolder{
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianHolder() {
            maxHeap = new PriorityQueue<>(new MaxHeapComparator());
            minHeap = new PriorityQueue<>(new MinHeapComparator());
        }

        // 只要两个堆内元素差大于1，调整
        private void modifyTwoHeapsSize() {
            if (this.maxHeap.size() == this.minHeap.size() + 2){
                this.minHeap.add(this.maxHeap.poll());
            }

            if (this.maxHeap.size() + 2 == this.minHeap.size()){
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        public void addNumber(int num) {
            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }

            // 不为空，
            if (this.maxHeap.peek() >= num) { // 小于大根堆堆顶
                this.maxHeap.add(num); // 入大根堆
            } else { // 大于大根堆堆顶
                // 入小根堆
                if (this.minHeap.isEmpty()) {
                    this.minHeap.add(num);
                    return;
                }
                if (this.minHeap.peek() > num) { // 如果小根堆堆顶大于num，还是给它归为到大根堆
                    this.maxHeap.add(num);
                } else {
                    this.minHeap.add(num);
                }
            }

            modifyTwoHeapsSize();
        }

        public Integer getMedian(){
            int maxheapSize = maxHeap.size();
            int minheapSize = minHeap.size();

            if ((maxheapSize + minheapSize) == 0){
                return null; // 没有元素进入结构
            }

            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();

            if (((maxheapSize + minheapSize) & 1) == 0) { // 两个堆中数为偶数狗
                return (maxHeapHead + minHeapHead) / 2;
            }

            // 奇数个元素 返回
            return maxheapSize > minheapSize ? maxHeapHead : minHeapHead;
        }
    }

    // 大根堆比较器
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    // 小根堆比较器
    public static class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        // test 奇偶性
        for (int i = 0; i < 9; i++) {
            System.out.print((i & 1) + "  ");
            // 0  1  0  1  0  1  0  1  0
        }

        MedianHolder medianHolder = new MedianHolder();
        int[] arr = {9, 5, 4, 8, 1, 0, 2, 3, 7, 6};
        for (int i = 0; i < arr.length; i++) {
            medianHolder.addNumber(arr[i]);
        }

        System.out.println("\n中位数: " + medianHolder.getMedian());
    }
}
