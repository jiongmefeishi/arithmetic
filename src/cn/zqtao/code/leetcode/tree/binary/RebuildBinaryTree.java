package cn.zqtao.code.leetcode.tree.binary;

/**
 * @auther: zqtao
 * @description: 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不包含重复的数字。
 * 例如输入前序遍历序列{1, 2, 4, 7, 3, 5, 6, 8}和
 * 中序遍历序列{4, 7, 2, 1，5, 3, 8, 6}，
 * 则重建出二叉树并输出它的头结点。
 *
 *
 * 前序遍历：先访问根节点，再访问左子结点，最后访问右子结点；（根左右）
 *
 * 中序遍历：先访问左子结点，再访问根结点，最后访问右子结点；（左根右）
 *
 * 后序遍历：先访问左子结点，再访问右子结点，最后访问根结点；（左右根）
 *
 *
 * 解题思路：
 *
 * 题目中给了我们先序遍历和中序遍历；在二叉树的前序遍历中，第一个数字总是树的根结点的值。
 * 但在中序遍历序列中，根结点的值在序列的中间，左子树的结点的值位于根结点的值的左边，
 * 而右子树的结点的值位于根结点的值的右边。因此我们需要扫描中序遍历序列，才能找到根结点的值。
 *
 * 题目给出的前序遍历序列的第一个数字1就是根结点的值。扫描中序遍历序列，就能确定根结点的值的位置。
 * 根据中序遍历的特点，在根结点的值1前面的3个数字都是左子树结点的值，位于1后面的数字都是右子树结点的值。
 *
 * 由于在中序遍历序列中，有3个数字是左子树结点的值，因此左子树总共有3个左子结点。
 * 同样，在前序遍历的序列中，根结点后面的3个数字就是3个左子树结点的值，再后面的所有数字都是右子树结点的值。
 * 这样就在前序和中序遍历的两个序列中，分别找到了左右子树对应的子序列。
 * @version: 1.0
 */
public class RebuildBinaryTree {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = rebuildBinaryTree(preorder, inorder);
        printBinaryTree(root);
    }

    /**
     * 重建二叉树
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     */
    public static BinaryTreeNode rebuildBinaryTree(int[] preorder, int[] inorder){
        // 空值直接返回
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0)
            return null;

        BinaryTreeNode rootNode = rebuildBinaryTreeCore(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

        return rootNode;
    }

    /**
     * 重建二叉树核心算法
     *
     * 思想：递归
     *
     * @param preorder 前序遍历序列
     * @param startPreorder 前序开始下标
     * @param endPreorder 前序终止下标
     * @param inorder 中序遍历虚列
     * @param startInorder 中序开始下标
     * @param endInorder 中序终止下标
     * @return
     */
    private static BinaryTreeNode rebuildBinaryTreeCore(int[] preorder, int startPreorder, int endPreorder, int[] inorder, int startInorder, int endInorder) {

        // 停止递归的条件
        if (startPreorder > endPreorder || startInorder > endInorder)
            return null;

        // 申明根节点,前序遍历的第一个节点
        BinaryTreeNode root = new BinaryTreeNode(preorder[startPreorder]);
        for (int i = startInorder; i <= endInorder; i++) {
            // 找到根节点，分左右子树
            if (preorder[startPreorder] == inorder[i]){
                // 其中i - startInorder 是中序遍历中左子树的个数
                /*
                    如
                    前序：{1, 2, 4, 7, 3, 5, 6, 8}
                    中序：{4, 7, 2, 1, 5, 3, 8, 6}

                    则  第一次递归时
                    i - startInorder = 3 - 0 = 3  --->  对应根节点1的左子树 4 7 2
                 */

                // 左子树
                //左子树对应的前序遍历的位置在preOrder[startPreorder + 1,startPreorder + i - startInorder]
                //左子树对应的中序遍历的位置在inOrder[startInorder,i - 1]

                // 左子树前序遍历开始下标 = 根节点下标 + 1   对应元素-- > 2
                int left_pre_start = startPreorder + 1;
                // 左子树前序遍历终止下标 = 根节点下标 + (中序排序中根节点下标 i - 中序排序开始下标)
                // 对应元素 -- > 7
                int left_pre_end = startPreorder + (i - startInorder);

                // 左子树中序遍历开始下标  -- > 对应元素 -- > 4
                int left_in_start = startInorder;
                // 左子树中序遍历终止下标  对应元素 -- > 2
                int left_in_end = i - 1;
                root.left = rebuildBinaryTreeCore(preorder, left_pre_start, left_pre_end, inorder, left_in_start, left_in_end);
                // 同理右子树
                // 右子树前序遍历开始下标 = left_pre_end + 1 对应元素 -- > 3
                int right_pre_start = left_pre_end + 1;
                // 右子树前序遍历终止下标 = endPreorder  对应元素 -- > 8
                int right_pre_end = endPreorder;
                // 右子树中序遍历开始下标   对应元素 -- > 5
                int right_in_start = i + 1;
                // 右子树中序遍历终止下标   对应元素 -- > 6
                int right_in_end = endInorder;
                root.right = rebuildBinaryTreeCore(preorder, right_pre_start, right_pre_end, inorder, right_in_start, right_in_end);
            }
        }
        return root;
    }

    /**
     * 循环打印二叉树
     * 中序遍历打印
     */
    public static void printBinaryTree(BinaryTreeNode rootNode) {
        if (rootNode != null){
            printBinaryTree(rootNode.left);
            System.out.print(rootNode.val + "  ");
            printBinaryTree(rootNode.right);
        }
    }

}

// 二叉树节点对象
class BinaryTreeNode{
    // 节点值
    public int val;
    // 左子树
    public BinaryTreeNode left;
    // 右子树
    public BinaryTreeNode right;

    public BinaryTreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
