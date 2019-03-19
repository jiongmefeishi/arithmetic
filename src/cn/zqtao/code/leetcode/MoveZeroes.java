package cn.zqtao.code.leetcode;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * @version: 1.0
 */
public class MoveZeroes {
    public static void main(String[] args) {
//        int[] nums = {0,1,0,3,12};
        int[] nums = {4,2,4,0,0,3,0,5,1,0};
//        int[] nums = {0, 0};
        moveZeroes(nums);
    }

    /**
     *       --> 4 2 4 0 0 3 0
     * i = 0 --> 4 2 4 0 0 3 0   zeroNum = 0
     * i = 1 --> 4 2 4 0 0 3 0   zeroNum = 0
     * i = 2 --> 4 2 4 0 0 3 0   zeroNum = 0
     *
     * i = 3 --> 4 2 4 0 0 3 0   zeroNum = 1
     * i = 4 --> 4 2 4 0 0 3 0   zeroNum = 2
     *
     * i = 5 --> 4 2 4 3 0 0 0   zeroNum = 2    i - zero = 3
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if (nums.length == 0) return;
        // 记录0个数
        int zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            } else if (zeroNum != 0) {
                // 如果nums[i] != 0 && zeroNum != 0
                nums[i - zeroNum] = nums[i];
                nums[i] = 0;
            }
        }

        System.out.println("输出: " + Arrays.toString(nums));
    }
}
