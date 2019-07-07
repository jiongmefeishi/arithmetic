package cn.zqtao.learn.day2;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 快速排序
 * 随机快速排序的细节和复杂度分析
 * <p>
 * 1. 经典快排：每次选取最后一个数作为参考点，有些数据状态不好的情况下，会变成O(N^N)的算法
 * 2. 随机快排：在经典快排上进行参考点选取优化；随机选取数组中的一个数和最后一个数进行交换
 * 3. 荷兰国旗优化：在随机快排上使用荷兰国旗方式进行进一步优化
 * <p>
 * 可以用荷兰国旗问题来改进传统的快速排序
 * 时间复杂度O(N*logN)，额外空间复杂度O(logN)
 */
public class Code_07_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            // 随机选取数组中的一个数和最后一个数进行交换作为参考点
            swap(arr, L + (int) Math.random() * (R - L + 1), R);

            int[] p = partition2(arr, L, R);
            // 分治
            // 左半部分递归排序
            quickSort(arr, L, p[0] - 1);
            // 右边部分递归排序
            quickSort(arr, p[1] + 1, R);
        }
    }

    // 精简一行，扣边界，默认最后一个元素不参与排序，等排序荷兰国旗过后再交换位置
    public static int[] partition2(int[] arr, int L, int R) { // O(N)

        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R])
                swap(arr, ++less, L++);
            else if (arr[L] > arr[R])
                swap(arr, L, --more);
            else
                L++;
        }

        // 之前将R位置的直接置为了大于区间，现在需要交换到等于区间
        swap(arr, more, R);

        return new int[]{less + 1, more};
    }

    public static int[] partition(int[] arr, int L, int R) { // O(N)
        int less = L - 1;
        int more = R + 1;
        int num = arr[R];

        while (L < more) {
            if (arr[L] < num)
                swap(arr, ++less, L++);
            else if (arr[L] > num)
                swap(arr, L, --more);
            else
                L++;
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 5, 8, 9, 2, 2, 2, 9, 11};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
