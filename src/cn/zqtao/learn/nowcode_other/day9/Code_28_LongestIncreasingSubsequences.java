package cn.zqtao.learn.nowcode_other.day9;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 最长递增子序列
 *
 * 月光宝盒的密码
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 131072KB；其他语言 655360KB
 * 题目描述：
 * 小希偶然得到了传说中的月光宝盒,然而打开月光宝盒需要一串密码。
 * 虽然小希并不知道密码具体是什么，但是月光宝盒的说明书上有着一个
 * 长度为 n (2 <= N <= 50000)的序列 a (-10^9 <= a[i] <= 10^9)的范围内。
 * 下面写着一段话：密码是这个序列的最长的严格上升子序列的长度
 * (严格上升子序列是指，子序列的元素是严格递增的，
 *
 * 例如: [5,1,6,2,4]的最长严格上升子序列为[1,2,4])，请你帮小希找到这个密码。
 *
 *
 * 输入
 * 第1行：1个数N，N为序列的长度(2<=N<=50000)
 *
 * 第2到 N+1行：每行1个数，对应序列的元素(-10^9 <= a[i] <= 10^9)
 *
 * 输出
 * 一个正整数表示严格最长上升子序列的长度
 */
public class Code_28_LongestIncreasingSubsequences {

    /**
     * 最长递增子序列 长度
     *
     * 碰到子序列，子数组等问题，最先想到的是状态化
     * 1、以某元素开头怎么怎么样
     * 2、以某元素结尾怎么怎么样
     *
     * 例如此题：以每一个元素结尾得到都最长子序列是多少
     *
     * 思路1、经典解法辅助数组，存储以每一个元素结尾获得的最长子序列长度
     * arr          3 1 2 5 4 6 7
     * helpLen      1 1 2 3 3 4 5
     *
     * return 5
     */
    public static int getMaxNum(int[] arr) {
        int[] helpLen = new int[arr.length];

        int maxLen = Integer.MIN_VALUE;// 记录最长子序列的长度
        for (int i = 0; i < arr.length; i++) {
            helpLen[i] = 1; // 无论之前数据怎样，自己都构成一个最长子序列，长度为1
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    helpLen[i] = Math.max(helpLen[i], helpLen[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, helpLen[i]);
        }
        return maxLen;
    }

    /**
     * 扩展求出递增的具体序列
     *
     * 最长递增子序列 序列
     *
     * 思路1、经典解法辅助数组，存储以每一个元素结尾获得的最长子序列长度
     * arr           3 1 2 5 4 6 7
     * helpLen       1 1 2 3 3 4 5
     *
     * return 1 2 5 6 7
     */
    public static int[] getMaxList(int[] arr) {

        int[] helpLen = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            helpLen[i] = 1; // 无论之前数据怎样，自己都构成一个最长子序列，长度为1
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    helpLen[i] = Math.max(helpLen[i], helpLen[j] + 1);
                }
            }
        }

        // 由辅助数组得到序列
        int index = 0; // 记录最长子序列中最大都下标
        int maxLen = Integer.MIN_VALUE;// 记录最长子序列的长度
        for (int i = 0; i < helpLen.length; i++) {
            if (maxLen < helpLen[i]) {
                maxLen = helpLen[i];
                index = i;
            }
        }

        // 反推序列
        int[] ans = new int[maxLen];
        ans[--maxLen] = arr[index]; // 填入结尾数
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && helpLen[i] == helpLen[index] - 1){
                ans[--maxLen] = arr[i];
                index = i;
            }
        }
        return ans;
    }

    /**
     * 优化：上面算法时间复杂度 O(N^2)
     * 为了加速第一步求 helpLen[] 数组
     * 再增加一个辅助数组 ends[]
     * ends[i] 含义: 长度为 i+1 的所以递增子序列的最小结尾
     *
     * arr   3 1 2 4 3
     *
     * ends[i] 更新，遍历arr数组，每一个arr[i] 都二分法在ends中寻找第一个 >= arr[i] 的数，更新
     * ends
     *       3 0 0 0 0
     *       1 0 0 0 0
     *       1 2 0 0 0
     *       1 2 4 0 0
     *       1 2 3 0 0
     */
    public static int[] getMaxList3(int[] arr){
        int[] helpLen = new int[arr.length];
        int[] ends = new int[arr.length];

        helpLen[0] = 1;
        ends[0] = arr[0];

        int L = 0;
        int R = 0;

        int right = 0; // ends右边界
        for (int i = 1; i < arr.length; i++) {
            L = 0;
            R = right;
            while (L <= R) {
                int mid = L + (R - L) / 2;
                if (ends[mid] >= arr[i]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            right = Math.max(right, L); // 没有找到的情况下 L > 边界
            ends[L] = arr[i];
            helpLen[i] = L + 1;
        }
        return generateLIS(arr, helpLen);
    }

    public static int[] getMaxList2(int[] arr){

        int[] helpLen = new int[arr.length];
        int[] ends = new int[arr.length];

        ends[0] = arr[0];
        helpLen[0] = 1;

        int right = 0; // ends右边界下标
        for (int i = 1; i < arr.length; i++) {
            int R = right;
            // 二分法查找第一个 >= arr[i] 的数的下标
            int index = getIndex(ends, 0, R, arr[i]);
            right = Math.max(right, index);
            ends[index] = arr[i];
            helpLen[i] = index + 1;
        }
        return generateLIS(arr,helpLen);
    }

    // 二分法找到第一个 >= k 的数的下标 , 没有找到就返回 +1
    // arr: 1 3 4 4   k=5  return 4
    public static int getIndex(int[] arr, int L, int R, int k){
        while (L <= R) {
            int mid = L + (R-L) / 2;
            if (arr[mid] >= k){
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return L;
    }

    public static int[] generateLIS(int[] arr, int[] helpLen) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < helpLen.length; i++) {
            if (helpLen[i] > len) {
                len = helpLen[i];
                index = i;
            }
        }
        int[] ans = new int[len];
        ans[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && helpLen[i] == helpLen[index] - 1) {
                ans[--len] = arr[i];
                index = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,5,4,6,7,1};
        int[] arr2 = {1,2,4,4,4,4,4};

        System.out.println(Arrays.toString(getMaxList(arr)));
        System.out.println(Arrays.toString(getMaxList3(arr)));
        System.out.println(Arrays.toString(getMaxList2(arr)));

        System.out.println(Arrays.toString(getMaxList(arr2)));
        System.out.println(Arrays.toString(getMaxList3(arr2)));
    }
}
