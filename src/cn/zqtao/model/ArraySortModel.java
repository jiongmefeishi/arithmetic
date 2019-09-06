package cn.zqtao.model;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 排序随机样本产生器模型
 * @version: 1.0
 */
public class ArraySortModel {
    // for test : 一个绝对正确的排序方法 --> 这里使用Java自带方法
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test : 随机样本产生器
    public static int[] generateRandomArray(int maxSize, int maxValue) {

        // 随机数组长度，随机长度
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        // 随机数据
        for (int i = 0; i < arr.length; i++) {
            // 两个随机数相减，可以随机出负数
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test : 复制数组
    public static int[] copyArray(int[] arr) {
        if (arr == null)
            return null;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = arr[i];
        return res;
    }

    // for test : 实现一个对比方法
    public static boolean isEqual(int[] arr1, int[] arr2) {
        // 有一个数组为null
        if (arr1 == null && arr2 != null
                || arr1 != null && arr2 == null)
            return false;
        // 两个数组都为null
        if (arr1 == null && arr2 == null) return true;

        // 两个数组的长度不一致
        if (arr1.length != arr2.length) return false;

        // 对比两个数组中的每一个元素
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

       /*
        采用对数器来进行验证
        1、一个你想要测试的方法 a
        2、一个绝对正确但是复杂度不好是方法 b
        3、实现一个随机样本产生器
        4、实现对比的方法
        5、把方法a和方法b比对很多次来验证方法a 是否正确
        6、如果有一个样本使得对比出错，打印出样本，分析到底是哪个方法出错
        7、当样本数量很多时比对测试依然正确，可以确定方法a 正确
     */
}
