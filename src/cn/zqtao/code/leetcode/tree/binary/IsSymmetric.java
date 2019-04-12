package cn.zqtao.code.leetcode.tree.binary;

/**
 * @auther: zqtao
 * @description: 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * @version: 1.0
 */
public class IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
//        if (root == null) return true;
//        return isMirror(root.left, root.right);
        return isMirror(root, root);
    }

    /**
     * 对称镜像
     *
     * 如果同时满足下面的条件，两个树互为镜像：
     *
     * 它们的两个根结点具有相同的值。
     * 每个树的右子树都与另一个树的左子树镜像对称。
     */
    static boolean isMirror(TreeNode node1, TreeNode node2){
        // 左右子树同时为null， 返回true
        if (node1 == null && node2 == null) return true;
        // 左右子树有一个为null ，返回false
        if (node1 == null || node2 == null) return false;

        // 左右子树都不为null， 比较val是否镜像对称（相等），并且继续递归向下遍历
        return (node1.val == node2.val)
                    && isMirror(node1.right, node2.left) // 每个树的右子树都与另一个树的左子树镜像对称
                    && isMirror(node1.left, node2.right);
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
