package cn.zqtao.learn.nowcode_other.day6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @auther: zqtao
 * @description: 彩砖拼色
 * @version: 1.0
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
