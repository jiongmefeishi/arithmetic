package cn.zqtao.learn.nowcode_other.day7;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 堆棋子
 * 堆棋子时间限制: 1秒空限制: 32768K
 *
 * 小易将n个棋子摆放在一张无限大的棋盘上。第个棋子放在第x[i]行 y[i]列。
 * 同一个格子允许放置多个棋子。每一次操作小易可以把一个棋子拿起并将其移动到原格子的上、下、左、右的任意一个格子中。
 * 小易想知道要让棋盘上出现有一个格子中至少有i (1 <= i <=n ) 个棋子所需要的最少操作次数。
 *
 * 输入描述:输入包括三行,第一行一个整数n(1 <= n <= 50),表示棋子的个数
 * 第二行为n个棋子的横坐标x[i] (1 <= n <= 10^9)
 * 第三行为n个棋子的横坐标y[i] (1 <= n <= 10^9)
 *
 * 输出描述:
 * 输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数,以空格分割。
 * 行末无空格如样例所示:对于1个棋子:不需要操作
 * 对于2个棋子:将前两个棋子放在(1, 1)中
 * 对于3个棋子:将前三个棋子放在(2, 1)中
 * 对于4个棋子:将所有棋子都放在(3, 1)中
 *
 * 输入例子1:
 *
 * 4
 *
 * 1 2 4 9
 *
 * 1 1 1 1
 *
 * 输出例子1:
 *
 * 0 1 3 10
 */
public class Code_21_MinOPS {

    /**
     * @param size 点个数
     * @param x 横坐标
     * @param y 纵坐标
     */
    public static int[] minOPs(int size, int[] x, int[] y) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) { // 结果数组初始化
            res[i] = Integer.MAX_VALUE;
        }

        // 优先级队列：存储穷举的各个点到输入点的曼哈顿距离
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { // 两个for用来穷举所有可能作为到输入点曼哈顿距离最小的点

                for (int k = 0; k < size; k++) { // 每次和输入的size个点进行曼哈顿距离计算
                    pq.add(Math.abs(x[k] - x[i]) + Math.abs(y[k] - y[j]));
                }
                int resIndex = 0;
                int sum = 0; // i个点移动到当前穷举的可能点的曼哈顿距离
                // （x[i],y[i]）这个可能点，存在 i 个点的曼哈顿距离（操作数）
                while (!pq.isEmpty()) {
                    sum += pq.poll();
                    res[resIndex] = Math.min(res[resIndex], sum);
                    resIndex++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] x = new int[size];
            int[] y = new int[size];
            for (int i = 0; i < size; i++) {
                x[i] = in.nextInt();
            }
            for (int i = 0; i < size; i++) {
                y[i] = in.nextInt();
            }
            int[] res = minOPs(size, x, y);
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < size; i++) {
                result.append(String.valueOf(res[i]) + " ");
            }
            System.out.println(result.toString().trim());
        }
        in.close();
    }
}
