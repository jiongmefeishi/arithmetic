package cn.zqtao.learn.nowcode_base.day0_extra;

/**
 * @auther: zqtao
 * @description: 找零问题
 * @version: 1.0
 */
public class Code_Extra_03_MinCoins {

    // 组合出 aim 的面额数
    public static int minCoins1(int[] coins, int aim) {
        if (coins == null || coins.length == 0) return 0;

        // dp 存储所有的面额组合，其中对于不能组合出的数，置为Integer.MAX_VALUE
        int[][] dp = new int[coins.length][aim + 1];
        int max = Integer.MAX_VALUE;

        for (int j = 1; j <= aim; j++) { // 只用 coins[0] 组合的面额，不能置为最大, 其中j 表示面额
            dp[0][j] = max;
            if (j - coins[0] >= 0 && dp[0][j - coins[0]] != max) {
                dp[0][j] = dp[0][j - coins[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < coins.length; i++) { // i = 0 只用coins[0] 上一个for已经更新过
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != max) {
                    left = dp[i][j - coins[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[coins.length - 1][aim] != max ? dp[coins.length - 1][aim] : -1;
    }
}
