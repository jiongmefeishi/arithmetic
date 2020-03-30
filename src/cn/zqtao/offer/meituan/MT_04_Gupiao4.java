package cn.zqtao.offer.meituan;

/**
 * @auther: zqtao
 * @description: k = +infinity with fee
 * 每次交易要支付手续费，只要把手续费从利润中减去即可
 * 
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
 * 解释：相当于买入股票的价格升高了。
 * 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
 *
 * @version: 1.0
 */
public class MT_04_Gupiao4 {

    int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
}
