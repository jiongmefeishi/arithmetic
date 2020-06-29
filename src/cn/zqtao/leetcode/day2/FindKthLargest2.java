package cn.zqtao.leetcode.day2;

import java.util.Arrays;

/**
 * @author: zqtao
 * @description: 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * @Date: 2020/6/29
 */
class FindKthLargest2 {

    public static int findKthLargest(int[] nums, int k) {
        if (nums.length < 2) return nums[0];

        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    public static void quickSort(int[] arr, int L, int R) {
        // 边界有效
        if (L < R) {
            // 随机抽取参考点和最后一个数进行交换，因为后面partition 时利用的就是最后一个数作为参考, 减少出现 O(N * N) 的情况次数
            swap(arr, L + (int) Math.random() * (R - L + 1), R);
            // 分区
            int[] p = partition(arr, L, R);

            // 分治
            // 左半部分递归排序
            quickSort(arr, L, p[0] - 1);
            // 右边部分递归排序
            quickSort(arr, p[1] + 1, R);
        }
    }

    // 分区处理数据，对 arr[...L...R...] 范围内的数据进行分区处理
    // 1、选取一个参考点 num ，默认使用最后一个数
    // 2、将L~R 范围内，分配 less 区，和 more 区
    // 3、比较数组中的每一项，将 arr[i] 小于 参考点 num 的数，放到 less 区域；将大于参考点 num 的数放到 more 区域
    // 4、返回 等于区域的开始坐标和结束坐标作为下一批分区的 边界
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R + 1;
        // 参考点选择最后一位数（可随机选择）
        int num = arr[R];

        // 进行分区处理
        while (L < more) {
            if (arr[L] < num)
                swap(arr, ++less, L++);
            else if (arr[L] > num)
                swap(arr, L, --more);
            else
                L++;
        }
        return new int[]{
                less + 1, more - 1
        };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(findKthLargest(arr, 2));
    }
}
