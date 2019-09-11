package cn.zqtao.learn.zcy_code.tree;

import cn.zqtao.learn.zcy_code.tree.model.Node;
import cn.zqtao.learn.zcy_code.tree.model.PrintBT;

import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description: 层次遍历重建二叉树
 * @version: 1.0
 */
public class Code_01_RebuildBTreeByLevel {

    /**
     * 根据层次遍历数组重建二叉树
     * @param arr 空值节点在数组中表现为 -1
     *
     * 数组模拟二叉树，寻找到对应节点类似堆排序
     * 任意节点 i 它的左孩子 2*i + 1
     *            它的右孩子2*i + 2
     *            它的父节点(i-1)/2
     */
    public static Node createBT(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        LinkedList<Node> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i] == -1 ? null : new Node(arr[i]));
        }
        Node head = list.get(0);
        for (int i = 0; i < arr.length / 2 - 1; i++) {
            if (list.get(i) != null) {
                list.get(i).left = list.get(2 * i + 1);
                list.get(i).right = list.get(2 * i + 2);
            }
        }

        int i = arr.length / 2 - 1;
        list.get(i).left = list.get(i * 2 + 1);
        if (arr.length % 2 == 1) {
            list.get(i).right = list.get(i * 2 + 2);
        }
        return head;
    }

    public static void main(String[] args) {
        // for test ：层次遍历重建二叉树
        int[] arr = {9, 6, 15, 2, -1, 12, 25, -1, -1, -1, -1, -1, -1, 20, 37};
        Node node = createBT(arr);
        PrintBT.printTree(node);
    }
}
