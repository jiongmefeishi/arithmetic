package cn.zqtao.examintion;

/**
 * 完美序列
 * 最长的一段连续子序列满足，每个数字都要大于等于前面所有数字的和。
 */
public class Code_12_PrefectSequence {

    /**
     * 滑动窗口 L R，来维护窗口的边界
     * 维护一个变量 sum 表示窗口内每一个元素的和
     */
    public static int prefectSeq(int[] arr) {
        int sum = 0;
        int L = 0;
        int R = 0;
        int len = 0;
        sum = 0;
        while (R < arr.length) {
            if (arr[R] >= sum) { // 满足完美序列
                len = Math.max(len, R - L + 1);
                sum += arr[R++]; // 窗口扩容
            } else { // 不满足完美序列，窗口重置
                sum = arr[R]; // sum = 当前数
                L = R++; // 左边界等于当前位置
                len = Math.max(len, R - L + 1);
            }
        }
        return len;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 9, 2, 6};
        int[] arr2 = {4, 2, 9, 16, 7};
        System.out.println(prefectSeq(arr));
        System.out.println(prefectSeq(arr2));
    }
}