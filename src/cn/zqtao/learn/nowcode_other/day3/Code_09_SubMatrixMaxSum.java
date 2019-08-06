package cn.zqtao.learn.nowcode_other.day3;

/**
 * @auther: zqtao
 * @description: 实例3：子矩阵最大累加和
 *
 * 给定一个矩阵matrix,其中的值有正、有负、有0,返回子矩阵的最大累加和。
 *
 * 例如,矩阵matrix为:
 *
 * -90 48 78
 *
 *  64-40 64
 *
 * -81 -7 66
 *
 * 其中,最大累加和的子矩阵为:
 *
 * 48 78
 *
 * -40 64
 *
 * -7   66
 *
 * 所以返回累加和209.
 *
 *
 *
 * 例如, matrix为:
 *
 * -1  -1  -1
 *
 * -1   2   2
 *
 * -1  -1  -1
 *
 * 其中,最大累加和的子矩阵为:
 *
 * 2   2
 *
 * 所以返回累加和4.
 *
 * 矩阵常识
 *
 * 1、一个N * N 矩阵可以构成的子矩阵约为 N的4次方
 *
 * - 选择两个点构成矩阵
 * - 左上角选点 (C(N,1), C(N,1))  都是从 N  选择一个，所以是 N的二次方，同理右下角 N的2次方
 *
 * 2、一个N * N 矩阵可以构成的正方形子矩阵约为N的3次方
 *
 * - 任意从左上角选择开始点：N的二次方
 * - 正方形的边长 1、2、3.... N   共有N种
 * @version: 1.0
 */
public class Code_09_SubMatrixMaxSum {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] helpArr = null; // 累加数组

        for (int i = 0; i < m.length; i++) { // 0 ~ N-1
            helpArr = new int[m[0].length];

            for (int j = i; j <m.length; j++) { // i ~ N-1

                cur = 0;
                for (int k = 0; k < helpArr.length; k++) {
                    helpArr[k] += m[j][k]; // 累加数组为新数组
                    cur += helpArr[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
    }
}
