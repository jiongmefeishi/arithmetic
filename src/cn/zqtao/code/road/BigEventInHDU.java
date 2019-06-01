package cn.zqtao.code.road;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: Big Event in HDU
 * Time Limit: 10000/5000 MS (Java/Others)    Memory Limit: 65536/32768 K (Java/Others)
 * Total Submission(s): 37483    Accepted Submission(s): 13007
 * <p>
 * <p>
 * Problem Description
 * Nowadays, we all know that Computer College is the biggest department in HDU. But, maybe you don't know that Computer College had ever been split into Computer College and Software College in 2002.
 * The splitting is absolutely a big event in HDU! At the same time, it is a trouble thing too. All facilities must go halves. First, all facilities are assessed, and two facilities are thought to be same if they have the same value. It is assumed that there is N (0<N<1000) kinds of facilities (different value, different kinds).
 *  
 * <p>
 * Input
 * Input contains multiple test cases. Each test case starts with a number N (0 < N <= 50 -- the total number of different facilities). The next N lines contain an integer V (0<V<=50 --value of facility) and an integer M (0<M<=100 --corresponding number of the facilities) each. You can assume that all V are different.
 * A test case starting with a negative integer terminates input and this test case is not to be processed.
 *  
 * <p>
 * Output
 * For each case, print one line containing two integers A and B which denote the value of Computer College and Software College will get respectively. A and B should be as equal as possible. At the same time, you should guarantee that A is not less than B.
 *  
 * <p>
 * Sample Input
 * 2
 * 10 1
 * 20 1
 * 3
 * 10 1
 * 20 2
 * 30 1
 * -1
 *  
 * <p>
 * Sample Output
 * 20 10
 * 40 40
 * @version: 1.0
 */
public class BigEventInHDU {

    /*
     * 输入一个整数N， 代表接下来将要接收N行输入的数据
     */

    /**
     * 题意：给出每个物体的价值和物体的数量，如何分使得A,B所得价值最接近并且A的价值不能小于B
     *
     * <p>
     * 思路：将总和平分后，就是一道01背包题了
     * <p>
     * B(j)= max{B(j), B(j-w(i))+v(i)}；
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] values = new int[1024];
        int[] dp = new int[1024];

        while (in.hasNext()) {
            int N = in.nextInt();
            System.out.println(N);

            int value = 0; // 价值
            int num = 0; // 数量
            int sum = 0; // 求得是尽量平均分配，计算总价值
            for (int i = 0, j = 0; i < N; i++) {
                // 将价值存入数组，转为01 背包问题
                value = in.nextInt();
                num = in.nextInt();
                System.out.println("value: " + value + "  num: " + num);
                for (int k = 0; k < num; k++) {
                    values[j++] = value;
                    sum += value;
                }
            }

            System.out.println("总价值为：" + sum);
            // 01 背包问题, 状态转移方程 B(j)= max{B(j), B(j-w(i))+v(i)}；
            for (int i = 0; i < values.length; i++) {
                for (int j = sum / 2; j >= values[i]; j--) {
//                    int tmp = j - values[i];
//                    dp[j] = Math.max(dp[j], dp[tmp] + values[i]);
                    dp[j] = Math.max(dp[j], dp[j - values[i]] + values[i]);// 状态转移方程
                }
            }
            System.out.println(sum - dp[sum / 2] + "  " + dp[sum / 2]);
        }
    }

}
