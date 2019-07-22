package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description: n 阶乘问题
 * @version: 1.0
 */
public class Code_41_Recur_Factorial {

    // recur
    public static long getFactorialByRecur(int n) {
        if(n == 1) return 1;
        return (long) n * getFactorialByRecur(n - 1);
    }

    // for
    public static long getFactorialByFor(int n){
        long res = 1L;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(getFactorialByFor(n));
        System.out.println(getFactorialByRecur(n));
    }
}
