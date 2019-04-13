package cn.zqtao.code.leetcode.tree.binary;

/**
 * @auther: zqtao
 * @description: 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * @version: 1.0
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

        int s = 3;
        int e = 8;

        System.out.println(s + (e - s) / 2); // 5
        System.out.println((e + s) / 2); // 5
        System.out.println((e + s) >>> 1); // 5
        System.out.println(s + (e - s) >>> 1); // 4 此方法不能作为求中位数方法
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    /**
     * 每次确定一个节点
     */
    public TreeNode buildTree(int[] nums, int l, int r){

        if (l > r) return null;
//        int mid = s + (e - s) >>> 1; // 提交报错 StackOverFlowError
        int mid = l + (r - l) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildTree(nums, l, mid - 1);
        root.right = buildTree(nums, mid + 1, r);
        return root;
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
