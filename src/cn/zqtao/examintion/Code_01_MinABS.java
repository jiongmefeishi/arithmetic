package cn.zqtao.examintion;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 贝壳
 * 计算绝对值
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 131072KB；其他语言 655360KB
 * 题目描述：
 * 给出n个正整数，要求找出相邻两个数字中差的绝对值最小的一对数字，如果有差的绝对值相同的，则输出最前面的一对数。
 *
 * 2<n<=100，正整数都在10^16范围内
 *
 * 输入
 * 输入包含2行，第一行为n，第二行是n个用空格分隔的正整数。
 *
 * 输出
 * 输出包含一行两个正整数，要求按照原来的顺序输出
 *
 *
 * 样例输入
 * 9
 * 1 3 4 7 2 6 5 12 32
 * 样例输出
 * 3 4
 * @version: 1.0
 */
public class Code_01_MinABS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            Integer[] tmp = getMin(arr);
            System.out.println(tmp[0] + " " + tmp[1]);
        }

        sc.close();
    }

    public static Integer[] getMin(int[] arr) {
        long min = Math.abs(arr[0] - arr[1]);
        HashMap<Long, Integer[]> map = new HashMap<>();
        map.put(min, new Integer[]{arr[0], arr[1]});

        for (int i = 2; i < arr.length; i++) {
            int tmp = Math.abs(arr[i] - arr[i - 1]);
            if (min > tmp) {
                min = tmp;
                map.put(min, new Integer[]{arr[i - 1], arr[i]});
            }
        }
        return map.get(min);
    }
}
