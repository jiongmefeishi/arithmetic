package cn.zqtao.code.leetcode.tree.binary;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @auther: zqtao
 * @description: 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * @version: 1.0
 */
public class IsValidBST {

    public static void main(String[] args) {
        System.out.println(Double.MIN_VALUE < 0); // false
    }

    // Double.MIN_VALUE > 0
    double borderVal = -Double.MAX_VALUE;

    /**
     * 利用二叉树的中序遍历来进行遍历
     * 先比较左子树和根节点
     */
    public boolean isValidBST(TreeNode root) {

        if (root == null) return true;

        if (isValidBST(root.left)) { // 验证左子树
            if (borderVal < root.val) { // 保证左子树小于父节点
                borderVal = root.val;
                return isValidBST(root.right); // 验证右子树
            }
        }

        return false;
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
