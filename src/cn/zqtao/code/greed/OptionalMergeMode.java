package cn.zqtao.code.greed;


import cn.zqtao.code.model.TreeNode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @auther: zqtao
 * @description: 贪心算法-最佳合并模式
 *  问题描述
 *   给定n个有序文件，每个文件的记录数分别为w1~wn，请给出一种两两合并的方案，使得总合并次数最少。
 *
 * 注意：
 * 1. 外排序算法是将多个有序文件合并成一个有序文件的过程。
 * 2. 在一次合并的过程中，两个文件中的所有记录都需要先从文件中读入内存，再在内存中排序，最后将排序的结果写入文件中。
 * 3. 假设两个待排序文件记录数分别为n、m，那么将这两个文件合并成一个有序的文件需要进行n+m次读写。
 *
 *
 * 问题转化  --  哈法曼树  求解最优
 *
 * n个文件两两合并的过程可以用一棵扩充二叉树来表示。
 * 因为扩充二叉树只有度为2或0的节点，没有度为1的节点，这符合两两合并的过程。
 *
 *
 *
 * 贪心算法适用标准
 * 1、求解的是最优问题   -->  求解最少合并次数
 * 2、结果集可以使用 n 元组进行表示   -->  将文件合并方案记录在优先权队列
 * 3、该问题满足最优子结构特征   -->  每两个文件合并之间互不影响
 * 4、可以找到最优度量标准，该最优度量标准可以导致一个整体最优解  -->  最终找到最少合并次数，即哈夫曼树的最小带权路径
 *
 *
 * @version: 1.0
 */
public class OptionalMergeMode {

    public static void main(String[] args) {
        int[] arr = {5, 10, 20, 30, 30};
        solution(arr);
    }

    public static void solution(int[] arr){
        TreeNode treeNode = new OptionalMergeMode().huffmanTree(arr);
    }

    /**
     * 构造哈夫曼树（最优二叉树）求解最优权值
     * @param ws 所有节点的权值
     * @return 哈夫曼根节点
     */
    public TreeNode huffmanTree(int[] ws){

        // 将所有的节点存入到优先权队列，按照权值递增排序
        // PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
        // initialCapacity  -->  初始化容量
        // Comparator 定义排序规则
        PriorityQueue<TreeNode> res = new PriorityQueue<>(ws.length, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.val - o2.val ;
            }
        });

        // 初始化所有的节点为一棵棵单节点树
        for (int i = 0; i < ws.length; i++) {
            // add() offer() 两者都是往队列尾部插入元素，不同的是，当超出队列界限的时候，add（）方法是抛出异常让你处理，
            // 而offer（）方法是直接返回false
            res.offer(new TreeNode(ws[i]));
        }

        final Iterator<TreeNode> iterator = res.iterator();
        while (iterator.hasNext()){
            final TreeNode next = iterator.next();
            System.out.println(next);
        }

        // 构造哈法曼树
        while (res.size() > 1){
            // 弹出最小的两个节点
            TreeNode node1 = res.poll();
            TreeNode node2 = res.poll();

            // 构造出两个子节点的父节点
            TreeNode father = new TreeNode(node1.val + node2.val);
            // 较小子节点为父节点的左子树
            father.left = node1;
            // 较大子节点为父节点的右子树
            father.right = node2;

            // 将父节点装载到优先权队列
            res.offer(father);

        }

        final Iterator<TreeNode> iterator2 = res.iterator();
        while (iterator2.hasNext()){
            final TreeNode next = iterator2.next();
            System.out.println(next);
        }

        return res.poll();
    }
}
