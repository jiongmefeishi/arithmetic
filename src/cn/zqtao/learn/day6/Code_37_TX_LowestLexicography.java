package cn.zqtao.learn.day6;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @auther: zqtao
 * @description: 字符串字典序拼接
 * 给你一个字符串数组，目的是把所有的字符串拼起来，不能遗漏，这样会得到很多
 * 拼接的结果，请你找到拼接的字符串，字典序最小的结果。
 * 如："ab", "cd", "ef"
 * 拼接结果
 * abcdef
 * abefcd
 *
 * cdabef
 * cdefab
 *
 * efabcd
 * efcdab
 *
 * 这六种结果，其中，字面值最小的是abcdef。
 *
 * 理解一个概念：字典序
 * 长度一样，当做26进制的数，比较大小，小在前
 * 长度不等，补全，补位为字面值最小的数 如 "abc" 和 "b" 比较，补位 "b00"。a < b
 * 所以"abc"在前，"b"在后
 *
 *
 *
 * 思路：进行字典排序
 * @version: 1.0
 */
public class Code_37_TX_LowestLexicography {

    // 贪心策略:两个字符串排序时先拼接再比较
    // str1 + str2 <= str2 + str1 ？ str1 : str2

    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String strA, String strB) {
            return (strA + strB).compareTo((strB + strA));
        }
    }

    public static String lowestLexicography(String[] str){
        if (str == null || str.length == 0)
            return "";
        Arrays.sort(str, new MyComparator());
        String res = "";
        for (int i = 0; i < str.length; i++) {
            res += str[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "abc", "bc", "ac", "aa", "abb" };
        System.out.println(lowestLexicography(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestLexicography(strs2));
    }
}
