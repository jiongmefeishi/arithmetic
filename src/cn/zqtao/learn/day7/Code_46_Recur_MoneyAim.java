package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description: 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的 数字，能不能累加得到aim，返回true或者false
 * 类似考题就是给定几种面额值的钞票，让你选择几张求 金额
 * @version: 1.0
 */
public class Code_46_Recur_MoneyAim {

    /**
     * @param arr
     * @param i 当前位置下标
     * @param sum 当前总数
     * @param aim 目标数
     * @return 是否能凑齐
     */
    public static boolean isSum(int[] arr, int i, int sum, int aim){
        if (i == arr.length) { // 表示已经到达最后位置
            return sum == aim;
        }

        if (sum == aim) {
            return true;
        }

        // 加上当前值，和不加上当前值，任意一个满足即可
        return isSum(arr, i + 1, sum, aim) || isSum(arr, i + 1, sum + arr[i], aim);
    }
}
