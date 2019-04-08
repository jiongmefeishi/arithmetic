package cn.zqtao.code.leetcode.string;

import java.io.BufferedReader;
import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 最长公共前缀
 * @version: 1.0
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int i;
        boolean samePre = true;
        for (i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()){
                    samePre = false;
                    break;
                }
                char c1 = strs[j].charAt(i);
                if (c != c1) {
                    samePre = false;
                    break;
                }
            }
            if (!samePre) break;
        }

        return strs[0].substring(0, i);
    }

    public static void main(String[] args) {
//        System.out.println("abc".substring(0, 2));

        String[] strs = {"flower", "flow", "flight"};
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));

        String[] strs1 = {"aa", "a", "a"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs1));
    }

}
