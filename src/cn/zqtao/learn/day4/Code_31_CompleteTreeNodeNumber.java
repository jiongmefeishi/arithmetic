package cn.zqtao.learn.day4;

/**
 * @auther: zqtao
 * @description: 已知一棵完全二叉树，求其节点的个数
 *
 *要求：时间复杂度低于O(N)，N为这棵树的节点个数
 *
 * 时间复杂度 O(logN ^ 2)
 * @version: 1.0
 */
public class Code_31_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getNumber(Node head) {
        if (head == null) return 0;

        return process(head, 1, getMaxLeftLevel(head, 1));
    }

    // 返回当前节点为树的节点数
    public static int process(Node node, int level, int treeHigh) {
        if (level == treeHigh) { // 当前节点所在的层级是二叉树的总高度，叶子节点
            return 1;
        }

        if (getMaxLeftLevel(node.right, level + 1) == treeHigh) { // 该节点的右子树的左边界是否等于二叉树总高度
            // 是 ，证明该节点的左子树是一课满完全二叉树
            // 总节点=公式计算左子树总节点数 + 右子树递归查找节点数 + 当前节点
            return (1 << (treeHigh - level)) + process(node.right, level + 1, treeHigh);
            /*
            return (1<< (treeHigh - level) - 1) + process(node.right, level + 1, treeHigh) + 1;
             */
        } else {
            // 否， 证明该节点的右子树是一课满完全二叉树
            // 总节点=左子树递归查找节点数 + 公式计算右子树总节点数 + 当前节点
            return process(node.left, level + 1, treeHigh) + (1 << (treeHigh - level - 1));
        }
    }

    // 返回当前节点的最大左边界
    public static int getMaxLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(getNumber(head));

    }

}
