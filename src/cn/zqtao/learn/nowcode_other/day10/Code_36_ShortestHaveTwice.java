package cn.zqtao.learn.nowcode_other.day10;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 连续Str子串的最短字符串
 * @version: 1.0
 */
public class Code_36_ShortestHaveTwice {

    public static String answer(String str) {
        if (str == null || str.length() == 0) return "";
        char[] chars = str.toCharArray();
        if (chars.length == 1) return str + str;
        if (chars.length == 2) // ab -->  aba是最短的
            return chars[0] == chars[1] ? (str + String.valueOf(chars[0])) : str + str;
        int endNext = endNextLength(chars);
        return str + str.substring(endNext);
    }

    // 求next[]
    // next[] 求每一个字符的最长前缀和最长后缀的匹配数
    private static int endNextLength(char[] chars) {

        int[] next = new int[chars.length + 1];
        next[0] = -1;
        next[1] = 0; // 人为规定0：-1 ，1:0
        int position = 2; // 指向chars[] 的第position位置的元素
        int cn = 0; // 指向next[] 最长前缀和后缀匹配位置
        while (position < next.length) {
            if (chars[position - 1] == chars[cn]){
                next[position++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[position++] = 0;
            }
        }

        System.out.println(Arrays.toString(next));
        return next[next.length - 1];
    }


    /**
     * KMP算法
     * KMP算法 解决 str1 中是否包含 str2
     * 包含返回 str2 开始位置，不包含返回 -1
     *
     * 1、暴力方法：以str1 的每一个字符去 匹配str2 的每一个字符，str1长N, str2长M,时间复杂度 O(M*N)
     * 2、KMP算法 str1长N, str2长M 可以优化到 O(N) N > M
     *
     * 此题仅仅使用了KMP 算法中的 next[] 的应用
     */
    public static void main(String[] args) {
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abcdabcd";
        System.out.println(answer(test4));

        String test5 = "abracadabra";
        System.out.println(answer(test5));
        System.out.println("abracadabra".substring(4)); // 保留 i+1~length-1位置
    }
}
