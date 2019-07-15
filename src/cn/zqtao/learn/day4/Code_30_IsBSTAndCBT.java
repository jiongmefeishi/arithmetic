package cn.zqtao.learn.day4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 * @version: 1.0
 */
public class Code_30_IsBSTAndCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 回忆之前学的非递归：中序遍历
    // 全力压左孩子入栈，整棵二叉树可以被分解为多个左子树状态
    /*public static void inOrderUnRecure(Node head){
        System.out.println("in-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            while (head != null || !stack.isEmpty()) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }

            }
        }
    }*/

    // 根据非递归形式改写中序遍历，判断搜索二叉树
    // 搜索二叉树特点，中序遍历结果是依次递增的
    public static boolean BST(Node head){
        if (head == null){
            return true;
        }

        int val = Integer.MIN_VALUE;

        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.isEmpty()){
            if (head != null) {
                stack.add(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (val > head.value){
                    return false;
                } else {
                    val = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

    // 层次遍历进行遍历

    /**
     * 判断一棵二叉树是否是完全二叉树,
     * 依据以下标准会使判断过程变得简单且易实现:
     *
     * 1,按层遍历二叉树,从每层的左边向右边依次遍历所有的节点。
     * 2,如果当前节点有右孩子,但没有左孩子,直接返回false.
     * 3,如果当前节点并不是左右孩子全有,那之后的节点必须都为叶节点,否则返回false.
     * 4,遍历过程中如果不返回false,遍历结束后返回true.
     */
    public static boolean CBT(Node head){
        if (head == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        Node L = null;
        Node R = null;
        boolean leaf = false; // 记录接下来的遍历是否都是叶节点的遍历

        while (!queue.isEmpty()){
            head = queue.poll();
            L = head.left;
            R = head.right;

            // 排除掉不是的可能性方案
            if (
                    (leaf && (L != null || R != null)) // 当开启叶节点遍历时：叶节点存在子树，则返回false
                 || (L == null && R != null) // 或者当前节点的左子树为null，右子树不为空，返回false
            ) {
                return false;
            }

            // 继续遍历子树
            if (L != null) {
                queue.offer(L);
            }
            if (R != null) {
                queue.offer(R);
            } else {
                leaf = true; // 当左子树，或者右子树等于null时，开启叶节点遍历
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(BST(head));
        System.out.println(CBT(head));
    }

//////////////////////////////////for test s/////////////////////////////////////////

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
//////////////////////////////////for test e/////////////////////////////////////////

}
