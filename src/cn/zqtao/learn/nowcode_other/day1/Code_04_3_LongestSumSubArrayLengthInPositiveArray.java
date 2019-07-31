package cn.zqtao.learn.nowcode_other.day1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 数组累加和问题3
 *
 * 3、给定一个数组，值可以为正、负和0，请返回累加和小于等于k的最长子数组长度。
 * @version: 1.0
 */
public class Code_04_3_LongestSumSubArrayLengthInPositiveArray {


    /**
     * 第一步：求得 以arr[i] 开头的情况下，往后累加，能得到的最小和是多少？存进 sums
     *              以arr[i] 开头累加出最小和，边界时多少？存进ends
     *
     * 第二步：遍历数组，求以a[i] 为开头，累加和 <=k 的子数组
     *
     * 无注释版
     */
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sums = new int[arr.length];
        HashMap<Integer, Integer> ends = new HashMap<>();

        // 第一步
        sums[arr.length - 1] = arr[arr.length - 1];
        ends.put(arr.length - 1, arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends.put(i, ends.get(i+1));
            } else {
                sums[i] = arr[i];
                ends.put(i, i);
            }
        }

        // 第二步: 采用滑动窗口
        int end = 0;
        int sum = 0;
        int resLen = 0;
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + sums[end] <= k) {
                sum += sums[end];
                end = ends.get(end) + 1;
            }

            sum -= end > i ? arr[i] : 0;
            resLen = Math.max(resLen, end - i);
            end = Math.max(end, i + 1);
        }

        return resLen;
    }

    // 注释版
    public static int maxLengthAwesome2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sums = new int[arr.length];

        // key: 第i位数下标  value: 第i位数开头的情况下累加最小和的右边界
        HashMap<Integer, Integer> ends = new HashMap<>();

        // 以arr[i] 开头的情况下，往后累加，能得到的最小和是多少？存进 sums
        // 以arr[i] 开头累加出最小和，边界时多少？存进ends


        // 最后一个数的最小累加和，就是本身
        sums[arr.length - 1] = arr[arr.length - 1];
        // 右边界时最后一个数的下标
        ends.put(arr.length - 1, arr.length - 1);


        // 遍历数组，求以arr[i] 开头情况下，往后累加，能得到的最小和是多少？存进sums
        // 右边界存进 ends
        for (int i = arr.length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) { // 对于arr[i] 如果以 arr[i+1] 开头求得的最小和 < 0
                sums[i] = arr[i] + sums[i + 1]; // arr[i] 开头的最小和=当前数+以arr[i+1]开头累加的最小和
                ends.put(i, ends.get(i + 1));// 右边界就等于 i+1 的右边界
            } else { // >= 0 情况下
                sums[i] = arr[i]; // 最小和就是本身
                ends.put(i, i); // 右边界也是本身
            }
        }


        // 以a[i] 为开头，累加和<=k 的子数组

        int end = 0; // 记录右边界
        int sum = 0; // 记录滑动窗口内累加和
        int res = 0; // 记录最长子数组长度
        for (int i = 0; i < arr.length; i++) {
            // 以arr[i]开头的最小累加和是小于 k 的才能继续累加。
            // 如果连最小的累加和都大于k,那么无论怎么样累加都不可能累加出小于等于k的累加和
            while (end < arr.length && sum + sums[end] <= k) { // 持续累加直到找到累加和大于 K的
                sum += sums[end];
                end = ends.get(end) + 1; // end指向下一个最小累加和的起始位置
                // 如以arr[0]开始的最小累加和到 i=7结束，即ends[0]=7。
                // 那么下一个最小累加和就是i=8位置开始的，end=8
            }
            sum -= end > i ? arr[i] : 0;
            res = Math.max(res, end - i);
            end = Math.max(end, i + 1); // 如果没有走while循环end 需要更新为下一个数
        }
        return res;
    }

    // 下面提供一种 O(N*logN) 的方法进行对比测试
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;

            System.out.println("arr: " + Arrays.toString(arr) + "  k=" + k);
            System.out.println("maxLengthAwesome(arr, k): " + maxLengthAwesome(arr, k));
            System.out.println("maxLength(arr, k): " + maxLength(arr, k));
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }
}
