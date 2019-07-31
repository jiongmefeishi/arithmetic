package cn.zqtao.learn.nowcode_base.day4;

/**
 * @auther: zqtao
 * @description: 判断一棵二叉树是否是平衡二叉树
 * @version: 1.0
 */
public class Code_29_IsBanlaceTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 递归返回值结构
    public static class ReturnData{

        public boolean isB; // 是否平衡
        public int heith; // 高度

        public ReturnData(boolean isB, int heith) {
            this.heith = heith;
            this.isB = isB;
        }
    }

    public static boolean isBanlanceTree(Node head){
        return process(head).isB;
    }


    /**
     * 递归处理过程
     *
     * 递归套路：分析好可能性后，确定要收集的信息，定义好返回结构，
     * 根据子过程收集的信息，自己在决策过程也加工整合出同样结构的信息，往上一层进行返回。
     *
     *
     * 这个套路最重要的是列出所有的可能性。
     *
     * 比如这道题：平衡二叉树
     * 可能性，考虑以每一个节点为根节点的每一棵子树是否也是平衡的
     *
     * 	    1、左子树是否平衡
     *
     * 	    2、右子树是否平衡
     *
     * 	    3、左子树是否平衡和高度
     *
     * 	    4、右子树是否平衡和高度
     *
     *
     *
     * 当前节点的高度是根据子树给的高度决定的。
     * @param head
     * @return
     */
    public static ReturnData process(Node head){
        // 07
        if (head == null) return new ReturnData(true, 0);// null是平衡树

        // 收集左子树信息
        ReturnData leftData = process(head.left);
        if (!leftData.isB) { // 左子树不平衡
            return new ReturnData(false,0);
        }

        // 收集右子树信息
        ReturnData rightData = process(head.right);
        if (!rightData.isB) { // 左子树不平衡
            return new ReturnData(false, 0);
        }

        // 根据左右子树信息，加工整合出同样结构的信息
        if (Math.abs(leftData.heith - rightData.heith) > 1) { // 左右子树平衡，高度差大于1情况
            return new ReturnData(false, 0);
        }

        // 平衡，往上一层进行返回
        return new ReturnData(true, Math.max(leftData.heith, rightData.heith) + 1);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBanlanceTree(head));

    }
}

