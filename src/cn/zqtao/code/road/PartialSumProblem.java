package cn.zqtao.code.road;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 部分和问题 DFS
 *
 * 给你n个数(a1,a2,a3.......an) ,是否存在某一些数字加起来等于k,有就输出 "YES",否则输出 "NO"。
 *
 * 数据范围：n<20;
 *
 * a1+a2+....an在int范围里面.
 *
 * 输入
 * 多组输入
 * 每组第一行输入两个数n,k
 * 第二行输入n个数a1 a2 ...... an
 * 输出
 * 如果存在一些数加起来为k输出"YES";否则输出"NO".
 * 样例输入
 * 5 6
 * 2 3 5 2 1
 * 3 6
 * 2 3 9
 * 样例输出
 * YES
 * NO
 * ---------------------
 *
 * <p>
 * 深度优先搜索用一个数组存放产生的所有状态。
 * （1） 把初始状态放入数组中，设为当前状态；
 * （2） 扩展当前的状态，产生一个新的状态放入数组中，同时把新产生的状态设为当前状态；
 * （3） 判断当前状态是否和前面的重复，如果重复则回到上一个状态，产生它的另一状态；
 * （4） 判断当前状态是否为目标状态，如果是目标，则找到一个解答，结束算法。
 * （5） 如果数组为空，说明无解。
 * @version: 1.0
 */
public class PartialSumProblem {

    int n; // 表示输入 n 项
    int k; // 目标值
    // 存储产生的状态
    Stack<Integer> res = new Stack<>();
    int[] arr = new int[21];

    private void solution() {
        Scanner in = new Scanner(System.in);
        n = 0;
        k = 0;
        while (in.hasNext()) {
            n = in.nextInt();
            k = in.nextInt();
            System.out.println("n: " + n + "  k: " + k);
            for (int i = 0; i < n; i++)
                arr[i] = in.nextInt();

            System.out.println("n 个输入数据：" + Arrays.toString(arr)); // 查看输入数据

            if (dfs(0, 0)) {
                System.out.println("YES");
                // 打印组成点（数）
                while (!res.empty())
                    System.out.print(res.pop() + "    ");
                System.out.println();
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * 深度优先搜索，
     *
     * @param i   第 i 项
     * @param sum 前 i 项和
     * @return 结果是否等于 目标值 k
     */
    public boolean dfs(int i, int sum) { // 已经从前 i  项得到了和sum，对 i 项之后的进行分支

        // 1、递归终止条件：如果前 n 项都计算过了，则返回sum 与目标 k 是否相等
        if (i == n) return sum == k;

        // 2、分支
        // 不加上 a[i] 项，判断sum是否等于 k 值
        if (dfs(i + 1, sum)) return true;
        // 加上 a[i] 项，判断是否等于 k 值, 比如 k = 0, 此时一个点都不加即可
        if (dfs(i + 1, sum + arr[i])) {
            res.push(arr[i]);
            return true;
        }
        // 加不加上 arr[i] sum都不等于 k, 返回false
        return false;
    }

    public static void main(String[] args) {
        new PartialSumProblem().solution();
    }
}
