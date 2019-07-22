package cn.zqtao.learn.day7;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_42_Recur_Hanoi {

    /**
     *
     * @param N 表示 这是 1 到 N的问题 1：N
     * @param from N 层汉诺塔原柱子
     * @param to N 层汉诺塔移动的目标柱子
     * @param help 辅助移动柱子
     *
     * 第一步需要将 N-1 层的汉诺塔从from移动到help上
     * 第二步将第N层的汉诺塔从from移动到to上
     *
     * 将from当做help，help当做from，继续前两步操作
     * 第三步需要将 N-2 层的汉诺塔从from移动到to上
     * 第四部将第N-1层的汉诺塔从from移动到to上
     *
     * 递归以上行为
     */
    public static void process(int N, String from, String to, String help){ // N问题 从 from 到 to ，借助help
        if (N == 1){ // 表示 前N - 1 个已经从from移动到了help上, 是1到N的问题
            System.out.println("move " + N + " from " + from + " to " + to);
        } else { // 否则是 1 到N-1 层问题
            process(N - 1, from, help, to); // N - 1 问题 从from 到 help， 借助to
            System.out.println("move " + N + " from " + from + " to " + to);
            process(N - 1, help, to, from); // 挪回来, 从help 到to， 借助from

            /*
                T(N) = T(N-1)+1+T(N-1)
                N-1 个移动到辅助柱子
                N移动到目标柱子
                N-1 挪回来
             */
        }
    }

    public static void main(String[] args) {
        process(3, "左", "右", "中");
    }
}
