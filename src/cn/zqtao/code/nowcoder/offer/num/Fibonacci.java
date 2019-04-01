package cn.zqtao.code.nowcoder.offer.num;

/**
 * @auther: zqtao
 * @description: 剑指offer-斐波那契数列
 * @version: 1.0
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(new Fibonacci().getFibonacci(8));
        System.out.println(new Fibonacci().getFibonacci(7));
    }

    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     * @param n
     * @return
     */
    public int getFibonacci(int n) {
        if (n > 39 || n <= 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int pre = 1;
        int next = 1;
        int num = 0;
        while (n-- > 2){
            num = next + pre;
            pre = next;
            next = num;
        }

        return num;
    }
}
