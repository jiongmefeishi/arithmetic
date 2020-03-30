package cn.zqtao.offer.meituan;

/**
 * @auther: zqtao
 * @description: 股票
 * @version: 1.0
 */
public class MT_04_Gupiao {

    // 通用框架，状态转移方程，base case
    // base case
/*
    dp[i][k][s] 第i天，最多允许k 次交易，s:买卖状态

    dp[-1][k][0] = 0
    解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
    dp[-1][k][1] = -infinity
    解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
    dp[i][0][0] = 0
    解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
    dp[i][0][1] = -infinity
    解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。


    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
                  max(   选择 rest  ,           选择 sell      )

    解释：今天我没有持有股票，有两种可能：
    要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
    要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                  max(   选择 rest  ,           选择 buy         )

    解释：今天我持有着股票，有两种可能：
    要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
    要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

*/

    public static int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) { // 天数0~n从第0天开始, -1 表示还没开始
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    // 优化空间复杂度 O(1)
    public static int maxProfit_k_1_Plus(int[] prices) {

        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }




}
