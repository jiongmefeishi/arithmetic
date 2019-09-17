package cn.zqtao.examintion;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class Code_08_LetterCombinations {
    public static LinkedList<String> getRes(String str){
        if (str == null ||str.length() == 0) return new LinkedList<>();
        String[] strings = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> res = new LinkedList<>();
        res.add("");
        for (int i = 0; i < str.length(); i++) {
            int index = Character.getNumericValue(str.charAt(i));
            while (res.peek().length() == i) {
                String tmp = res.remove();
                for(char c : strings[index].toCharArray()){
                    res.add(tmp + c);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(getRes(line));
        }
        in.close();
    }
}