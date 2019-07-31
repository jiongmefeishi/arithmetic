package cn.zqtao.learn.nowcode_other.day1;

/**
 * @auther: zqtao
 * @description: 数组累加和问题1
 * 1、给定一个数组，值全是正数，请返回累加和为给定值k的最长子数组长度。
 *
 * 思路：滑动窗口
 *
 * 双指针 L R 分别控制窗口进元素和出元素
 * 时间复杂度：O(N)
 * @version: 1.0
 */
public class Code_04_1_LongestSumSubArrayLengthInPositiveArray {

    public static int maxSubArrLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;

        int L = 0;
        int R = 0;

        int sum = arr[R];
        int len = 0;
        while (R < arr.length) {
            if (sum == k) {
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            } else if (sum < k) {
                R++;
                if (R == arr.length) break; // 防止越界
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {7,3,2,1,1,1,1,4,6};
        System.out.println(maxSubArrLen(arr, 6));
    }
}
