package cn.zqtao.code.leetcode.other;

/**
 * @auther: zqtao
 * @description: 最大连续子序列问题
 *
 * 问题定义：
 * 给定K个整数的序列{ N1, N2, ..., Nk }，
 * 其任意连续子序列可表示为{ Ni, Ni+1, ..., Nj }，
 * 其中 1 <= i <= j <= K。最大连续子序列是所有连续子序列中元素和最大的一个，
 * 例如给定序列{ -2, 11, -4, 13, -5, -2 }，
 * 其最大连续子序列为{ 11, -4, 13 }，
 *
 * 最大和为20
 */
public class MaxSubArray {

    /**
     * 解法2：分治算法, 时间复杂度：O(nlogn)
     * 对于任意一个序列{a1, a2, ...,am,.... an}, ( m=(n+1)/2 ) 最大的连续子序列在该序列中的位置存在三种情况:
     *
     * 位于中间部分的左边;
     * 位于中间部分的右边 ;
     * 左边和右边都含有最大的连续子序列的一部分, e.g. ai, ..., am, ...., aj.
     *
     * 对于情况1,2,  使用递归算法可以轻松计算出；
     * 对于情况3， 则通过求出前半部分的最大和（包含前半部分的最后一个元素）
     * 以及后半部分的最大和（包含后半部分的第一个元素）而得到，
     * 然后将这两个和加在一起，
     * 最后，三种情况中最大的结果就是要求的结果。
     */
    public int maxSubArray2 (int[] nums) {
        return maxSub2(nums, 0, nums.length - 1);
    }

    public int maxSub2(int[] nums, int left, int right){
        int maxLeftSum;
        int maxRightSum;
        int mid;

        // 只有一个元素的子序列
        if (left == right){
            if (nums[left] > 0)
                return nums[left];
            else return 0; // 处理小于0 的元素
        }

        mid = (left + right) / 2;
        // 情况 1
        maxLeftSum = maxSub2(nums, left, mid);
        // 情况 2
        maxRightSum = maxSub2(nums, mid + 1, right);

        // 情况 3
        // 求解最大序列的左边部分
        int leftBorderSum = 0;
        int maxLeftBorderSum = 0;
        for (int i = mid; i >= left; i--) {
            leftBorderSum += nums[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        // 求解最大序列的右边部分
        int rightBorderSum = 0;
        int maxRightBorderSum = 0;
        for (int i = mid + 1; i <= right ; i++) {
            rightBorderSum += nums[i];
            if (rightBorderSum > maxRightBorderSum)
                maxLeftBorderSum = rightBorderSum;
        }

        return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBorderSum + maxRightBorderSum);

    }

    // 解法1：朴素解法, 时间复杂度 O(K^2)
    public int maxSubArray1(int[] nums) {

        //假设给定序列：a1,a2,...,aK

        int K = nums.length;
        int maxSum = 0; // 最大和
        for (int i = 0; i < K; i++) {
            int tmpSum = 0;
            for (int j = 0; j < K; j++) {
                tmpSum += nums[j];
                if (tmpSum > maxSum)
                    maxSum = tmpSum;
            }
        }

        return maxSum;
    }
}
