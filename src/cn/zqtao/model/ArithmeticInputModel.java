package cn.zqtao.model;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 输入处理模板
 * @version: 1.0
 */
public class ArithmeticInputModel {

    /**
     * Input  输入数据有多组，每组占一行，由一个整数组成。
     * Sample Input
     * 56
     * 67
     * 100
     * 123
     */
    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int num = in.nextInt();

            // todo
        }
    }*/

    /**
     * 输入数据有多组，每组占2行，第一行为一个整数N，指示第二行包含N个实数。
     * Sample Input
     * 4
     * 56.9  67.7  90.5  12.8
     * 5
     * 56.9  67.7  90.5  12.8
     */
/*    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                double num = sc.nextDouble();
            }

            // todo
        }
    }
    */


    /**
     * 输入数据有多行，第一行是一个整数n，表示测试实例的个数，后面跟着n行，
     * 每行包括一个由字母和数字组成的字符串。
     *
     * Sample Input
     * 2
     * asdfasdf123123asdfasdf
     * asdf111111111asdfasdfasdf
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

        }

        // todo
    }
}
