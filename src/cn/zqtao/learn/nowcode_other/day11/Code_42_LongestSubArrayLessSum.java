package cn.zqtao.learn.nowcode_other.day11;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 子数组累加和小于定值等于 K
 * @version: 1.0
 */
public class Code_42_LongestSubArrayLessSum {

    /**
     * 时间复杂度 O(N)
     * 思路：
     * 第一步：1、获取以i 位置开始 i~N-1 能够累加出的最小和
     *        2、记录最小和累加的右边界
     * 第二步：滑动窗口求取最大长度
     * 其中的加速过程是：不需要每一个 i 位置都进行向后累加，可以借助前一次累加的最大结果 - (i-1)位置的结果
     * 得到的就是 i~(i-1)所达到的最大值
     *
     * 如下面范围内都是第一步得到的最小累加和的区域
     * 0~6  7~9   10~17   18~23   24~28
     *
     * 0位置开始窗口扩容，直到 10~17 位置内累加和都是小于 K 的， 累加18~23区域会大于 K，那么0~17范围累加和为 sum
     * 此时开始从 1 位置遍历，不需要从新从 1~6 .... 10~17 依次累加，只需要 sum-arr[0] 即可得到新 sum 这就是加速过程
     *
     */
    public static int maxLengthSumLessK(int[] arr, int K) {
        if (arr == null || arr.length == 0) return 0;

        int[] sums = new int[arr.length]; // 以i 位置开始 i~N-1 能够累加出的最小和
        HashMap<Integer, Integer> ends = new HashMap<>(); // 记录最小累加和的右边界

        sums[arr.length - 1] = arr[arr.length - 1];// 最后一个数累加最小和就是自己
        ends.put(arr.length - 1, arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) { // 倒遍历
            if (sums[i+1] < 0) {
                sums[i] = arr[i] + sums[i+1];
                ends.put(i, ends.get(i + 1));// 填充的是 i+1 的右边界
            } else { // >=0 自己本身就是最小和
                sums[i] = arr[i];
                ends.put(i, i);
            }
        }

        // 遍历完毕，采用滑动窗口求最大长度
        int end = 0;
        int sum = 0;
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + sums[end] <= K){
                sum += sums[end];
                end = ends.get(end) + 1;
            }

            sum -= end > i ? arr[i] : 0;
            res = Math.max(res, end - i);
            end = Math.max(end, i + 1); // 更新滑动窗口边界
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {7,5,-1,-2,-2,0,0,0};
        System.out.println(maxLengthSumLessK(arr, 3));
    }
}
