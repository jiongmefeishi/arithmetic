package cn.zqtao.learn.nowcode_other.day6;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 等差数列
 * @version: 1.0
 */
public class Code_16_ArithmeticSequence {

    public static boolean isArithmeticSequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }

        int n = arr.length;
        if ((2 * (sum - min * n)) % (n * (n - 1)) == 0) { // d 可以为0 或是整数，%后为0
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        if (isArithmeticSequence(arr))
            System.out.println("Possible");
        else
            System.out.println("Impossible");
        in.close();
    }
}
