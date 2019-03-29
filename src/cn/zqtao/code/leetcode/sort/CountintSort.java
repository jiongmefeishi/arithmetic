package cn.zqtao.code.leetcode.sort;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 计数排序
 * @version: 1.0
 */
public class CountintSort {
    public static void main(String[] args) {
        int[] arr = {9, 7, 6, 3, 9, 2, 7, 3, 2, 8};
        System.out.println(Arrays.toString(countintSort(arr)));
    }

    /**
     * 不知道数据取值范围
     */
    public static int[] countintSort2(int[] arr){
        // 找出取值边界
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        // 创建频率数组
        int[] frequency = new int[max - min + 1];
        // 存储元素频率
        for (int i = 0; i < arr.length; i++) {
            // 计算当前元素处于频率数组中的位置
            // 比如 min = 3  当前arr[i] = 3   位于频率数组位置  3 - 3 + 1 = 1;  位于1号位
            // 注意0号位不存元素频率，方便下面由频率计算出元素在排序后的数组中处于的下标位置
            frequency[arr[i] - min + 1]++;
        }

        // 由频率数组计算元素在排序后的数组中应该处于的下标位置
        for (int i = 1; i < frequency.length; i++) {
            frequency[i] += frequency[i - 1];
        }

        // 结果数组
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

//
//            // 计算当前元素处于排序数组的下标
//            // 当前元素
//            int num = arr[i];
//            // 当前元素在频率数组中的位置
//            int fre_index = num - min;
//            // 排序数组中的位置
//            int index = frequency[fre_index];
//            // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
//            res[index++] = arr[i];
//


            res[frequency[arr[i] - min]++] = arr[i];
        }

        return arr;
    }

    /**
     * 知道取值范围是 0~9
     */
    public static int[] countintSort(int[] arr){

        // 定义频率数组
        int[] frequency = new int[10];
        // 计算原数组元素频率，存储到频率数组
        for (int i = 0; i < arr.length; i++) {
            // 计算当前元素处于频率数组中的位置
            // arr[i] 即位置
            frequency[arr[i]]++;
        }

        // 反馈回原数组
        for (int i = 0, j = 0; i < frequency.length; i++) {
            // 取出频率
            int fre = frequency[i];
            while (fre-- != 0)
                arr[j++] = i;
        }

        return arr;
    }

}
