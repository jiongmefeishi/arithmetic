package cn.zqtao.learn.day1;

import cn.zqtao.learn.model.ArraySortModel;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 归并排序
 * <p>
 * 分治思想
 * <p>
 * 左半部分排序
 * 右半部分排序
 * <p>
 * 采用外排方式，合并两个有序部分到一个临时数组
 * <p>
 * 复制回原数组
 * @version: 1.0
 */
public class Code_03_MergerSort {

    public static void mergerSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        sortProcess(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

    }

    public static void sortProcess(int[] arr, int L, int R) {
        if (L == R)
            return;
        int mid = L + ((R - L) >> 1); // 等同于 (L + R) / 2
        sortProcess(arr, L, mid);
        sortProcess(arr, mid + 1, R);

        merger(arr, L, mid, R); // O(N)
        // T(N) = 2 T(N/2) + O(N) 符合master公式
        // 所以时间复杂度： O(N * logN)
        // 空间复杂度：O(N)  临时辅助数组
    }

    // 合并两个有序部分
    private static void merger(int[] arr, int L, int mid, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;

        // 双指针，分别指向第一个有序部分第一个数位置，第二个有序部分第一个数位置
        int p1 = L;
        int p2 = mid + 1;

        // 外排方式选填进入临时辅助数组的数, 当两个指针任意一个越界时跳出
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 有且只有一个越界
        while (p1 <= mid)
            help[i++] = arr[p1++];
        while (p2 <= R)
            help[i++] = arr[p2++];

        // 回写到原数组
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 10;
        int maxValue = 100;

        for (int i = 0; i < testTime; i++) {
            int[] randomArr = ArraySortModel.generateRandomArray(maxSize, maxValue);
            int[] arr1 = ArraySortModel.copyArray(randomArr);
            int[] arr2 = ArraySortModel.copyArray(randomArr);

            mergerSort(arr1);
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
