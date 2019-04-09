package cn.zqtao.code.leetcode.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @auther: zqtao
 * @description: 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @version: 1.0
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) throws IOException {
//        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("bbbb"));
//        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));
//        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(" "));

        // 输入测试
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()){
//            String str = scanner.next();
//            System.out.println(str);
//            System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring2(str));
//        }

        // 上述输入无法读取空格
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("line = " + line);
            System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring2(line));
        }
    }

    /**
     * 滑动窗口优化
     *
     * 如果 s[j] 在 [i, j) 范围内有于 j` 重复的字符，不需要逐渐增加 i
     * 可以直接就跳过 [i, j`] 中的所有元素，并将i 变为 j` + 1
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() <= 0) return 0;

        int maxLength = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int start = 0, end = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end)))
                start = Math.max(map.get(s.charAt(end)), start);
            maxLength = Math.max(maxLength, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }

        return maxLength;
    }

    /**
     * 滑动窗口
     * <p>
     * 使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。
     * 然后我们向右侧滑动索引 j，
     * 如果它不在 HashSet 中，我们会继续滑动 j。直到 s[j] 已经存在于 HashSet 中。
     * 此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 0) return 0;

        int maxLength = 0;
        Set<Character> str = new HashSet<>();

        // 窗口长度 end - start
        // 滑动窗口左区间
        int start = 0;
        // 滑动窗口右区间
        int end = 0;

        while (start < s.length() && end < s.length()) {
            if (!str.contains(s.charAt(end))) {
                str.add(s.charAt(end++));
                // 比较之前存储的maxLength 和 现在窗口长度大小
                maxLength = Math.max(maxLength, end - start);
            } else {
                // 反之，去掉窗口左区间
                str.remove(s.charAt(start++));
            }
        }
        return maxLength;
    }

    /**
     * 时间复杂度 > O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) return 0;

        int maxLength = 0;
        Set<Character> str = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // 当重复时，获取set中长度最为maxLength, 同时清空Set
                if (!str.add(s.charAt(j))) {
                    if (maxLength < str.size())
                        maxLength = str.size();
                    str.clear();
                    break;
                }
            }
        }

        // 如果字符串无重复字符，返回set长度
        return maxLength == 0 ? str.size() : maxLength;
    }

}
