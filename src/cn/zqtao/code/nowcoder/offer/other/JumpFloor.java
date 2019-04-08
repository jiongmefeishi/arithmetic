package cn.zqtao.code.nowcoder.offer.other;

/**
 * @auther: zqtao
 * @description: 变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @version: 1.0
 */
public class JumpFloor {

    public static void main(String[] args) {
        int sum = 0;
        System.out.println(jumpFloor(0, 2));
    }

    /**
     * 暴力法，使用递归方法来模拟爬楼梯时是前进2 还是 1
     * @param i 当前所在阶数
     * @param n 目标阶数
     *
     *          是时间复杂度 2的n 次方
     *          空间复杂度：O(n)。递归树的深度可以达到 n 。
     */
    public static int jumpFloor(int floorIndex, int target) {

        if (floorIndex > target)
            return 0;
        if (floorIndex == target)
            return 1;
        return jumpFloor(floorIndex + 1, target) + jumpFloor(floorIndex + 2, target);
    }
}
