package cn.zqtao.offer.meituan;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zqtao
 * @description: 全排列（回溯算法）
 * @version: 1.0
 */
class MT_05_FullPermutation {
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>(); // 结果集
        int[] visited = new int[nums.length]; // 访问路径
        backtrack(res, nums, new ArrayList<>(), visited);
        return res;

    }

    private static void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) { // 路径到底，说明当前路线已经走完，加入结果集
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue; // 标识路径是否已经访问过
            visited[i] = 1; // 标识此路径已经访问过

            tmp.add(nums[i]); // 计入访问集合
            backtrack(res, nums, tmp, visited);
            visited[i] = 0; // 访问完毕，重新标识当前路径为未访问
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> permute = permute(arr);
        System.out.println(permute);
    }
}
