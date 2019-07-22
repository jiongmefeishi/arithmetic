package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_43_Print_All_Subsquences {

    /**
     * @param chars 字符数组
     * @param i 当前字符下标
     * @param pre 上一个递归，给定的结果
     */
    public static void printAllSubString(char[] chars, int i, String pre){
        if (i == chars.length) {
            // 字符数组中每一个字符已经选择完毕
            System.out.println(pre);
            return;
        }

        printAllSubString(chars, i + 1, pre + chars[i]); // 选择要当前字符
        printAllSubString(chars, i + 1, pre + "_"); // 选择不要当前字符
    }

    public static void main(String[] args) {
        String str = "abc";
        printAllSubString(str.toCharArray(), 0, "");
    }
}
