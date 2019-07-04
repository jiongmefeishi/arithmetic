package cn.zqtao.learn.day2;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 快速排序
 * 和经典版快排相比，这里采用荷兰国旗的解决方式，来进行快排升级
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int[] p = partition(arr, L, R);
            // 分治
            // 左半部分递归排序
            quickSort(arr, L, p[0] - 1);
            // 右边部分递归排序
            quickSort(arr, p[1] + 1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R) {
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

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3,6,5,5,8,9,2,2,2,9,11};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
