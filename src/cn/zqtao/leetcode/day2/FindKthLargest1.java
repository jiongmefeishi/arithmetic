package cn.zqtao.leetcode.day2;

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
class FindKthLargest1 {

    // 冒泡排序
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) return nums[0];
        int j = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            for (j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
            }
        }

        return nums[nums.length - k];
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1};
        System.out.println(findKthLargest(arr, 2));
    }
}
