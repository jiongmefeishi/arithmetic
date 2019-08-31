package cn.zqtao.learn.nowcode_other.day10;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_37_Parentheses {
    /**
     * 维护一个sum 变量，遇到 ) ,sum++，遇到 ( sum--
     *
     * 思路：
     * 求每一个左括号，右括号比左括号多多少
     * ( ( ( ) ) )
     * 1 2 3
     *
     * 从右向左进行遍历，维护一个sum ， 遇到右括号++，左括号--
     *
     * 查看一个单独的合法括号序列移除方案
     * ( ( ( ) ) )
     * 第一个( 可以支配的右括号是3个
     * 第二个( 可以支配的右括号是2个
     * 第三个( 可以支配的右括号是1个
     *
     * ( ( ( ) ) )
     * 3 2 1
     *
     * 所以这个合法括号序列移除方案是3*2*1个
     * 其实统计出每一个左括号位置，右括号比左括号多多少，逆序就是每一个( 可以支配的括号数
     */
    public static int possibilities(String parentheses) {
        char[] chars = parentheses.toCharArray();
        int ans = 1;
        int sum = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ')'){
                sum++;
            } else {
                ans *= sum--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(possibilities("((()))")); // 6
        System.out.println(possibilities("()(())")); // 2
        System.out.println(possibilities("()()()")); // 1
    }
}
