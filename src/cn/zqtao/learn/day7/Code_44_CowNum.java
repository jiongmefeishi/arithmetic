package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description: 牛生育问题
 * @version: 1.0
 */
public class Code_44_CowNum {

    // for
    public static int cowNumByFor(int n) {
        if (n <= 3) {
            return n;
        }

        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;

            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }

        return res;
    }

    // recur
    public static int cowNumByRecur(int n) {
        if (n < 1) return 0;

        if (n >= 1 && n <= 3) {
            return n;
        }

        return cowNumByRecur(n - 1) + cowNumByRecur(n - 3);
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(cowNumByFor(n));
        System.out.println(cowNumByRecur(n));
    }

}
