package cn.zqtao.code.leetcode.sort;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 归并排序
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * <p>
 * 思想： 分而治之
 * 先分，再治
 */
public class MergeSort {
    // 比较次数统计
    static int count;
    public static void main(String[] args) {
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        int[] arr = {3, 2};
        int[] arr = {6, 202, 100, 301, 38, 8, 1};
        count = 0;
        sort(arr);
        System.out.println("归并排序结果：\n" +Arrays.toString(arr));
        System.out.println("比较次数：" + count);
    }

    /**
     * 从小到大
     *
     * 归并操作(merge)，也叫归并算法，指的是将两个顺序序列合并成一个顺序序列的方法。
     * 如　设有数列{6，202，100，301，38，8，1}
     * 初始状态：6,202,100,301,38,8,1
     * 第一次归并后：{6,202},{100,301},{8,38},{1}，比较次数：3；
     * 第二次归并后：{6,100,202,301}，{1,8,38}，比较次数：4；
     * 第三次归并后：{1,6,8,38,100,202,301},比较次数：4；
     * 总的比较次数为：3+4+4=11；
     * 逆序数为14；
     *
     *
     * 归并操作的工作原理如下：
     * 第一步：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 第二步：设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 第三步：比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 重复步骤3直到某一指针超出序列尾
     * 将另一序列剩下的所有元素直接复制到合并序列尾
     */
    public static void sort(int[] arr) {
        // 排序前，先申明一个和原数组等长的临时数组，用来存储排序结果，同时避免递归过程中频繁的开辟空间
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
    }

    public static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;

            // 先分
            //左边归并排序，使得左子序列有序
            sort(arr, left, mid, tmp);
            //右边归并排序，使得右子序列有序
            sort(arr, mid + 1, right, tmp);
            //将两个有序子数组合并操作
            merge(arr, left, mid, right, tmp);
        }
    }

    /**
     * 合并两个有序子序列
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        // 左序列开始指针
        int i = left;  // ---> 结束指针 mid
        // 右序列开始指针
        int j = mid + 1;  // ---> 结束指针 right

        int index = 0; // 临时数组下标
        // 排序合并两个有序子序列
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
            count++;
        }

        // 左序列有剩余
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }

        // 右序列有剩余
        while (j <= right) {
            tmp[index++] = arr[j++];
        }

        // 数据回写
        index = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = tmp[index++];
        }
    }


}
