package cn.zqtao.learn.nowcode_other.day910;

/**
 * @auther: zqtao
 * @description:
 *
 * 1 22 333 4444 5555
 *
 * 每个连续数字的结尾
 * 1:1
 * 2:3
 * 3:6
 * 4:10
 * 5:15
 *
 * 每一个结尾，在这里可以看做是等差数列的求和
 * 例如
 * 6：是1+2+3
 * 15:是1+2+3+4+5
 * 即每个连续数的结尾数，是这个连续数的单数等差数列的和
 * 等差数列求和公式 S(n)= n*(n+1)/2
 *
 * 现在知道了这个 S(n) --> K 来反推这个连续数
 */
public class Code_34_NNum {

    public static int getNum(long n) {
        return (int) Math.ceil((Math.sqrt(1 + 8 * ((double) n)) - 1) / 2);
    }
}
