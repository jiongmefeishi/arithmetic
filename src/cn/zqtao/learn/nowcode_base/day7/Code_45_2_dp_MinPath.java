package cn.zqtao.learn.nowcode_base.day7;

import cn.zqtao.model.MatrixModel;

/**
 * @auther: zqtao
 * @description: 最短路径
 * @version: 1.0
 */
public class Code_45_2_dp_MinPath {

    /**
     * 空间压缩
     * dp[]
     * min M N
     * 对于矩阵选择较小的行或者列来进行空间压缩
     */
    public static int minPath3(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int more = Math.max(m.length, m[0].length);// 行数和列数较大的那个为more
        int less = Math.min(m.length, m[0].length);// 较小的为less
        boolean rowMore = more == m.length; // 行是否是最大的那个
        int[] dp = new int[less]; // 选择小
        dp[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i - 1] + (rowMore ? m[0][i] : m[i][0]);
        }
        for (int r = 1; r < more; r++) {
            dp[0] = dp[0] + (rowMore ? m[r][0] : m[0][r]);
            for (int c = 1; c < less; c++) {
                dp[c] = Math.min(dp[c], dp[c - 1]) + (rowMore ? m[r][c] : m[c][r]);
            }
        }
        return dp[less - 1];
    }

    /**
     * 空间压缩
     * dp[]
     */
    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int[] dp = new int[m[0].length];
        dp[0] = m[0][0];
        for (int i = 1; i < m[0].length; i++) { // (0,0) ---> (0,j) 位置的最短路径和
            dp[i] = dp[i - 1] + m[0][i];
        }

        for (int r = 1; r < m.length; r++) {
            dp[0] = dp[0] + m[r][0]; // 更新第一个值，一直向下的情况
            for (int c = 1; c < m[0].length; c++) {
                dp[c] = Math.min(dp[c], dp[c - 1]) + m[r][c];
            }
        }
        return dp[m[0].length - 1];
    }

    public static int minPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        MatrixModel.printMatrix(matrix);// 打印原矩阵

        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        int[][] dp = new int[rowSize][colSize];

        dp[0][0] = matrix[0][0]; // 第一步，标记目标位置。对于此题就是左上角位置(0,0)

        MatrixModel.printMatrix(dp); // 打印第一步状态


        // 第二步、回到base case中，把不被依赖的位置设置好，对于此题就是最后一行，最后一列值,
        // 但是递归改为动态规划是反的，所以是第一行和第一列
        for (int c = 1; c < colSize; c++) { // 第一行
            dp[0][c] = matrix[0][c] + dp[0][c - 1];
        }
        MatrixModel.printMatrix(dp);

        for (int r = 1; r < rowSize; r++) { // 第一列
            dp[r][0] = matrix[r][0] + dp[r - 1][0];
        }
        MatrixModel.printMatrix(dp);

        for (int r = 1; r < rowSize; r++) {
            for (int c = 1; c < colSize; c++) {
                int right = matrix[r][c] + dp[r - 1][c]; // 右状态
                int down = matrix[r][c] + dp[r][c - 1]; // 下状态
                dp[r][c] = Math.min(right, down);
            }
            MatrixModel.printMatrix(dp);
        }

        return dp[rowSize - 1][colSize - 1];
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath(m));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));
    }
}
