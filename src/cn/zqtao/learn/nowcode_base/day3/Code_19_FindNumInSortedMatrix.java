package cn.zqtao.learn.nowcode_base.day3;

/**
 * @auther: zqtao
 * @description: 在行列都排好序的矩阵中找数
 *
 * 【题目】
 * 给定一个有N*M的整型矩阵matrix和一个整数K， matrix的每一行和每一 列都是排好序的。
 * 实现一个函数，判断K 是否在matrix中。
 * 例如：
 * 0   1   2   5
 * 2   3   4   7
 * 4   4   4   8
 * 5   7   7   9
 * 如果K为7，返回true；
 * 如果K为6，返 回false。
 * 【要求】 时间复杂度为O(N+M)，额外空间复杂度为O(1)。
 * @version: 1.0
 */
public class Code_19_FindNumInSortedMatrix {

    public static boolean findNumInSoftedMatrix(int[][] matrix, int k){
        if (matrix == null || matrix.length == 0) return false;

        // 从右上角点出发开始遍历查找 (R, C)
        int R = 0;
        int C = matrix[0].length - 1;

        // 终止条件是查找到目标数或者当前点到达了左下角的最后一个点依然没有找到目标数，停止
        while (R < matrix.length && C > -1){
            if (matrix[R][C] > k)
                C--;
            else if (matrix[R][C] < k)
                R++;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 233;
        System.out.println(findNumInSoftedMatrix(matrix, K));
    }
}
