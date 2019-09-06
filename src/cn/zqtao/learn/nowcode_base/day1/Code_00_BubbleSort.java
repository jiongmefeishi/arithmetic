package cn.zqtao.learn.nowcode_base.day1;

import java.util.Arrays;

import static cn.zqtao.model.ArraySortModel.*;

/**
 * @auther: zqtao
 * @description: 冒泡排序（工程基本不用） - 小到大
 * 每次相邻的两个数进行比较，然后交换（需要时交换），每一趟都找到了当前样本中的最大数
 */
public class Code_00_BubbleSort {


    // 排序
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        // 每一趟少一个最大元素，固定范围
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) { // i 不能= end ，不然 i + 1 数组越界
                if (arr[i] > arr[i + 1])
                    swap(arr, i, i + 1);
            }
        }

    }

    // 交换
    public static void swap(int[] arr, int i, int j) {
        // 交换时间复杂度：常数时间复杂度
        // 交换只涉及到了数组元素的寻址操作，这是常数时间操作
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // 测试次数
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;

        for (int i = 0; i < testTime; i++) {
            int[] randomArray = generateRandomArray(maxSize, maxValue, true);
            int[] arr1 = copyArray(randomArray);
            int[] arr2 = copyArray(randomArray);

            bubbleSort(arr1);
            comparator(arr2);

            if (!isEqual(arr1, arr2)) {
                // 错误打印出错是的数据样本
                System.out.println("出错: " + Arrays.toString(randomArray));
                break;
            }
        }
    }

}
