package cn.zqtao.learn.nowcode_other.day7;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 疯狂队列
 * 疯狂队列时间限制: 1秒限制: 32768K小易老师是非常严厉的,它会要求所有学生在进入教室前都排成一列,
 * 并且他要求学生按照身高不递减的顺序排列。有一次,n个学生在列队的时候,小易老师正好去卫生间了。
 * 学生们终于有机会反击了,于是学生们决定来一次疯狂的队列,他们定义一个队列的疯狂值为每对相邻排列学生身高差的绝对值总和。
 * 由于按照身高顺序排列的队列的疯狂值是最小的,他们当然决定按照疯狂值最大的顺序来进行列队。
 * 现在给出n个学生的身高,请计算出这些学生列队的最大可能的疯狂值。小易老师回来一定会气得半死。
 *
 * 输入描述:输入包括两行,第一行一个整数n (1 <= n <= 50),表示学生的人数第二行为n个整数h[i] (1 <= h[i] <= 1000),表示每个学生的身高
 * 输出描述:输出一个整数,表示n个学生列队可以获得的最大的疯狂值。
 *
 * 例如
 * 输入
 *
 * 5
 * 5 10 25 40 25
 * 输出： 100
 *
 * 最大疯狂值时，队列顺序是25-10-40-5-25
 */
public class Code_22_MaxMad {

    /**
     *
     * 贪心策略：先选出最大和最小站好，次大放在最小右边，次小放在最大左边
     *
     * 通过坐标变化模拟站队
     *
     * 1 20 50 200
     * 第一步模拟：200 1              选出最大和最小
     * 第二步模拟：200 1 50           次大放最小右边
     * 第三步模拟：20 200 1 50        次小放最大左边
     *
     * 如果是奇数个，最后一个需要判断放左边还是放右边
     */
    public static int maxMad(int[] arr) {
        Arrays.sort(arr);
        int res = arr[arr.length - 1] - arr[0];
        int maxI = arr.length - 2; // 次大下标
        int minI = 1; // 次小下标
        while (minI < maxI) {
            res += arr[maxI + 1] - arr[minI];
            res += arr[maxI] - arr[minI - 1];
            maxI--;
            minI++;
        }
        if (minI == maxI) { // 奇数个处理
            res += Math.max(arr[minI] - arr[minI - 1], arr[minI + 1] - arr[minI]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(maxMad(arr));
        }
        in.close();
    }
}

