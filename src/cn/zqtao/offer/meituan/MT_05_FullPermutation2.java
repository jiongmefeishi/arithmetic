package cn.zqtao.offer.meituan;

/**
 * @auther: zqtao
 * @description: 全排列，剪枝去重
 * @version: 1.0
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class MT_05_FullPermutation2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        boolean[] used = new boolean[n];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(n);
        dfs(nums, n, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int n, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == n) { // 路径走完，加入结果集
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (used[i]) { // 该路径已经使用过
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, n, depth + 1, used, path, res);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        MT_05_FullPermutation2 solution = new MT_05_FullPermutation2();
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println(res);
    }
}

