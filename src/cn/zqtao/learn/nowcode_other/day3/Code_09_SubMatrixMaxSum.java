package cn.zqtao.learn.nowcode_other.day3;

/**
 * @auther: zqtao
 * @description:
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
