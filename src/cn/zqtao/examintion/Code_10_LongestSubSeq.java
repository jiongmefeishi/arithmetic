package cn.zqtao.examintion;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最长等差数列
 */
public class Code_10_LongestSubSeq {

    public static int method(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        Arrays.sort(arr);
        int d = arr[arr.length - 1] - arr[0]; // 最大等差
        int[][] dp = new int[arr.length][d + 1];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= d; j++) {
                dp[i][j] = 1; // 任意一个元素单独成一个长度为1 的数列
            }
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) { // 考察 i-1~0 范围
                d = arr[i] - arr[j]; // 相同等差 d , a(n) = a(n-1) +d
                dp[i][d] = dp[j][d] + 1; // 所以相同等差，长度可以加一
                max = Math.max(max, dp[i][d]);
            }
        }
        return max;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(method(arr));
        }
        in.close();
    }
}