package cn.zqtao;

import java.util.Map;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Main {

    Map<Integer, Integer>

    public static void method(int[] arr) {

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = in.nextInt();
            }
            method(arr);
        }
        in.close();
    }


    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = in.nextInt();
            }
            method(arr);
        }
        in.close();
    }


}