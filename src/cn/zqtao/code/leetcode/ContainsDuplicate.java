package cn.zqtao.code.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: zqtao
 * @description:存在重复
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * @version: 1.0
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
//        test();
        int[] nums = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicate(nums));
    }

    /**
     * set集合存储不同数据
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) return false;
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!res.add(nums[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 测试Set集合
     */
    public static void test(){

        Set<Integer> res = new HashSet<>();
        System.out.println(res.add(1));// true
        System.out.println(res.add(1));// false
    }
}
