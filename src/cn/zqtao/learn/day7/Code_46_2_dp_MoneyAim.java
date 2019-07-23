package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description: 给你一个数组arr，和一个整数aim。
 * 如果可以任意选择arr中的 数字，能不能累加得到aim，返回true或者false
 * @version: 1.0
 */
public class Code_46_2_dp_MoneyAim {

    public static boolean isSum(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];

        printMatrix(dp);
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        printMatrix(dp);

        for (int i = arr.length - 1; i >= 0; i--) { // 0 --> arr.length -1    arr
            for (int j = aim - 1; j >= 0 ; j--) { // 0 -->  aim-1  目标额
//                dp[i][j] = dp[i + 1][j];
//                if (j + arr[i] <= aim) {
//                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
//                }
                if (j + arr[i] <= aim) { // 如果 j + 当前数，小于aim，
                    // dp[i][j] = 加上当前值，和不加上当前值，任意一个满足即可
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        printMatrix(dp);

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4 };
        int aim = 7;
        System.out.println(isSum(arr, aim));
    }

    // print matrix
    public static void printMatrix(boolean[][] matrix) {
        System.out.println("-------开始打印-------");
        if (matrix == null || matrix.length == 0) return;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-------结束打印-------");
    }
}
