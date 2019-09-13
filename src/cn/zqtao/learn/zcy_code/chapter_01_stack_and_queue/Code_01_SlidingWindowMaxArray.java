package cn.zqtao.learn.zcy_code.chapter_01_stack_and_queue;

import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description: 生成窗口最大值数组
 * 思路：双端队列来维护窗口最大值更新
 * <p>
 * 时间复杂度 O(N)
 * @version: 1.0
 */
public class Code_01_SlidingWindowMaxArray {

    public static int[] getMaxWindowArr(int[] arr, int w) {

        if (arr == null || w < 1 || arr.length < w) return null;

        LinkedList<Integer> queueMax = new LinkedList<>();
        // N 个元素 W 大小的窗口 一共可以产生 N-W+1 个最大值
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!queueMax.isEmpty() && queueMax.peekLast() <= arr[i]) {
                queueMax.pollLast();
            }
            queueMax.add(arr[i]);
            if (queueMax.peekFirst() == i - w) { // 队头过期
                queueMax.pollFirst();
            }

            if (i >= w - 1) {
                res[index++] = arr[queueMax.peekFirst()];
            }
        }
        return res;
    }
}
