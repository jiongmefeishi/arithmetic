package cn.zqtao.examintion;


/**
 * 小米之家购物 --->  N 元钱最少能买几件，求最少not最多
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 小米之家有很多米粉喜欢的产品，产品种类很多，价格也不同。比如某签字笔1元，某充电宝79元，某电池1元，某电视1999元等
 *
 * 假设库存不限，小明去小米之家买东西，要用光N元预算的钱，请问他最少能买几件产品？
 *
 * 输入
 * 第1行为产品种类数
 *
 * 接下来的每行为每种产品的价格
 *
 * 最后一行为预算金额
 *
 *
 * 输出
 * 能买到的最少的产品的件数，无法没有匹配的返回-1
 *
 *
 * 样例输入
 * 2
 * 500
 * 1
 * 1000
 * 样例输出
 * 2
 */
public class Code_04_MinItemByAllMoney {
    public static int solution(int[] prices, int budget) {
        if (prices == null || prices.length == 0 || budget < 0) return  -1;

        int n = prices.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][budget+1];
        for (int j = 1; j <= budget; j++) {
            dp[0][j] = max;
            if (j - prices[0] >= 0 && dp[0][j - prices[0]] != max){
                dp[0][j] = dp[0][j - prices[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= budget; j++) {
                left = max;
                if (j - prices[i] >= 0 && dp[i][j - prices[i]] != max){
                    left = dp[i][j - prices[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][budget] != max ? dp[n - 1][budget] : -1;
    }

    public static void main(String[] args) {
        int[] arr = {500, 1};
        System.out.println(solution(arr, 2001));
    }
}
