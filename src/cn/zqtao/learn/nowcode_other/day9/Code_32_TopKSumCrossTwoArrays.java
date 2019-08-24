package cn.zqtao.learn.nowcode_other.day9;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @auther: zqtao
 * @description: 两个有序数组间相加和的 TOP K 问题
 *
 * 堆问题
 *
 * 对两个数组进行排序
 * 维护一个大根堆和一个map
 * 两个数组看做一个矩阵
 * 大根堆：上来将最大的值入堆，每次弹出的就是当前最大的数，同时将相邻的上一个元素和左边有一个元素入堆
 * map：作用是去重，防止有重复的值进入大根堆
 *
 */
public class Code_32_TopKSumCrossTwoArrays {

    public static class HeapNode{
        public int row;
        public int col;
        public int val;

        public HeapNode(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static int[] topKSum(int[] a1 , int[] a2, int topK) {
        if (a1 == null || a2 == null || topK < 1) {
            return null;
        }

        topK = Math.min(topK, a1.length * a2.length);// 防止topK 超过节点个数
        HeapNode[] maxHeap = new HeapNode[topK + 1]; // 数组模拟大根堆
        int heapSize = 0; // 堆内节点个数

        int headR = a1.length - 1;
        int headC = a2.length - 1;

        int uR = -1; // 上节点左标
        int uC = -1;
        int lR = -1; // 下节点坐标
        int lC = -1;

        // 上来先入最大值进大根堆
        heapInsert(maxHeap, heapSize++, headR, headC, a1[headR] + a2[headC]);
        HashSet<String> positionSet = new HashSet<>(); // 标注是否已经入过堆

        int[] res = new int[topK];
        int resIndex = 0;

        while (resIndex != topK) {
            HeapNode headNode = popHeapHead(maxHeap, heapSize--);
            res[resIndex++] = headNode.val;

            headR = headNode.row;
            headC = headNode.col;

            // 找到上一个节点
            uR = headR - 1;
            uC = headC;
            if (headR != 0 && !isContains(uR, uC, positionSet)){ // 上一个节点是存在的，并且之前未曾入过堆
                heapInsert(maxHeap, heapSize++, uR, uC, a1[uR] + a2[uC]);
                addPositionToSet(uR, uC, positionSet);
            }

            // 找到左边节点
            lR = headR;
            lC = headC - 1;
            if (headC != 0 && !isContains(lR, lC, positionSet)) {
                heapInsert(maxHeap, heapSize++, lR, lC, a1[lR] + a2[lC]);
                addPositionToSet(lR, lC, positionSet);
            }
        }
        return res;
    }

    /**
     * 判断(r,c) 点是否已经入过堆
     */
    private static boolean isContains(int row, int col, HashSet<String> positionSet) {
        return positionSet.contains(String.valueOf(row + "_" + col));
    }

    private static void addPositionToSet(int row, int col, HashSet<String> positionSet) {
        positionSet.add(String.valueOf(row + "_" + col));
    }

    /**
     * 弹出大根堆头结点
     */
    private static HeapNode popHeapHead(HeapNode[] heap, int heapSize) {
        HeapNode pop = heap[0];
        swap(heap, 0, heapSize - 1);
        heap[--heapSize] = null;
        heapIfy(heap, 0, heapSize);
        return pop;
    }

    /**
     * 找到父节点，比较大小，交换，直到适合位置
     * @param heap 要插入的大根堆
     * @param index 要插入的位置（数组模拟大根堆，即数组中的位置）
     * @param row 插入点--行
     * @param col 插入点--列
     * @param val 插入点--值
     */
    public static void heapInsert(HeapNode[] heap, int index, int row, int col, int val){
        heap[index] = new HeapNode(row, col, val);
        int parentNodeIndex = (index - 1) / 2;
        while (index != 0) {
            if(heap[index].val > heap[parentNodeIndex].val){
                swap(heap, index, parentNodeIndex);
                index = parentNodeIndex;
                parentNodeIndex = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    public static void swap(HeapNode[] heap, int a, int b){
        HeapNode tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    /**
     * @param heap 大根堆
     * @param index 从index 这个位置开始进行调整
     * @param heapSize heap size
     */
    public static void heapIfy(HeapNode[] heap, int index, int heapSize) {
        // 数组模拟大根堆，左右子孩子的下标变换
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        while (left < heapSize) { // 左孩子必须存在
            if (heap[left].val > heap[index].val){ // 左孩子更大
                largest = left;
            }

            if (right < heapSize && heap[right].val > heap[largest].val){ // 右孩子更大
                largest = right;
            }

            if (index == largest) { // 自己最大
                break;
            } else {
                swap(heap, largest, index);
            }

            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,2};
        int[] arr2 = {6,5,4};

        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int[] ints = topKSum(arr1, arr2, 3);
        System.out.println(Arrays.toString(ints));
    }
}
