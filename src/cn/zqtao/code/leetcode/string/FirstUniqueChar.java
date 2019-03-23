package cn.zqtao.code.leetcode.string;

/**
 * @auther: zqtao
 * @description: 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 * @version: 1.0
 */
public class FirstUniqueChar {
    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }

    /**
     * 分析：该字符串只包含小写字母。
     * 即只存在26种小写字母
     * 申明一个数组，记录每一种
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        if (s == null || "".equals(s)) return -1;

        char[] chars = s.toCharArray();
        int[] res = new int[26];

        // 存
        for (int i = 0; i < chars.length; i++) {
            res[chars[i] - 'a']++;
        }
        // 第一个个数为1的即字符串中的第一个唯一字符
        for (int i = 0; i < chars.length; i++) {
            if (res[chars[i] - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
