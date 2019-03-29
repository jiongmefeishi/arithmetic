package cn.zqtao.code.leetcode.sort;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 快速排序
 *
 * 如 20，40，50，10，60
 *
 * left指针，right指针，base参照数。
 *
 *
 *
 * 其实思想是蛮简单的，就是通过第一遍的遍历（让left和right指针重合）来找到数组的切割点。
 *
 *
 *
 * 第一步：首先我们从数组的left位置取出该数（20）作为基准（base）参照物。
 *
 *
 *
 * 第二步：从数组的right位置向前找，一直找到比（base）小的数，
 *
 *
 *
 *             如果找到，将此数赋给left位置（也就是将10赋给20），
 *
 *
 *
 *             此时数组为：10，40，50，10，60，
 *
 *
 *
 *             left和right指针分别为前后的10。
 *
 *
 *
 * 第三步：从数组的left位置向后找，一直找到比（base）大的数，
 *
 *
 *
 *              如果找到，将此数赋给right的位置（也就是40赋给10），
 *
 *
 *
 *              此时数组为：10，40，50，40，60，
 *
 *
 *
 *              left和right指针分别为前后的40。
 *
 *
 *
 * 第四步：重复“第二,第三“步骤，直到left和right指针重合，
 *
 *
 *
 *              最后将（base）插入到40的位置，
 *
 *
 *
 *              此时数组值为： 10，20，50，40，60，至此完成一次排序。
 *
 *
 *
 * 第五步：此时20已经潜入到数组的内部，20的左侧一组数都比20小，20的右侧作为一组数都比20大，
 *
 *
 *
 *             以20为切入点对左右两边数按照"第一，第二，第三，第四"步骤进行，最终快排大功告成。
 * @version: 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
//        int[] arr = { 49, 38, 65, 97, 23};

        // 从小到大
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 分治法的基本思想
     *     　分治法的基本思想是：将原问题分解为若干个规模更小但结构与原问题相似的子问题。递归地解这些子问题，然后将这些子问题的解组合为原问题的解。
     *
     *     时间复杂度：快速排序具有最好的平均性能（average behavior） 只有 O(nlogn)，但最坏性能（worst case behavior）和插入排序
     * 相同，也是O(n^2)
     * @param arr
     * @param in_start
     * @param in_end
     */
    public static void quickSort(int[] arr, int in_start, int in_end) {

        int start = in_start;
        int end = in_end;
        // 基准元素
        int base = arr[start];
        while (start < end){
            while (start < end && arr[end] >= base)
                end--;
            if (start < end){
                arr[start] = arr[end];
                start++;
            }

            while (start < end && arr[start] <= base)
                start++;

            if (start < end){
                arr[end] = arr[start];
                end--;
            }
        }
        // 跳出循环时，找到目标位置，目标左边都比目标小，右边都比目标大
        arr[start] = base;

        System.out.println(Arrays.toString(arr));
        // 左半部分
        if (start - 1 > in_start)
            quickSort(arr, in_start, start - 1);
        // 右半部分
        if (end + 1 < in_end)
            quickSort(arr, end + 1, in_end);
    }
}
