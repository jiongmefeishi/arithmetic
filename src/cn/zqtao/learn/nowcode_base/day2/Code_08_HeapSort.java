package cn.zqtao.learn.nowcode_base.day2;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 堆排序
 * 1、先将数组转化为大根堆（逻辑上的堆）
 * 2、交换大根堆的第一个元素和最后一个元素，那么最后一个元素一定的最大的数
 * 3、再对剩余的大根堆进行 heapify 调整，调整成为大根堆
 * 重复2、3过程，就是堆排序
 * @version: 1.0
 */
public class Code_08_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = 0; i < arr.length; i++) {
            headInsert(arr, i);
        }

        // 交换首尾节点，大根堆长度减一
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        while (heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 向大根堆中新增加一个元素
     * 完毕之后  0 ~  i 之间元素构成的就是一个大根堆
     *
     * @param arr
     * @param i   增加第 i 个元素为大根堆的节点
     */
    public static void headInsert(int[] arr, int i) {

        // 简洁方式
        while (arr[i] > arr[(i - 1) / 2]) { // 隐藏结束条件：i = 0时 arr[0] == arr[0]
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

        // 找到父节点
//        int father = (i - 1) / 2;
//        while (arr[i] > arr[father]) {
//            swap(arr, i, father);
//            i = father;
//            // 继续找此节点的父节点
//            father = (i - 1) / 2;
//        }
    }

    /**
     * 调整变动过的大根堆为正确的大根堆
     * @param arr
     * @param index 需要开始调整的元素下标
     * @param heapSize 大根堆长度
     */
    public static void heapify(int[] arr, int index, int heapSize){
        // 找到当前节点的左孩子
        int left = 2 * index + 1;
        while (left < heapSize){
            // 找出左右孩子谁最大；先确认是否存在右孩子, 没有右孩子返回左孩子下标；有则比较返回最大的孩子的下标
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            // 比较最大孩子和父节点值，返回最大下标
            largest = arr[largest] > arr[index] ? largest: index;
            // 如果最大的值下标，等于父节点本身，则直接break
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 5, 8, 9, 2, 2, 2, 9, 11};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
