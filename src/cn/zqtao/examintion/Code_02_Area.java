package cn.zqtao.examintion;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 图形面积
 * @version: 1.0
 */
public class Code_02_Area {
    public static long solution(int[][] m, int N, int M) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0L;

        long sum = 0L;
        for (int i = 0; i < N; i++) {

            int max = Integer.MIN_VALUE;
            for (int j = 0; j < M; j++) {
                max = Math.max(max, m[i][j]);
            }
            sum += max;
        }

        for (int j = 0; j < M; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                max = Math.max(max, m[i][j]);
            }
            sum += max;
        }
        return (sum * 2) + (N * M * 2);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[][] arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = in.nextInt();
                }
            }
            System.out.println(solution(arr, N, M));
        }
        in.close();
    }
}
