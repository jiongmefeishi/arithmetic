package cn.zqtao.model;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class MatrixModel {

    // random matrix 随机产生 rowSize 行，colSize列的矩阵
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) return null;

        int[][] m = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                m[i][j] = (int) (Math.random() * 10);
            }
        }
        return m;
    }

    // print matrix
    public static void printMatrix(int[][] matrix) {
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
