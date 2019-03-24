package cn.zqtao.code.leetcode.string;

/**
 * @auther: zqtao
 * @description: 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 * @version: 1.0
 */
public class StrToNum {
    public static void main(String[] args) {
//        System.out.println(myAtoi("-4193 with words"));
//        System.out.println(myAtoi("words and 987"));
//        System.out.println(myAtoi("-91283472332"));
//        System.out.println(myAtoi("  -42")); // -42
//        System.out.println(myAtoi("+-12 6"));  // 0
//        System.out.println(myAtoi("-+12"));
//        System.out.println(myAtoi("2147483646"));
//        System.out.println(myAtoi("-2147483648"));
        System.out.println(myAtoi3("-2147483648"));
//        test();
    }

    /**
     * @version 3.0
     * 简略代码
     */
    public static int myAtoi3(String str) {
        // 非空
        if (str == null || "".equals(str.trim()))
            return 0;
        str = str.trim();
        System.out.println("输入： " + str);
        char[] chars = str.toCharArray();

        int res = 0;
        // 标记数据正负性
        int flag = 1;
        for (int i = 0; i < chars.length; i++) {
            // 判断第一个非空字符,并确定正负形
            if (chars[i] == '+' && i == 0) continue;
            if (chars[i] == '-' && i == 0) {
                flag = -1;
                continue;
            }

            // 判断当前元素是否为整数, 不是返回之前的结果
            if (chars[i] < '0' || chars[i] > '9') return res;
            // 判断边界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && chars[i] > '7'))
                return Integer.MAX_VALUE;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -chars[i] < -'8'))
                return Integer.MIN_VALUE;

            res = res * 10 + (chars[i] - '0') * flag;
        }
        return res;
    }

    /**
     * @version 2.0
     * 要点
     * 1、第一个非空格字符
     * 2、第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号
     * 3、假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 4、该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略
     * 5、在任何情况下，若函数不能进行有效的转换时，请返回 0
     * 6、如果数值超过边界，返回  INT_MAX (231 − 1) 或 INT_MIN (−231)
     * 特殊情况
     * ++ 123
     * +-123
     */
 /*   public static int myAtoi(String str) {
        // 非空
        if (str == null || "".equals(str.trim()))
            return 0;
        str = str.trim();
        System.out.println("输入： " + str);
        char[] chars = str.toCharArray();

        int res = 0;
        // 标记数据正负性
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            // 判断第一个非空字符,并确定正负形
            if (chars[i] == '+' && i == 0) continue;
            if (chars[i] == '-' && i == 0) {
                flag = false;
                continue;
            }

            // 判断当前元素是否为整数, 不是返回之前的结果
            if (chars[i] < '0' || chars[i] > '9') return res;
            // 判断边界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && chars[i] > '7'))
                return Integer.MAX_VALUE;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -chars[i] < -'8'))
                return Integer.MIN_VALUE;

            // 正负性
            if (flag) {
                res = res * 10 + (chars[i] - '0');
            } else {
                res = res * 10 - (chars[i] - '0');
            }
        }
        return res;
    }
*/
    /**
     * @version 1.0
     * 淘汰：未考虑周全
     *
     * 要点
     * 1、第一个非空格字符
     * 2、第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号
     * 3、假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 4、该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略
     * 5、在任何情况下，若函数不能进行有效的转换时，请返回 0
     * 6、如果数值超过边界，返回  INT_MAX (231 − 1) 或 INT_MIN (−231)
     */
 /*   public static int myAtoi(String str) {
        System.out.println("输入： " + str);
        // 非空
        if (str == null || "".equals(str.trim()))
            return 0;
        str = str.trim();
        System.out.println("输入： " + str);
        char[] chars = str.toCharArray();
        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9'))
            return 0;

        int res = 0;
        // 标记首字母是整数
        boolean isNum = true;
        if (chars[0] == '-')
            isNum = false;
        // 整数则从0 开始，否则从 1开始遍历
        for (int i = isNum ? 0 : 1; i < chars.length; i++) {
            if (chars[i] == '+' && res == 0) continue;
            // 判断是否为整数
            if (chars[i] < '0' || chars[i] > '9') return res;
            // 判断边界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && chars[i] > 7))
                return Integer.MAX_VALUE;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && chars[i] < -8))
                return Integer.MIN_VALUE;
            if (isNum){
                res = res * 10 + (chars[i] - '0');
            } else {
                res = res * 10 - (chars[i] - '0');
            }
        }
        return res;
    }
*/

    /**
     * 测试char 0~9  转为整数 0~9
     */
    public static void test() {
        System.out.println('4'); // 4
        System.out.println(3 + '4'); // 55

        System.out.println('4' - '0'); // 4
        System.out.println(3 + ('4' - '0')); // 7
    }
}
