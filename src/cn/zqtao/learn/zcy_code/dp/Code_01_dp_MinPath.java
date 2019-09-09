package cn.zqtao.learn.zcy_code.dp;

import cn.zqtao.model.MatrixModel;

/**
 * @auther: zqtao
 * @description: 矩阵的最小路径和
 * 给定一个矩阵m,从左上角开始每次只能向右或者向下走,最后到达右下角的位置,
 * 路径上所有的数字累加起来就是路径和,返回所有的路径中最小的路径和。
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 *
 * 最小路径和：1 3 1 0 6 1 0  --> 12
 *
 * 三种解决
 * 1、暴力递归
 * 2、动态规划 dp[][] 二维dp 表
 * 3、动态规划，空间压缩 dp[]
 *
 * 注意：空间压缩是有局限性的，本题如果改成：打印最小路径和的路径
 * 就必须使用完整的动态规划表，如果只是求解最优解的值，可以使用空间压缩的方法省去空间
 * 空间压缩是滚动更新的，让求解轨迹变得不可回溯。
 */
public class Code_01_dp_MinPath {
    // 暴力递归
    public static int minPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        return work(matrix, 0, 0);
    }

    // 从(r,c)点到右下角位置最短路径和
    public static int work(int[][] matrix, int r, int c) {
        if (r == matrix.length - 1 && c == matrix[0].length - 1) { // 当前点到达右下角位置
            return matrix[r][c];// 从右下角位置到右下角位置，路径和等于右下角值
        }

        if (r == matrix.length - 1 && c != matrix[0].length - 1) { // 到达最后一行
            return matrix[r][c] + work(matrix, r, c + 1); // 只能向右移动
        }

        if (r != matrix.length - 1 && c == matrix[0].length - 1) { // 到达最后一列
            return matrix[r][c] + work(matrix, r + 1, c);
        }

        // 既可以向右，也可以向下
        int right = work(matrix, r, c + 1); // right -> 右边位置到最右下角的最短路径和
        int down = work(matrix, r + 1, c); // down -> 下边位置到最右下角的最短路径和
        return matrix[r][c] + Math.min(right, down);
    }

    /**
     * 动态规划 dp[][]
     */
    public static int minPath2(int[][] matrix) {
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


    /**
     * 空间压缩
     * dp[]
     */
    public static int minPath3(int[][] m) {
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

    /**
     * 空间压缩
     * dp[]
     * min M N
     * 对于矩阵选择较小的行或者列来进行空间压缩
     */
    public static int minPath4(int[][] m) {
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

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));
        System.out.println(minPath4(m));
    }
}
