package cn.zqtao.learn.day3;

/**
 * @auther: zqtao
 * @description:
 * “之”字形打印矩阵
 * 【题目】 给定一个矩阵matrix，按照“之”字形的方式打印这 个矩阵，
 * 例如：
 * 1   2   3   4
 * 5   6   7   8
 * 9  10  11  12
 *
 * “之”字形打印的结果为：
 * 1，2，5，9，6，3，4，7，10，11， 8，12
 * 【要求】 额外空间复杂度为O(1)。
 *
 * @version: 1.0
 */
public class Code_18_ZigZagPrintMatrix {
    public static void zigZagPrintMatrix(int[][] matrix) {
        // A点坐标，A一直向右前进，当遇到边界时，改为向下前进
        int aR = 0;
        int aC = 0;

        // B点坐标，B一直向下前进，当遇到边界时，改为向右前进
        int bR = 0;
        int bC = 0;

        // 下前进边界，即总行
        int enbR = matrix.length - 1;
        int enbC = matrix[0].length - 1; // 右前进边界，即总列

        // 向哪个方向进行打印标志
        boolean fromUp = false;
        // 结束打印条件：
        // 以A点分析,A点前进路线是  A--> 右 --> 遇边界 --> 下
        // 当A--> 下 遇到边界时，就是结束之时
        // 同理，以B作为分析一样，
        while (aR != enbR + 1) {

            printLevel(matrix, aR, aC, bR, bC, fromUp);
            aR = aC == enbC ? aR + 1 : aR;
            aC = aC == enbC ? aC : aC + 1;
            bC = bR == enbR ? bC + 1 : bC;
            bR = bR == enbR ? bR : bR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int aR, int aC, int bR, int bC, boolean fromUp) {

        if (fromUp) {
            while (aR != bR + 1)
                System.out.print(m[aR++][aC--] + " ");
        } else {
            while (bC != aC + 1)
                System.out.print(m[bR--][bC++] + " ");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        zigZagPrintMatrix(matrix);

    }
}
