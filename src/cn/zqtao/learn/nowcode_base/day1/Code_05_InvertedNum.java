package cn.zqtao.learn.nowcode_base.day1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 逆序对问题
 * 在一个数组中，左边的数如果比右边的数大，则两个数构成一个逆序对，请打印所有逆序对
 * @version: 1.0
 */
public class Code_05_InvertedNum {

    public static void invertedNum(int[] arr) {
        if (arr == null || arr.length < 2) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) return;

        int mid = L + ((R - L) >> 1);
        // 打印左边逆序对
        mergeSort(arr, L, mid);
        // 打印右边逆序对
        mergeSort(arr, mid + 1, R);
        // 打印合并后的逆序对
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;

        int p1 = L;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= R) {
            if (arr[p1] > arr[p2]) {
                for (int j = p2; j <= R; j++) {
                    System.out.println("逆序对：" + arr[p1] + "--" + arr[j]);
                }
            }
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid)
            help[i++] = arr[p1++];
        while (p2 <= R)
            help[i++] = arr[p2++];

        for (i = 0; i < help.length; i++)
            arr[L + i] = help[i];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split(" ");
            int[] num = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                num[i] = Integer.parseInt(str[i]);
            }
            System.out.println(Arrays.toString(num));
            invertedNum(num);
            System.out.println(Arrays.toString(num));
        }
        sc.close();

    }

}
