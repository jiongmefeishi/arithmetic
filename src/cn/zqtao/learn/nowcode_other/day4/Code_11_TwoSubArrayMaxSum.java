package cn.zqtao.learn.nowcode_other.day4;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_11_TwoSubArrayMaxSum {
    public static int twoSubArrMaxSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        // 每一项存 i 到 N-1 范围内最大子数组和
        int[] maxRight = new int[arr.length];

        int max = Integer.MIN_VALUE;
        int cur = 0;

        // 第一次遍历，求取 i 到 N-1 范围内最大子数组和
        for (int i = arr.length - 1; i > 0; i--) { // i==0 不需要进行求最大累加和
            cur += arr[i];
            max = Math.max(cur, max);
            maxRight[i] = max; // 入数组存储
            cur = cur < 0 ? 0 : cur;
        }

        max = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        cur = 0;
        // 第二次遍历，求取 0 到 i 范围内最大子数组和
        // 同时 取 i+1 到 N-1 范围内最大子数组和进行累加比较
        for (int i = 0; i < arr.length - 1; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            res = Math.max(res, max + maxRight[i + 1]);

            cur = cur < 0 ? 0 : cur;
        }
        return res;
    }

    // for test 暴力求解
    public static int rightAnswer(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int p = 0; p < arr.length - 1; p++) {
            res = Math.max(res, maxSum(arr, 0, p) + maxSum(arr, p + 1, arr.length - 1));
        }
        return res;
    }

    // for test
    public static int maxSum(int[] arr, int l, int r) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = l; i <= r; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) - 10;
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 50;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            int[] test = generateRandomArray();
            if (twoSubArrMaxSum(test) != rightAnswer(test)) {
                hasErr = true;
            }
        }
        if (hasErr) {
            System.out.println("23333333");
        } else {
            System.out.println("66666666");
        }
    }
}
