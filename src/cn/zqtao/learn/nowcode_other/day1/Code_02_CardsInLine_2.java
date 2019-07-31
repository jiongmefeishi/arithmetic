package cn.zqtao.learn.nowcode_other.day1;

import static cn.zqtao.learn.nowcode_other.day1.Code_01_CardsInLine_1.win1;

/**
 * @auther: zqtao
 * @description: 两人选数游戏
 *
 * 时间复杂度 O(N ^ 2)
 *
 * 思路2：从暴力递归到动态规划
 * <p>
 * 暴力递归其实就是一个暴力尝试过程，尝试每一种选择
 * 缺点：重复的大量计算，而且这种重复计算随着样本量的增加而指数式增加，做了很多无用功
 * <p>
 * 动态规划：
 * 1、写出暴力尝试
 * 2、确定最终解，是什么点
 * 3、查看暴力尝试过程中的计算解，是否是完全无后效性的
 * 4、找到可以代替解的变量
 * 5、base case 给表赋值
 * 6、一般情况的依赖关系
 * <p>
 * 1、暴力尝试：win1()
 * 2、确定最终解：(0, N-1) 点
 * 3、win1() 中 f(arr, i, j)  arr 是固定值，f(i , j) 是无效性的
 * 4、可以用 i , j  表示解
 * 5、根据递归中的base case 来给表赋值（不变值，基本情况下的值，如本题 i==j 时）
 * 6、一般情况下的依赖关系
 *      first() (i,j) 依赖于second() 中的 (i+1, j) 和 (i, j-1)
 *      second() (i,j) 依赖于 first() 中的 (i+1,j) 和 (i, j-1)
 * <p>
 * <p>
 * 暴力尝试可以使用表进行表示
 * dpf 可以保存所有 first() 的所有情况
 * dps 可以保存所有 second() 的所有情况
 * <p>
 * first() 中
 * @version: 1.0
 */
public class Code_02_CardsInLine_2 {

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        // 保存各种状态
        int[][] dpf = new int[arr.length][arr.length];
        int[][] dps = new int[arr.length][arr.length];

        for (int j = 0; j < arr.length; j++) {

            dpf[j][j] = arr[j]; // first() 中 base case 中 i==j 情况赋值
//            dps[j][j] = 0; // second() 中 base case 中 i==j 情况，由于Java是自动给数组初始化为0 的所以可以忽略

            for (int i = j - 1; i >= 0; i--) {
                // 一般情况依赖关系
                // first() (i,j) 依赖于second() 中的 (i+1, j) 和 (i, j-1)
                dpf[i][j] = Math.max(arr[i] + dps[i + 1][j], arr[j] + dps[i][j - 1]);

                // second() (i,j) 依赖于 first() 中的 (i+1,j) 和 (i, j-1)
                dps[i][j] = Math.min(dpf[i + 1][j], dpf[i][j - 1]);
            }

        }
        return Math.max(dpf[0][arr.length - 1], dps[0][arr.length - 1]);
    }


    public static int[] generateRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 5;
        int testTime = 50000;

        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArr(maxSize, 20);
            int r1 = win1(arr);
            int r2 = win2(arr);
            if (r1 != r2) {
                System.out.println("error");
            }
        }
    }
}