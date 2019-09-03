package cn.zqtao.learn.nowcode_other.day11;

/**
 * @auther: zqtao
 * @description: 子矩阵最大累加和
 * N*M 矩阵
 * 时间复杂度可以达到 O(N*N*M)
 * @version: 1.0
 */
public class Code_44_MaxSumSubMatrix {

    public static int maxSubMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] arr = null;
        for (int i = 0; i < matrix.length; i++) { // 控制需要以仅包含 i 层数组开始，求最大累加和
            arr = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) { // 控制 i~N-1 层逐次降维
                cur = 0;
                for (int k = 0; k < arr.length; k++) { // 降维后，求一位数组的最大累加和
                    arr[k] += matrix[j][k];
                    cur += arr[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSubMatrix(matrix));
    }
}
