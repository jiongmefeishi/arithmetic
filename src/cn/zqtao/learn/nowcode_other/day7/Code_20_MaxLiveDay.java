package cn.zqtao.learn.nowcode_other.day7;

/**
 * @auther: zqtao
 * @description: 独立的小易
 * @version: 1.0
 */
public class Code_20_MaxLiveDay {

    /**
     * @param x 每天需要支付的房租
     * @param f 手里已经有的水果数
     * @param d 拥有的钱
     * @param p 每个水果售价
     */
    public static int liveDay(int x, int f, int d, int p) {
        // 在水果够吃的情况下，唯一的开销是房租
        if (f >= (d / x)) {
            return d / x;
        }
        return f + (d - f * x) / (p + x);
    }

    public static void main(String[] args) {
        System.out.println(liveDay(3, 5, 100, 10));
    }
}
