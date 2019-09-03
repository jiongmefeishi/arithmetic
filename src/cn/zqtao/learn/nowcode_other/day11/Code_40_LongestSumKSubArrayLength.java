package cn.zqtao.learn.nowcode_other.day11;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 正负零子数组累加和为 K的最长子数组长度
 * @version: 1.0
 */
public class Code_40_LongestSumKSubArrayLength {


    /**
     * 此题和Code_39_XOR 思路基本类似
     *
     * 思路：
     * 维护一个 sum 和一个 maxLen
     * 寻找么一个以 i 位置结尾 累加和为K 的最长子数组长度
     *
     * 0~i 位置累加和是 sum ，那么只需要找到第一次出现 sum-K 的位置 j，就能知道 j+1~i 位置累加和一定是 K
     * 如： K=200    0~i ：sum=1000， sum-K=800 ---> j  那么j+1~i 一定能累加出 K=200，记录长度 i-j+1
     * @param arr
     * @return
     */
    public static int subArrSumK(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // sum-k=0 第一次出现的情况，表示0~i 位置，就是当前最长i-(-1)+1
        int maxLen = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum- K)){
                int j = map.get(sum - K); // 第一次出现 sum-K 的位置
                maxLen = Math.max(i-j, maxLen);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,5,1,2,3};
        System.out.println(subArrSumK(arr, 5));
    }
}
