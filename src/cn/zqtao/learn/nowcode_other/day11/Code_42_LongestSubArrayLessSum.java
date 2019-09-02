package cn.zqtao.learn.nowcode_other.day11;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 子数组累加和小于定值等于 K
 * @version: 1.0
 */
public class Code_42_LongestSubArrayLessSum {

    public static int maxLengthSumLessK(int[] arr, int K) {
        if (arr == null || arr.length == 0) return 0;

        int[] sums = new int[arr.length]; // 以i 位置开始 i~N-1 能够累加出的最小和
        HashMap<Integer, Integer> ends = new HashMap<>(); // 记录累加最小和的右边界

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
