package cn.zqtao.code.model;

/**
 * @auther: zqtao
 * @description: 定义二叉树
 * @version: 1.0
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    // 方便测试打印节点信息
    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
