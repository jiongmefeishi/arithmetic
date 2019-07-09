package cn.zqtao.learn.day3;

/**
 * @auther: zqtao
 * @description: 转圈打印矩阵
 * @version: 1.0
 */
public class Code_15_PrintMatrixSpiralOrder {

    // 43
    public static void printMatrixSpiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length < 1) return;

        // A(ax,ay) B(bx,by) 对角线的俩个点
        int ax = 0;
        int ay = 0;
        int bx = matrix.length - 1;
        int by = matrix[0].length - 1;

        // 保证两点是存在的
        while (ax <= bx && ay <= by) {
            // 每次传入两个对角线
            printEdge(matrix, ax++, ay++, bx--, by--);
            System.out.println(); // 每打印一圈换行，方便查看结果
        }
    }

    public static void printEdge(int[][] matrix, int ax, int ay, int bx, int by) {
        // 两点在一条直线上，不是对角线处理
        if (ay == by) {
            for (int i = ax; i <= bx; i++) {
                System.out.println(matrix[i][ay] + " ");
            }
        } else if (ax == bx) {
            for (int i = by; i >= ay; i--) {
                System.out.println(matrix[ax][i] + " ");
            }
        } else { // 对角线，成圈打印
            int curX = ax;
            int curY = ay;
            // 向右
            while (curY != by) {
                System.out.print(matrix[ax][curY] + " ");
                curY++;
            }

            // 向下
            while (curX != bx) {
                System.out.print(matrix[curX][by] + " ");
                curX++;
            }

            // 向左
            while (curY != ay) {
                System.out.print(matrix[bx][curY] + " ");
                curY--;
            }

            // 向上
            while (curX != ax) {
                System.out.print(matrix[curX][ay] + " ");
                curX--;
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }, {17,18,19,20} };
        printMatrixSpiralOrder(matrix);

    }
}
