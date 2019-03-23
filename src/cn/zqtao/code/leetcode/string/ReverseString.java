package cn.zqtao.code.leetcode.string;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * @version: 1.0
 */
public class ReverseString {
    public static void main(String[] args) {
//        char[] s = {'h','e','l','l','o'};
        char[] s2 = {'1', '2', '3', '4', '5'};
//        reverseString(s2);
        reverseString2(s2);
    }

    /**
     * tmp : 5
     * [5, 1, 2, 3, 4]
     * tmp : 4
     * [5, 4, 1, 2, 3]
     * tmp : 3
     * [5, 4, 3, 1, 2]
     * tmp : 2
     * [5, 4, 3, 2, 1]
     * tmp : 1
     * [5, 4, 3, 2, 1]
     * [5, 4, 3, 2, 1]
     *
     * 自右向左
     * @param s
     *
     * 699 ms	53.1 MB
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) return;

        for (int i = 0; i < s.length; i++) {
            // 取最后一个元素临时存储，其余元素向右推进一位
            char tmp = s[s.length - 1];
            System.out.println("tmp : " + tmp);
            for (int j = s.length - 1; j > i; j--) {
                s[j] = s[j - 1];
            }
            // 将临时元素赋值给 i 对应元素
            s[i] = tmp;
            System.out.println("每次循环退位结果： " + Arrays.toString(s));
        }
        // 最终结果
        System.out.println(Arrays.toString(s));
    }


    /**
     * 优化
     * 双指针遍历
     *
     * 	32 ms	57.4 MB
     */
    public static void reverseString2(char[] s) {
        if (s == null || s.length == 0) return;
        int start = 0;
        int end = s.length - 1;

        while (start < end){
            // 交换
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;

            start++;
            end--;
        }

        System.out.println(Arrays.toString(s));
    }

}
