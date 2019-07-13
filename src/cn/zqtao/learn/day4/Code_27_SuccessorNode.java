package cn.zqtao.learn.day4;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_27_SuccessorNode {

    public static class Node{
        public int value;
        public Node parent; // 父节点
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 给定二叉树中的任意一个节点，找到，当前节点的后继节点，并返回
     *
     * 第一种情况：如果node有右子树，那么后继节点就是整棵右子树上最左边的节点    右子树--->最左
     * 第二种情况：如果node没有右子树
     *      a、node是不是node 父节点的左孩子, 是，返回父节点
     *      b、不是，向上遍历，创建指针parent指向node父节点，node节点和父节点一直上移，找到 a 的情况， 返回
     *
     */
    public static Node successorNode(Node node){
        if (node == null) return node;

        if (node.right != null) { // 有右孩子
            return getLeftMostOfNodeHaveRightChild(node.right);
        } else { // 无右孩子
            Node parent = node.parent;
            while (parent != null && parent.left != node) { // parent != null 针对的是整个二叉树上的最后一个节点
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    /**
     * 有右子树找后继节点
     * @param node 目标节点的右节点
     * @return 后继节点
     *
     * 思路，如果目标节点有右子树，那么后继节点就是整棵右子树上最左边的那个节点
     */
    public static Node getLeftMostOfNodeHaveRightChild(Node node){
        if (node == null) return node;

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + successorNode(test));
    }

    /*
    补充：二叉树寻找前驱节点

    回忆一下二叉树寻找后继节点
    1、有右孩子
        右孩子 --->  最左
    2、无右孩子
        找其中父节点的【左子树节点】等于当前节点（一直在向上移动，即，当前节点在变）的父节点

    同理前驱
    1、有左孩子
        左孩子 --->  最右
    2、无孩子
        找其中父节点的【右子树节点】等于当前节点（一直在向上移动，即，当前节点在变）的父节点
     */
}
