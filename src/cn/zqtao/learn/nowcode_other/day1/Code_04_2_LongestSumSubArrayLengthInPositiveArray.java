package cn.zqtao.learn.nowcode_other.day1;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 数组累加和问题2
 * 时间复杂度：O(N)
 *
 * 2、给定一个数组，值可以为正、负和0，请返回累加和为给定值k的最长子数组长度。
 *
 * 注意：子数组，子串等问题，常规套路：
 *
 *     选择以某个位置开始怎么怎么样, 或者选择以某个位置结束怎么怎么样！！
 *
 *     即选择子串开始位置考虑，或者选择子串结束位置考虑。
 *
 *     通过其他位置来推此位置时的状态
 *
 * 此题，以某个位置为结尾的子数组，累加和为定值 k
 *
 * @version: 1.0
 */
public class Code_04_2_LongestSumSubArrayLengthInPositiveArray {

    public static int maxSubArrLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;

        int len = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 处理第一位等于 k 的情况
        /*
             6 1 2 3  k = 6
             上来 sum = 6
             sum - k = 0;
             len = i - map.get(0) 但是现在不存在map.get(0) 这样len返回的结果是0，而不是 1
         */
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    // for test

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr = generateArray(20);
        int[] arr = {2, 3, -2, 1, -3, 4, 0, 0, -3, -3, 4, 1, -4, 4, 4, -5, -3, 4, 4, -1};
        printArray(arr);
        System.out.println(maxSubArrLen(arr, 10));
    }
}
