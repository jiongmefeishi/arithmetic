package cn.zqtao.code.leetcode.array;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 962. 最大宽度坡
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 平均星级：4.64 (14次评分)
 * <p>
 * 2019年2月15日  |  1.7K 次预览
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 * <p>
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 * 示例 2：
 * <p>
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 * @version: 1.0
 */
public class MaxWidthRamp {

    /**
     * 解决方案
     * 方法一：排序
     * 思路与算法
     * <p>
     * 对于每一个形如 A[i] = v 的元素，我们将其索引 i 按照对应值 v 排序之后的顺序写下。
     * 例如， A[0] = 7, A[1] = 2, A[2] = 5, A[3] = 4，
     * 我们应该这样顺序写下索引值 i=1, i=3, i=2, i=0。
     * <p>
     * 然后，当我们写下一个索引 i 的时候，我们可以得到候选的宽度答案 i - min(indexes_previously_written)
     * （如果这个值是正数的话）。 我们可以用一个变量 m 记录已经写下的最小索引。
     */
    public int maxWidthRamp(int[] A) {
        int N = A.length;
        Integer[] B = new Integer[N];
        for (int i = 0; i < N; ++i)
            B[i] = i;

        Arrays.sort(B, (i, j) -> ((Integer) A[i]).compareTo(A[j]));

        int ans = 0;
        int m = N;
        for (int i : B) {
            ans = Math.max(ans, i - m);
            m = Math.min(m, i);
        }

        return ans;
    }
}
