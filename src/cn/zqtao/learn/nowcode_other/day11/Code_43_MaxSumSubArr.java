package cn.zqtao.learn.nowcode_other.day11;

/**
 * @auther: zqtao
 * @description: 连续子数组最大和
 * 给定无序整数序列,求连续子数组最大和,例如1-23 17-7 11-21 34),
 * 子串为17,-7,111,最大和为21输入描述:输入为整数序列,数字用空格分隔,
 * 如:-23 17-7 11-2 1-34输出描述:输出为子序列的最大和: 21
 * 示例1输入-23 17 -7 11 -2 1 -34输出21
 * @version: 1.0
 */
public class Code_43_MaxSumSubArr {

    /**
     * 策略
     * 维护两个变量 cur 和 max
     * cur 向右扩展累加，过程中只要cur 不是小于 0 的，都继续进行累加过程；小于0 cur归 0
     * max 更新记录累加出来的最大和
     *
     *
     * 一个含有正负零的数组中存在最大的子数组
     * 那么这个最大的子数组一定不是以 负数开始的，即子数组的任意前缀一定不是负数
     *
     * 如果数组中都是负数，也不会错过那个最大的负数
     */
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-1,-2,-3, 1};
        System.out.println(maxSum(arr));
    }
}
