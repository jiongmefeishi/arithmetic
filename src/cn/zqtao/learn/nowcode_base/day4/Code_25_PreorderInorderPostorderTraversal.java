package cn.zqtao.learn.nowcode_base.day4;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 二叉树操作：前序遍历二叉树、中序、后序遍历二叉树。
 * @version: 1.0
 */
public class Code_25_PreorderInorderPostorderTraversal {
    // 二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 前序遍历递归版
    public static void preOrderRecur(Node head) {
        if (head == null)
            return;
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 中序遍历递归版
    public static void inOrderRecur(Node head) {
        if (head == null) return;

        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // 后序遍历递归版
    public static void postOrderRecur(Node head) {
        if (head == null) return;
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 前序遍历：非递归
     * 1、准备一个栈，先入头结点
     * 2、遍历栈（循环条件：栈不为空）
     *      从栈中弹出一个元素，打印
     *      判断这个节点是否有右子树，有入栈
     *      判断这个节点是否有左子树，有入栈
     * @param head
     */
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        // stack.push(head); // 存头结点
        // stack 的add 方法和push 方法本质上没有区别，都是调用java.util.Vector.add(E)方法
        // 唯一不同就是返回值不同，add 返回添加成功否，push 返回这个添加的元素对象

        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }

        System.out.println();
    }

    /**
     * 中序遍历，非递归
     *
     * 全力压左边界入栈，整棵树可以被左边界分解
     *
     * 1、准备一个栈
     * 2、遍历（循环条件：栈不为空 || 当前节点不为空）
     *      a、当前节点不为空，就将当前节点入栈，指向左孩子
     *
     *      b、当前节点为空，栈不为空，从当前栈中弹出一个节点，打印
     *         当前节点 指向当前节点的右孩子
     *
     * 即：
     *      if（当前节点不为空）{ //
     *          压入当前节点的左孩子
     *          当前节点 = 当前节点.left
     *      } else {
     *          弹出一个节点， 打印
     *          指向右孩子，继续循环 2 过程
     *      }
     * @param head
     */
    public static void inOrderUnRecur(Node head){
        System.out.print("in-order: ");

        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.isEmpty()){
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历：非递归
     *
     * 前序遍历：中左右   非递归 思路
     * 保存头结点，当前节点有右孩子，存
     *             当前节点有左孩子，存
     *             总体入栈顺序是：右左  --->  打印结果：中左右
     *
     * 后序遍历 ：左右中  思路：借用前序遍历的思路，做出一个 中右左的打印结果，在该打印的时候入另一个栈
     * 根据栈的弹出顺序，就成了左右中
     *
     * 保存头结点，当前节点有左孩子，存
     *             当前节点有右孩子，存
     *             总体入栈顺序是：左右  ---> 打印结果：中右左（不打印，节点入另一个栈）
     *
     * 最后在统一打印另一个栈的节点   --- 中右左  ----> 左右中
     * @param head
     */
    public static void postOrderUnRecur1(Node head) {
        System.out.print("post-order-1: ");
        if (head == null) return;

        Stack<Node> preStack = new Stack<>();
        Stack<Node> postStack = new Stack<>();
        preStack.add(head);
        while (!preStack.isEmpty()) {
            head = preStack.pop();
            postStack.push(head); // 在本该打印的时机，选择存入到另一个栈
            if (head.left != null) {
                preStack.push(head.left);
            }
            if (head.right != null){
                preStack.push(head.right);
            }
        }

        while (!postStack.isEmpty()){
            System.out.print(postStack.pop().value + " ");
        }
        System.out.println();
    }

    // 暂时不要求理解
    public static void postOrderUnRecur2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        postOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        postOrderUnRecur1(head);
        postOrderUnRecur2(head);

    }
}
