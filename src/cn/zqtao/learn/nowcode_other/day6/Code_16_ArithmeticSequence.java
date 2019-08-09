package cn.zqtao.learn.nowcode_other.day6;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 等差数列
 * 如果一个数列S满足对于所有的合法的i,都有S[i + 1] = S[i] +d,这里的d也可以是负数和零,我们就称数列S为等差数列。
 *
 * 小易现在有一个长度为n的数列x,小易想把x变为一个等差数列。
 *
 * 小易允许在数列上做交换任意两个位置的数值的操作,并且交换操作允许交换多次。
 *
 * 但是有些数列通过交换还是不能变成等差数列,小易需要判别一个数列是否能通过交换操作变成等差数列
 *
 * 输入描述:
 *
 * 输入包括两行,
 *
 * 第一行包含整数n(2 <n < 50),即数列的长度。
 *
 * 第二行n个元素x[i] (0 <x[i] < 1000),即数列中的每个整数。
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
