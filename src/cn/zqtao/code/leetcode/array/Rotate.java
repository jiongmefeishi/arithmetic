package cn.zqtao.code.leetcode.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Rotate {
    public static void main(String[] args) {

        System.out.println( 7 % 3);

        int[] nums = {1,2,3,4,5,6,7};
        new Rotate().rotate2(nums, 3);

        int[] nums2 = {1,2,3};
        new Rotate().rotate(nums2, 7);

        Scanner scanner = new Scanner(System.in);

//        while (scanner.hasNext()){
//
//        }
    }

    /**
     * 双重for循环
     * 1,2,3,4,5,6,7     k = 3
     * 第一次循环：7,1,2,3,4,5,6
     * 第二次循环：6,7,1,2,3,4,5
     * 第三次循环：5,6,7,1,2,3,4
     */
    public void rotate(int[] nums, int k) {

        if (nums == null || k == 0) return;

        // 去重
        // 如果 k = 7, nums.length = 3;
        // 取余后 k = 1, 只需要遍历一次即可得到结果
        k %= nums.length;
        for (int i = 0; i < k; i++) {
            // 每次取出最后一位元素，同时将其他元素向后移动一位
            int tmp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--){
                nums[j] = nums[j - 1];
            }

            // 将取出的元素，赋值给首位
            nums[0] = tmp;
        }

        System.out.println(Arrays.toString(nums));
    }

    /**
     * 翻转
     */
    public void rotate2(int[] nums, int k) {
        // 去重
        k %= nums.length;

        // 旋转数组的前半部分
        r(nums, 0, k);
        // 旋转数组的后半部分
        r(nums, k + 1, nums.length - 1);
        // 旋转整个数组
        r(nums, 0, nums.length - 1);

        System.out.println(Arrays.toString(nums));
    }

    /**
     * 翻转数组
     * @param i 开始位置
     * @param j 结束位置
     */
    private void r(int[] nums, int i, int j){

        while (i < j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;

            i++;
            j--;
        }
    }
}
