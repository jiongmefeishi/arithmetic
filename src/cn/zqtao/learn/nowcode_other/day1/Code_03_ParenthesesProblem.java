package cn.zqtao.learn.nowcode_other.day1;

/**
 * @auther: zqtao
 * @description: 括号问题
 * 时间复杂度：O(N)
 * @version: 1.0
 */
public class Code_03_ParenthesesProblem {

    // 是否是有效括号
    public static boolean isValidParentheses(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }

        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (')' == chars[i] && --count < 0) {
                return false;
            }
            if ('(' == chars[i]) {
                count++;
            }
        }
        return count == 0;
    }

    // 有效最大长度
    public static int maxLength(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int res = 0;

        int[] dp = new int[chars.length];
        int pre = 0; // 和当前 ')' 对应的 '('的位置下标

        for (int i = 1; i < chars.length; i++) { // 无论是什么括号，dp[0] 一定是0
            if (')' == chars[i]) {
                pre = i - dp[i - 1] - 1; // 根据第 i-1 位，进行前推 dp[i-1] - 1 个
                if (pre >= 0 && chars[pre] == '(') {
                    int preLenth = pre > 0 ? dp[pre - 1] : 0; // 之前有效的长度, 注意：pre <= 0 的存在
                    dp[i] = (dp[i - 1] + 2) + preLenth;
                }
            }

            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValidParentheses(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValidParentheses(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValidParentheses(str3));
        System.out.println(maxLength(str3));

    }
}