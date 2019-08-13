package cn.zqtao.learn.nowcode_other.day6;

/**
 * @auther: zqtao
 * @description: 机器走位
 * 一排有N个位置,一个机器人在最开始停留在P位置上,如果 p==0位置,下一分钟机器人一定向右移动到1位置;
 * 如果p==N-1,下一分钟机器人一定向左移动到N-2位置。如果P在0和N-1之间,下一分钟机器人一定会向左或者向右移动。
 * 求K分钟的时候,机器人到达位置有多少种走法。函数为: int f(int N, int P, int K, int T),返回值为走法的数量
 * @version: 1.0
 */
public class Code_19_MachineRoad {

    // 暴力尝试
    public static int f1(int N, int P, int K, int T) {
        if (N < 2 || P < 0 || K < 1 || T < 0 || P >= N || T >= N) {
            return 0; // 越界处理
        }
        if (K == 1) { // 一分钟，一种或没有
            return T == P ? 1 : 0;
        }

        // 两端位置
        if (T == 0) {
            return f1(N, P, K - 1, 1);
        }
        if (T == N - 1) {
            return f1(N, P, K - 1, T - 1);
        }
        // 普遍位置
        return f1(N, P, K - 1, T - 1) + f1(N, P, K - 1, T + 1);
    }

    // 动态规划 一维表
    public static int f2(int N, int P, int K, int T) {
        if (N < 2 || P < 0 || K < 1 || T < 0 || P >= N || T >= N) {
            return 0;
        }
        int[][] dp = new int[K][N];
        dp[0][P] = 1;
        for (int i = 1; i < K; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][N - 1] = dp[i - 1][N - 2];
            for (int j = 1; j < N - 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
        }
        return dp[K - 1][T];
    }

    // 动态规划 二维表
    public static int f3(int N, int P, int K, int T) {
        if (N < 2 || P < 0 || K < 1 || T < 0 || P >= N || T >= N) {
            return 0;
        }
        int[] dp = new int[N];
        dp[P] = 1;
        int pre = 0;
        int tmp = 0;
        for (int i = 1; i < K; i++) {
            pre = dp[0];
            dp[0] = dp[1];
            for (int j = 1; j < N - 1; j++) {
                tmp = dp[j];
                dp[j] = pre + dp[j + 1];
                pre = tmp;
            }
            dp[N - 1] = pre;
        }
        return dp[T];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            int N = (int) (Math.random() * 5) + 5;
            int P = (int) (Math.random() * N);
            int K = (int) (Math.random() * 10) + 2;
            int T = (int) (Math.random() * N);
            int res1 = f1(N, P, K, T);
            int res2 = f2(N, P, K, T);
            int res3 = f3(N, P, K, T);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Fuck man!");
                break;
            }
        }
    }
}
