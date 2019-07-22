package cn.zqtao.learn.day7;

import cn.zqtao.learn.model.MatrixModel;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_45_1_Recur_MinPath {

    public static int minPath(int[][] matrix) {
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

        // 暴力尝试
        // 暴力尝试存在的问题
        // 多次的重复计算
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath(m));
        System.out.println(minPath(MatrixModel.generateRandomMatrix(6, 6)));
    }
}
