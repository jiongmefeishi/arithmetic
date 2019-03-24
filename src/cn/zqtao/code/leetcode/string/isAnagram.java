package cn.zqtao.code.leetcode.string;

/**
 * @auther: zqtao
 * @description: 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * @version: 1.0
 */
public class isAnagram {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram("rat", "car"));
    }

    /**
     * 分别统计两个字符串中字母的个数
     * 字符串中只包含小写字母
     * <p>
     * 使用 长度为26 的数组记录个数
     * <p>
     * 比较两个数组结果不一样则返回 false
     */
    public static boolean isAnagram(String s, String t) {
        if (s != null && t != null) {
            char[] schars = s.toCharArray();
            char[] tchars = t.toCharArray();
            int[] scharNum = getCharNum(schars);
            int[] tcharNum = getCharNum(tchars);

            for (int i = 0; i < scharNum.length; i++) {
                if (scharNum[i] != tcharNum[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    public static int[] getCharNum(char[] c) {
        int[] chars = new int[26];

        for (int i = 0; i < c.length; i++) {
            chars[c[i] - 'a']++;
        }
        return chars;
    }
}
