package cn.zqtao.learn.day1;

import cn.zqtao.learn.model.ArraySortModel;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 小和问题
 * <p>
 * 小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和。
 * 例子：
 * [1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 * @version: 1.0
 */
public class Code_04_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergesort(arr, 0, arr.length - 1);
    }


    public static int mergesort(int[] arr, int L, int R) {
        if (L == R) return 0;

        int mid = L + ((R - L) >> 1);
        return mergesort(arr, L, mid) +
                mergesort(arr, mid + 1, R) +
                merge(arr, L, mid, R);
        //左边产生的小和+右边产生的小和+合并产生的小和就是整个数组的小和
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;

        // 双指针
        int p1 = L;
        int p2 = mid + 1;

        int res = 0;
        while (p1 <= mid && p2 <= R) {
            // 左边小于右边，左边数 * 右边比左边大的数的个数，因为已经使用归并排序进行了排序
            // 所以右边剩下的数也比左边数大
            // 如左边  1 3
            //   右边  2 5 6
            // 1 < 2 ,此时右边数，5 6 一定都比1大， 则 小和结果是 1 * 3

            res = arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid)
            help[i++] = arr[p1++];
        while (p2 <= R)
            help[i++] = arr[p2++];

        for (i = 0; i < help.length; i++)
            arr[L + i] = help[i];
        return res;
    }

    // for test 写一个实现简单，绝对正确但是时间复杂度高的算法
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] randomArr = ArraySortModel.generateRandomArray(maxSize, maxValue);
            int[] arr1 = ArraySortModel.copyArray(randomArr);
            int[] arr2 = ArraySortModel.copyArray(randomArr);
            if (!ArraySortModel.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("错误: " + Arrays.toString(randomArr));
                System.out.println("待测方法结果：" + Arrays.toString(arr1));
                System.out.println("正确方法结果：" + Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
