package cn.zqtao.learn.nowcode_base.day1;

import cn.zqtao.learn.nowcode_base.model.ArraySortModel;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 插入排序（相对工程中使用挺多）
 * <p>
 * 类似插牌
 * <p>
 * 将数据组划分为两块，一块是已经排序好的，一块是待排序的
 * <p>
 * 每一次从待排序数组中取出一个元素，进行插入
 * 插入选择：从排序好的数组的最后一个元素开始进行倒序遍历，寻找它应该存放的位置，进行交换插入
 * @version: 1.0
 */
public class Code_02_InsertionSort {

    public static void insertionSort(int[] arr) {

        if (arr == null || arr.length < 2)
            return;

        // 外层循环控制循环次数, 默认认为第一个元素是已经排序好的
        for (int i = 1; i < arr.length; i++) {
            // 对已经排序好的数组进行倒叙遍历，查询新元素的插入下标
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }

    }

    // 不需要使用临时变量，进行交换
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 10;
        int maxValue = 100;

        for (int i = 0; i < testTime; i++) {
            int[] randomArr = ArraySortModel.generateRandomArray(maxSize, maxValue);
            int[] arr1 = ArraySortModel.copyArray(randomArr);
            int[] arr2 = ArraySortModel.copyArray(randomArr);

            insertionSort(arr1);
            ArraySortModel.comparator(arr2);

            if (!ArraySortModel.isEqual(arr1, arr2)) {
                System.out.println("错误: " + Arrays.toString(randomArr));
                System.out.println("待测方法结果：" + Arrays.toString(arr1));
                System.out.println("正确方法结果：" + Arrays.toString(arr2));
                break;
            }
        }
    }

}
