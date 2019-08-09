package cn.zqtao.learn.nowcode_other.day6;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 01 串
 * 实例3：01串
 *
 * 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。
 *
 * 例如: "1","10101","0101010"都是交错01串。
 *
 * 小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。
 *
 * 输入描述:
 *
 * 输入包括字符串s,
 *
 * s的长度length(1 < length < 50),字符串中只包含'0'和'1
 *
 * 题目三扩展小易想找出一个最长的连续子串,并且这个子串是0和1数量相等的。该怎么做?
 * @version: 1.0
 */
public class Code_17_01String {

    public static int max01Str(String str) {
        if (str == null || str.length() == 0) return 0;

        int max = 1;
        int len = 1;
        for (int i = 0; i < str.length(); i++) {
            len++;
            if (str.charAt(i) == str.charAt(i-1)){
                len = 1;
            }
            max = Math.max(max, len);
        }
        return max;
    }

    public static int max01Str2(String str) {
        if (str == null || str.length() == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int len = 0;
        int k = 0; // 求满足子数组累加等于0 的子数组长度

        for (int i = 0; i < str.length(); i++) {
            int cNum = (int) str.charAt(i) - '0';
            sum += cNum;
            if (map.containsKey(sum - k)){
                len = Math.max(len, i - map.get(sum - k));
            }
            if (!map.containsKey(sum - k)){ // 不存在就添加
                map.put(sum, i);
            }
        }
        return len;
    }
}
