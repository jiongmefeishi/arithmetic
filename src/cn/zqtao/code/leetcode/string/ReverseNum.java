package cn.zqtao.code.leetcode.string;

/**
 * @auther: zqtao
 * @description: 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @version: 1.0
 */
public class ReverseNum {
    public static void main(String[] args) {
        test();
        System.out.println(reverse(-123));
    }

    /**
     * 方法：弹出和推入数字 & 溢出前进行检查
     * 思路
     *
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     *
     * 算法
     *
     * 反转整数的方法可以与反转字符串进行类比。
     *
     * 我们想重复“弹出” xx 的最后一位数字，并将它“推入”到 \text{rev}rev 的后面。最后，\text{rev}rev 将与 xx 相反。
     * @param x
     * @return
     */
    public static int reverse(int x) {
        // 反转结果
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            // 最大次反转  rev * 10 + pop 其中当 rev * 10 恰好等于 2147483640 时，pop 应该小于 7
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    // 测试边界
    public static void test(){
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); // -2147483648

        // 最大次反转  rev * 10 + pop 其中当 rev * 10 恰好等于 2147483640 时，pop 应该小于 7
    }
}
