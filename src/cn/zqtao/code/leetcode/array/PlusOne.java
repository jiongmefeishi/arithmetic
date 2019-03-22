package cn.zqtao.code.leetcode.array;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * @version: 1.0
 */
public class PlusOne {
    public static void main(String[] args) {
//        int[] nums = {4,3,2,1};
//        int[] nums = {9,9};
        int[] nums = {0};
        System.out.println(Arrays.toString(plusOne(nums)));
    }

    /**
     * 判断digits的最后一位，如果他小于9，则直接加1，并且直接输出;
     * 如果不是，则将当前位置0；
     * 重复3操作，判断他的前一位；
     * 如果每一位都是9，则将每一位置0；
     * 将数组digits后移一位，并且在digits的最高位置1；
     * @param digits
     */
    public static int[] plusOne(int[] digits) {
        if (digits.length == 0) return new int[0];

        // 如果每一位都是9    如9 9 9 则循环后数组为 0 0 0
        // 其余情况则直接返回digits数组
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                // 小于9 加1 直接返回
                digits[i] = digits[i] + 1;
                return digits;
            } else {
                // 若为9，置0
                digits[i] = 0;
            }
        }

        System.out.println("全为9循环后数组情况： " + Arrays.toString(digits));

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
