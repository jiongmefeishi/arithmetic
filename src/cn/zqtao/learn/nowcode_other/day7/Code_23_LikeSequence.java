package cn.zqtao.learn.nowcode_other.day7;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 小易喜欢的数列
 * 小易喜欢的数列时间限制: 1秒间限制: 32768K
 *
 * 小易非常喜欢拥有以下性质的数列:
 *
 * 1、数列的长度为n
 *
 * 2、数列中的每个数都在1到k之间(包括1和k)
 *
 * 3、对于位置相邻的两个数A和B (A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
 *
 * 例如,当n=4, k=7
 *
 * 那么(1, 7, 7,2],它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
 *
 * 但是小易不喜欢[4, 4,4, 2]这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。
 */
public class Code_23_LikeSequence {

    // 暴力尝试
    public static long number1(int n, int k) {
        return process(1, n, k);
    }

    public static long process(int pre, int n, int k) {
        if (n == 0) {
            return 1L;
        }
        long sum = 0;
        for (int cur = pre; cur <= k; cur++) {
            sum += process(cur, n - 1, k);
        }
        for (int cur = 1; cur < pre; cur++) {
            sum += (pre % cur) != 0 ? process(cur, n - 1, k) : 0;
        }
        return sum % 1000000007L;
    }

    // 动态规划
    public static long number2(int n, int k) {
        long[][] dp = new long[k + 1][n];
        for (int i = 0; i < k + 1; i++) {
            dp[i][0] = 1L;
        }
        for (int col = 1; col < n; col++) {
            for (int row = 1; row < k + 1; row++) {
                long sum = 0L;
                for (int cur = row; cur <= k; cur++) {
                    sum += dp[cur][col - 1];
                }
                for (int cur = 1; cur < row; cur++) {
                    sum += (row % cur) != 0 ? dp[cur][col - 1] : 0;
                }
                dp[row][col] = sum % 1000000007L;
            }
        }
        long res = 0L;
        for (int i = 1; i <= k; i++) {
            res += dp[i][n - 1];
            res %= 1000000007L;
        }
        return res;
    }

    // 动态规划进一步缩减计算量
    public static long number3(int n, int k) {
        long[][] dp = new long[k + 1][n];
        for (int i = 0; i < k + 1; i++) {
            dp[i][0] = 1L;
        }
        for (int col = 1; col < n; col++) {
            long sum = 0;
            for (int row = 1; row < k + 1; row++) {
                sum += dp[row][col - 1];
                sum %= 1000000007L;
            }
            for (int row = 1; row < k + 1; row++) {
                long noInclude = 0L;
                for (int cur = row * 2; cur <= k; cur += row) {
                    noInclude += dp[cur][col - 1];
                    noInclude %= 1000000007L;
                }
                dp[row][col] = (sum - noInclude) % 1000000007L;
            }
        }
        long res = 0L;
        for (int i = 1; i <= k; i++) {
            res += dp[i][n - 1];
            res %= 1000000007L;
        }
        return res;
    }

    // 缩减空间
    public static long number4(int n, int k) {
        long[] dp = new long[k + 1];
        for (int i = 0; i < k + 1; i++) {
            dp[i] = 1L;
        }
        for (int col = 1; col < n; col++) {
            long sum = 0;
            for (int row = 1; row < k + 1; row++) {
                sum += dp[row];
                sum %= 1000000007L;
            }
            for (int row = 1; row < k + 1; row++) {
                long noInclude = 0L;
                for (int cur = row * 2; cur <= k; cur += row) {
                    noInclude += dp[cur];
                    noInclude %= 1000000007L;
                }
                dp[row] = (sum - noInclude) % 1000000007L;
            }
        }
        long res = 0L;
        for (int i = 1; i <= k; i++) {
            res += dp[i];
            res %= 1000000007L;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(number1(6, 9));
        System.out.println(number2(6, 9));
        System.out.println(number3(6, 9));
        System.out.println(number4(6, 9));

        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int k = in.nextInt();
            System.out.println(number2(n, k));
        }
        in.close();
    }
}