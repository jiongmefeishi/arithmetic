package cn.zqtao.learn.zcy_code.chapter_01_stack_and_queue;

import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description: 最大值减去最小值小于等于 num 的子数组数量
 * @version: 1.0
 */
public class Code_02_AllLessNumSubArray {

    /**
     * 思路：arr[i...j] 范围内寻找每一个以 arr[i] 为开头满足条件的子数组数量 j - i
     * 暴力方法是枚举出所有的子数组 O(N^2) 然后遍历 子数组，求子数组的最大值和最小值，计算条件
     * <p>
     * 这里使用双端队列来快速的获取子数组的最大值和最小值
     * <p>
     * 注意：双端队列中存的是数组元素的 下标
     */
    public static int getArrNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) return 0;

        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();

        int res = 0;
        int i = 0;
        int j = 0; // i,j 表示 arr[i...j] 范围的子数组
        // 如果arr[i...j] 范围内的max - min 满足条件，那么 arr[i...j] 内的任意一个子数组都满足条件
        // 一旦 arr[i...j] 出现不满足情况 从 j-1之前都是满足的
        // arr[i...j-1]  arr[i...j-2] ...
        // 这样的子数组以 arr[i] 开头一共有 j-i 个
        while (i < arr.length) {

            while (j < arr.length) { // j 向右扩展子数组范围
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j])
                    qmin.pollLast();
                qmin.addLast(j);

                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j])
                    qmax.pollLast();
                qmax.addLast(j);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num)
                    break; // 不满足条件是，j 停止扩展，此时 i 向右扩展
                j++;
            } // i 向右缩减子数组范围

            if (qmin.peekFirst() == i)
                qmin.pollFirst();
            if (qmax.peekFirst() == i)
                qmax.pollFirst(); // 下一步 i 向右扩展 ,如果双端队列的最大值或最小值就是当前 i 位置，
            // 那么i++ 后，最大值和最小值就会失效，所以需要剔除
            res += j - i;
            System.out.println("res : " + res + " j: " + j + " i: " + i);
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 7, 2};
        System.out.println(getArrNum(arr, 4));
    }
}
