package cn.zqtao.learn.nowcode_base.day3;

/**
 * @auther: zqtao
 * @description: 旋转正方形矩阵, 翻转90度
 * 旋转正方形矩阵
 *
 * 【题目】 给定一个整型正方形矩阵matrix，
 * 请把该矩阵调整成 顺时针旋转90度的样子。
 *
 * 【要求】 额外空间复杂度为O(1)。
 * @version: 1.0
 */
public class Code_16_RotateMatrix{
    public static void rotateMatrix(int[][] matrix){

        if (matrix == null || matrix.length == 0) return;

        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        while (tR < dR) {
            rotate(matrix, tR++, tC++, dR--, dC--);
        }
    }

    // 翻转
    public static void rotate(int[][] m, int tR, int tC, int dR, int dC){
        int times = dR - tR;// 本圈遍历次数
        int tmp = 0;

        for (int i = 0; i < times; i++) {
            tmp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotateMatrix(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
