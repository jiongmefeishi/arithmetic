package cn.zqtao.code.nowcoder.offer.tree;

/**
 * @auther: zqtao
 * @description: 剑指offer-重建二叉树
 * @version: 1.0
 */
public class RebuildBinaryTree {

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = new RebuildBinaryTree().reConstructBinaryTree(preorder, inorder);
        System.out.println("中序");
        printTreeNodeToInorder(root);

        System.out.println("\n前序");
        printTreeNodeToPreorder(root);
    }

    /**
     * 重建二叉树
     *
     * @param pre 前序序列
     * @param in  中序序列
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0) return null;

        TreeNode root = reConstructBinaryTreeCore(pre, 0, pre.length - 1, in, 0, in.length - 1);

        return root;
    }

    /**
     * 重建二叉树核心算法，递归完成
     * @param pre 前序序列
     * @param startPre 前序序列开始下标
     * @param endPre 前序序列结束下标
     * @param in 中序序列
     * @param startIn 中序序列开始下标
     * @param endIn 中序序列结束下标
     */
    private static TreeNode reConstructBinaryTreeCore(int[] pre, int startPre, int endPre,
                                                     int[] in, int startIn, int endIn) {
        // 递归结束条件
        if (startPre > endPre || startIn > endIn) return null;

        // 前序序列的第一个节点作为根节点
        TreeNode root = new TreeNode(pre[startPre]);

        // 遍历中序，找到根节点，分左右子树，再次递归
        for (int i = startIn; i <= endIn; i++) {

            // 找根节点
            if (in[i] == pre[startPre]){
                // 分子树

                // 左子树
                root.left = reConstructBinaryTreeCore(pre, startPre + 1, startPre + (i - startIn),in, startIn, i - 1);
                // 右子树
                root.right = reConstructBinaryTreeCore(pre, startPre + (i - startIn) + 1, endPre, in,i + 1, endIn);

            }
        }
        return root;
    }

    // 递归打印二叉树 - 中序
    public static void printTreeNodeToInorder(TreeNode root){
        if (root != null){
            printTreeNodeToInorder(root.left);
            System.out.print(root.val + " ");
            printTreeNodeToInorder(root.right);
        }
    }

    // 递归打印二叉树 - 中序
    public static void printTreeNodeToPreorder(TreeNode root){
        if (root != null){
            System.out.print(root.val + " ");
            printTreeNodeToPreorder(root.left);
            printTreeNodeToPreorder(root.right);
        }
    }
}
