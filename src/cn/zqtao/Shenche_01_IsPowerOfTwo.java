package cn.zqtao;

import java.util.Scanner;
/**
 * 2次幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方
 */
public class Shenche_01_IsPowerOfTwo {

    public static boolean method(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            System.out.println(method(N) ? "True" : "False");
        }
    }
}