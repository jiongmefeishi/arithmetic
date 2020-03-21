package cn.zqtao.offer.meituan;

import java.util.Arrays;
import java.util.Scanner;

public class meituan_01_ThreeSum {

    public static Long method(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        Long sum1 = 0L;
        Long sum2 = 0L;
        if (arr1.length < 1 || arr1.length > 20000 ||
                arr2.length < 1 || arr2.length > 20000) return 0L;

        if (arr1.length < 3) {
            for (int i = 0; i < arr1.length; i++) {
                sum1 += arr1[i];
                sum2 += arr2[i];
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                sum1 += arr1[arr1.length - i];
                sum2 += arr2[arr1.length - i];
            }
        }

        if (sum1 >= sum2) {
            return sum1;
        } else {
            return sum2;
        }

    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int[] arr1 = new int[N];
            int[] arr2 = new int[N];
            for (int i = 0; i < N; i++) {
                arr1[i] = in.nextInt();
            }
            for (int i = 0; i < N; i++) {
                arr2[i] = in.nextInt();
            }
            System.out.println(method(arr1, arr2));
        }
    }
}