package cn.zqtao.code.nowcoder.offer.String;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuffer("We Are Happy.")));
        System.out.println(replaceSpace(new StringBuffer()));
    }

    // 利用StringBuffer 类型原理是 char[] 来进行替换
    public static String replaceSpace2(StringBuffer str) {
        if (str == null) return null;

        StringBuffer res = new StringBuffer();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                res.append("%20");
                continue;
            }
            res.append(str.charAt(i));
        }

        return res.toString();
    }


    // 利用封装好的方法替换
    public static String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) return str.toString();
        String res = str.toString().replaceAll(" ", "%20");
        return res;
    }

}
