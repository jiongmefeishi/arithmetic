package cn.zqtao.learn.nowcode_base.day2;

import cn.zqtao.learn.nowcode_base.model.ArraySortModel;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 最大差值
 * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时 间复杂度O(N)，
 * 且要求不能用非基于比较的排序。
 * <p>
 * 运用到了桶排序中的分桶的概念，但是它不是桶排序
 * 若有N个数，则准备N + 1 个桶
 * 找到N个数的最大值和最小值，0号位就放最小值，N 号位就放最大值
 * <p>
 * 记录每个桶的最大值，最小值，以及这个桶是否存在数
 * <p>
 * 每向一个桶添加一个数，都更新这个桶的最大值、最小值、是否有数
 * <p>
 * 最终，依次比较两个非空桶之间的 min  和  max 得到结果
 * @version: 1.0
 */
public class Code_10_MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 找到数组中的最大值和最小值
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        // 数组中的数都相同的情况，如：1 1 1 1 1
        if (max == min) return 0;

        boolean[] hasNum = new boolean[arr.length + 1];
        int[] maxs = new int[arr.length + 1];
        int[] mins = new int[arr.length + 1];

        int bid = 0;
        for (int i = 0; i < arr.length; i++) {
            bid = getBucketIndex(arr[i], arr.length, min, max);
            // 更新桶信息
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }

        int res = 0;

        int lastMax = maxs[0];
        for (int i = 1; i < hasNum.length; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    /**
     * 确认每一个数在桶中所应该处于哪个位置
     *
     * @param num   需要确认的数
     * @param lenth 桶数量
     * @param min   最小值
     * @param max   最大值
     * @return
     */
    public static int getBucketIndex(long num, long lenth, long min, long max) {
        return (int) ((num - min) * lenth / (max - min));
    }

    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] randomArr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = ArraySortModel.copyArray(randomArr);
            int[] arr2 = ArraySortModel.copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                if (!ArraySortModel.isEqual(arr1, arr2)) {
                    succeed = false;
                    System.out.println("错误: " + Arrays.toString(randomArr));
                    System.out.println("待测方法结果：" + Arrays.toString(arr1));
                    System.out.println("正确方法结果：" + Arrays.toString(arr2));
                    break;
                }
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
