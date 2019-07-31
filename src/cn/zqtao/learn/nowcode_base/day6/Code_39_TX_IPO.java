package cn.zqtao.learn.nowcode_base.day6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @auther: zqtao
 * @description: 最大项目收益
 * 输入： 参数1，正数数组costs
 *
 *  参数2，正数数组profits
 *
 * 参数3，正数k
 *
 *  参数4，正数m
 *
 * costs[i]表示i号项目的花费
 *
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 *
 * k表示你不能并行、只能串行的最多做k个项目
 *
 * m表示你初始的资金
 *
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个 项目。
 * 输出： 你最后获得的最大钱数。
 *
 * 贪心策略
 *
 * 每次从可选择的项目中，选取花费最小的几个；从这几个中选取收益最高的项目。
 * @version: 1.0
 */
public class Code_39_TX_IPO {

    // 构建项目数据结构
    public static class Node{
        public int cost; // 花费
        public int profits; // 利润

        public Node(int cost, int profits){
            this.cost = cost;
            this.profits = profits;
        }
    }

    // 最小花费，小根堆
    public static class MinCostHeapComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }

    // 最大收益，大根堆
    public static class MaxProfitsHeapComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.profits - o1.profits;
        }
    }

    /**
     *
     * @param k 表示你不能并行、只能串行的最多做k个项目
     * @param m 表示你初始的资金
     * @param profits profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * @param costs costs[i]表示i号项目的花
     * @return 你最后获得的最大钱数。
     *
     * @Description 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个 项目
     */
    public static int findMaximizedCapital(int k, int m, int[] profits, int[] costs) {
        Node[] nodes = new Node[profits.length];
        // 结构化项目数据
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(costs[i], profits[i]);
        }

        // 使用自定义的排序规则，即自定义的对数器，建立最小花费小根堆，最大收益大根堆
        PriorityQueue<Node> minCostsHeap = new PriorityQueue<>(new MinCostHeapComparator());
        PriorityQueue<Node> maxProfitsHeap = new PriorityQueue<>(new MaxProfitsHeapComparator());

        for (int i = 0; i < nodes.length; i++) {
            minCostsHeap.add(nodes[i]);
        }

        for (int i = 0; i < k; i++) { // 表示最多可以做 k 个项目
            while (!minCostsHeap.isEmpty() && minCostsHeap.peek().cost <= m){ // 小根堆不为空且存在花费小于当前可用的金额
                maxProfitsHeap.add(minCostsHeap.poll());
            }
            if (maxProfitsHeap.isEmpty()){
                return m; // 大根堆为空，表示没有花费小于当前可用金额的项目进入大根堆
            }
            m += maxProfitsHeap.poll().profits;
        }

        return m;
    }
}
