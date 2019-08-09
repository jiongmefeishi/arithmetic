package cn.zqtao.learn.nowcode_other.day6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @auther: zqtao
 * @description: 彩砖拼色
 * 小易有一些彩色的砖块。每种颜色由一个大写字母表示。各个颜色砖块看起来都完全一样。
 *
 * 现在有一个给定的字符串s,s中每个字符代表小易的某个砖块的颜色。
 *
 * 小易想把他所有的砖块排成一行。
 *
 * 如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的。
 *
 * 请你帮助小易计算有多少种方式将他所有砖块排成漂亮的一行。
 * (如果两种方式所对应的砖块颜色序列是相同的,那么认为这两种方式是一样的。)
 *
 * 例如: s= "ABAB",那么小易有六种排列的结果:
 * 'AABB", "ABAB", "ABBA", "ВAAВ", " ВАВА ", " ВВАА"
 * 其中只有"AABB"和"BBAA"满足最多只有一对不同颜色的相邻砖块。
 *
 */
public class Code_15_ColorBrik {

    public static int getColorBrik(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();

        int res = 0;
        for (char c : str.toCharArray()) {
            if (!set.contains(c)){
                set.add(c);
                res++;
            }
        }
        return res > 2 ? 0 : res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(getColorBrik(str));
        in.close();
    }
}
