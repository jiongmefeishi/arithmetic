package cn.zqtao.code.leetcode.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @auther: zqtao
 * @description: 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class LevelOrder {

    /**
     * 使用队列保证顺序
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        // 层次遍历结果
        List<List<Integer>> res = new ArrayList<>();
        // 入队形式，保证每层访问顺序正确
        Queue<TreeNode> queue = new LinkedList<>();

        // 根节点入队
        queue.add(root);
        while(!queue.isEmpty()) {

            // 存储当前层节点值
            List<Integer> list = new ArrayList<>();
            // 获取队列中需要弹出处理的节点个数
            int count = queue.size();

            while(count > 0) {
                // 弹出当前层需要处理的节点
                TreeNode node = queue.poll();
                // 存储当前节点值
                list.add(node.val);
                // 判断是否存在下一层的左节点，存在，入队
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);

                // 当前节点访问完成，队列中节点数--
                count--;
            }

            // 存储当前层节点值
            res.add(list);
        }
        return res;
    }

    // Definition for a binary tree node.
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
