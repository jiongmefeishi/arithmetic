package cn.zqtao.learn.nowcode_other.day11;

/**
 * @auther: zqtao
 * @description: 正子数组累加和为定值K
 * @version: 1.0
 */
public class Code_41_SubArrSumK {

    /**
     * 滑动窗口
     * 维护一个sum变量记录窗口内元素累加和
     * 当sum < K, 窗口扩容 R++
     * 当sum > K，窗口缩减 L++
     * 当sum = K，记录子数组长度（窗口长度），同时缩减窗口容量 L++
     */
    public static int maxSumK(int[] arr, int K) {
        if (arr == null || arr.length == 0) return 0;

        int L = 0;
        int R = 0;
        int sum = arr[R];
        int len = 0;
        while (R < arr.length){
            if (sum == K) {
                len =  Math.max(R - L + 1, len);
                sum -= arr[L++];
            } else if (sum < K){
                R++;
                if (R == arr.length) break;
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1,1,1,4,2,1,1,1,1,1,1};
        System.out.println(maxSumK(arr, 6));
    }
}
