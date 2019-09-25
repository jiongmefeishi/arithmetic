package cn.zqtao.learn.zcy_code.dp;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_03_dp_PostOfficeMinDistance {

    public static int minDistance(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }

        /*
        one[i][j] 表示 i...j 范围内，只建立一个邮局，这一区间上总距离是多少？
        如果 i...j-1 上有奇数个点，中点是  (i+j-1)/2
        加速j 那么 i...j 就是偶数个节点，中点是 (i+j)/2
        这两个中点是同一个。
        例如
        arr[i...j-1]={4,5,6} 中点是 5
        arr[i...j]={4,5,6,7} 中点是 5

        那么arr[i...j] 的总距离就是 arr[i...j-1] 的总距离 + (arr[j] 到中点的距离)
         */
        int[][] one = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                one[i][j] = one[i][j - 1] + (arr[j] - arr[(i+j)/2]);
            }
        }

        int[][] dp = new int[num][arr.length];
        for (int j = 0; j < arr.length; j++) {
            // dp[a][b] 表示 在0~b 范围内，布置 a+1 个邮局，总长度最少是多少？
            // dp[0][b] 表示 在0~b 范围内，布置 1 个邮局，总长度最少是多少
            dp[0][j] = one[0][j];
        }

        for (int i = 1; i < num; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + one[k+1][j]);
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5, 1000};
        System.out.println(minDistance(arr, 2));
    }
}
