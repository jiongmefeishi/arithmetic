package cn.zqtao.learn.nowcode_other.day7;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 疯狂队列
 * @version: 1.0
 */
public class Code_22_MaxMad {

    /**
     *
     * 贪心策略：先选出最大和最小站好，次大放在最小右边，次小放在最大左边
     *
     * 通过坐标变化模拟站队
     *
     * 1 20 50 200
     * 第一步模拟：200 1              选出最大和最小
     * 第二步模拟：200 1 50           次大放最小右边
     * 第三步模拟：20 200 1 50        次小放最大左边
     *
     * 如果是奇数个，最后一个需要判断放左边还是放右边
     */
    public static int maxMad(int[] arr) {
        Arrays.sort(arr);
        int res = arr[arr.length - 1] - arr[0];
        int maxI = arr.length - 2; // 次大下标
        int minI = 1; // 次小下标
        while (minI < maxI) {
            res += arr[maxI + 1] - arr[minI];
            res += arr[maxI] - arr[minI - 1];
            maxI--;
            minI++;
        }
        if (minI == maxI) { // 奇数个处理
            res += Math.max(arr[minI] - arr[minI - 1], arr[minI + 1] - arr[minI]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(maxMad(arr));
        }
        in.close();
    }
}

