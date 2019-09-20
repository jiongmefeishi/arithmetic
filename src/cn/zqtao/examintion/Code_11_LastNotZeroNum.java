package cn.zqtao.examintion;

import java.util.Scanner;

/**
 * 2020秋招-360笔试题-N!最后一位不为0的数字
 *
 * 末尾的数
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 小明想知道 n! ，最后一位不为 0 的数字，你能告诉他吗？ n! = n*(n-1)*(n-2)*....*3*2*1
 * 输入
 * 一个正整数n（n<=5000）
 * 输出
 *  一位数答案，表示最后一位不为0的数字
 *
 * 样例输入
 * 7
 * 样例输出
 * 4
 *
 * 提示 ： 7！=5040 ，最后一位不为0的数字为4
 *
 * 思路：
 * 1、先求出N! 中所有的 因数5 个数
 * 2、因为成对的因数2 和因数5 会导致 0 ，所有对N! 的每一位进行同等消除因数 2 和因数5
 * 3、消除会直接导致0 的因数 10
 */
public class Code_11_LastNotZeroNum {
    public static int getLastNotZeroNum(int n) {
        int count5 = 0;
        for (int i = 5; i <= n; i++) { // 求 i 的5 因数个数
            int m = i;
            while (m % 5 == 0) {
                m /= 5;
                count5++;
            }
        }

        int count2 = count5;
        int res = 1;
        for (int i = 1; i <= n; i++) {
            int m = i;
            while (m % 2 == 0 && count2 != 0) {
                // 去除 2 因子，2因子和5因子会导致 0
                m /= 2;
                count2--;
            }
            while (m % 5 == 0 && count5 != 0) { // 同理去除 5 因子
                m /= 5;
                count5--;
            }
            m %= 10; // 去除直接导致右边增加0的因子10
            res = (res * m) % 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt(); // 7 ---> 4
            System.out.println(getLastNotZeroNum(N));
        }
        in.close();
    }
}