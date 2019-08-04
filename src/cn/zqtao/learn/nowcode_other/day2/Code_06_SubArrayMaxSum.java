package cn.zqtao.learn.nowcode_other.day2;

/**
 * @auther: zqtao
 * @description: 子数组的最大累加和
 * 给定一个数组arr,返回子数组的最大累加和。
 * 例如, arr=[1,-2, 3,5,-2,6,-1],所有的子数组中,
 * [3, 5,-2,6]可以累加出最大的和12,所以返回12.
 * @version: 1.0
 */
public class Code_06_SubArrayMaxSum {

    public static int maxSubArr(int[] arr) {
        if (arr == null || arr.length == 0)return 0;

        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;// 排除子数组是以负数开头的，即子数组的前缀不能为负数
            // 如  一个子数组  -4 5 6 2 -1 3 其中-4可以直接舍弃
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-1,1,2,-8,12,1,-3,-4,6};
        System.out.println(maxSubArr(arr));
    }
}
