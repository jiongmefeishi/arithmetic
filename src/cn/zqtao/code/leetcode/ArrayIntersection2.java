package cn.zqtao.code.leetcode;

import java.util.*;

/**
 * @auther: zqtao
 * @description: 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * @version: 1.0
 */
public class ArrayIntersection2 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5,4};
        int[] nums2 = {9,4,9,8,4};
        System.out.println("无序：" + Arrays.toString(intersect(nums1, nums2)));
        System.out.println("有序：" + Arrays.toString(intersect2(nums1, nums2)));
    }

    /**
     * 两个数组有序
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        // 模拟有序状态
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] == nums2[j]){
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]){
                j++;
            } else {
                i++;
            }
        }
        return getIntegerArray(res);
    }

    /**
     * 无序状态
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        Map<Integer, Integer> tmp = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        // 将第一个数组添加到map中
        for(Integer i: nums1){
            // 不存在则初始化
            tmp.putIfAbsent(i, 0);
            tmp.put(i, tmp.get(i) + 1);
        }

        // 遍历第二个数组，存在交集数据则 -1
        // 1 2 4         4存在(4, 1)

        // 1 4 4 4     4存在(4, 3)
        // 比较后        4存在(4, -2)
        // 结果 : [1, 4, 4]

        for(Integer i : nums2){
            if (tmp.containsKey(i) && tmp.get(i) > 0){
                res.add(i);
                tmp.put(i, tmp.get(i) - 1);
            }
        }

        return getIntegerArray(res);
    }

    /**
     * 将List转为int[]
     * @param input
     * @return
     */
    public static int[] getIntegerArray(List<Integer> input){
        int[] resArray = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            resArray[i] = input.get(i);
        }
        return resArray;
    }
}
