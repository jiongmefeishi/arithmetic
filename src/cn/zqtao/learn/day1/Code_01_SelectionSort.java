package cn.zqtao.learn.day1;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 选择排序（工程基本不用）
 * 每次选择一个最小的放在该放的位置上
 * @version: 1.0
 */
public class Code_01_SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        /*
            5 9 4 1 2
            第一次 i = 0, 循环完毕找到 数1 的下标，交换数，将1 放到 第0 个位置上
            交换后结果：1 9 4 5 2

            第二次 i = 1, 循环完毕找到 数2 的下标，交换数，将2 放到 第1 个位置上
            交换后结果：1 2 4 5 9

            依次类推 ...5 个数，需要循环4次
         */
        // 外层规定循环范围, 每次找到最小的数，放在第 i 个位置上
        for (int i = 0; i < arr.length - 1; i++) {

            // 假设当前 i 位置上的数就是最小数
            int minIndex = i;

            // 内层寻找最小数下标
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }

            swap(arr, i, minIndex);
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        // 交换时间复杂度：常数时间复杂度
        // 交换只涉及到了数组元素的寻址操作，这是常数时间操作
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 32, 8, 1, 9, 66};
        selectionSort(arr);
        /*
         * 时间复杂度 O（N * N）
         * 空间复杂度 O(1)  只涉及到了tmp 临时变量
         */
    }
}
