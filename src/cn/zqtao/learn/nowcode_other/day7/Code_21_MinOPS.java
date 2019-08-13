package cn.zqtao.learn.nowcode_other.day7;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 堆棋子
 * @version: 1.0
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
