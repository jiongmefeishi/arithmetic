package cn.zqtao.learn.nowcode_other.day4;

/**
 * @auther: zqtao
 * @description:
 * 给定一个长度为N (N>1)的整型数组arr,可以划分成左右两个部分,
 *
 * 左部分为arr [0..K],右部分为arr [K+1..N-1], K可以取直的范围是[0, N-2]。
 *
 * 求这么多划分方案中,左部分中的最大值减去右部分最大值的绝对值中,最大是多少?
 *
 * 列如: [2, 7, 3, 1, 1],当左部分为[2,7],右部分为[3, 1, 1]时,左部分中的最大值减去右部分最大值的绝对值为4。
 *
 * 当左部分为 [2,7, 3],右部分为[1,1]时,左部分中的最大值减去右部分最大值的绝对值为6。
 *
 * 还有很多划分方案,但最终返回6。
 * @version: 1.0
 */
public class Code_12_MaxABSBetweenLeftAndRight {
    
    // 预处理数组
    // 使用两个数组分别记录 0~i 上的最大值，和i+1 ~ N-1范围的最大值。
    // 这样就可以随时取出任意i 位置为划分时，两边的最大值
    public static int maxABS1(int[] arr) {
        int[] maxL = new int[arr.length];
        int[] maxR = new int[arr.length];

        // 左  -- > max
        maxL[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxL[i] = Math.max(maxL[i - 1], arr[i]);
        }

        // 右 --> max
        maxR[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0 ; i--) {
            maxR[i] = Math.max(maxR[i+1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            res = Math.max(res, Math.abs(maxL[i] - maxR[i+1]));
        }
        return res;
    }

    /*
    思路2：思维跳跃

    这是一个凭经验的思维跳跃解法。

        1、先遍历一遍数组，找到数组的最大值max。

        2、将max分别划分到左右两个部分进行考虑

    一、当max划分到左边部分
        回头看看题意：左边部分最大值-右边部分最大值。
        现在全局最大值在左边部分，并且需要右边部分找一个最大值
        res=max-右边部分最大值maxR
        想要res 尽可能的大，那么右边部分的最大值就要尽可能的小，什么时候maxR 尽可能的小呢？答案是 maxR=arr[N-1] 即最后一个元素时最小。
            举个栗子：
            5 2 9 2 3 5 4
        现在max=9 划分到了左边，那么maxR=4是右边部分最大值中较小的。只有一个元素就是最小的，如果多纳入元素，最大值可能就会变大，如纳入5之后，最大值就变成了5而不是4

        同理max划分在右边部分
        左边部分最大值尽可能的小，那么就是第一个元素arr[0]

     */
    public static int maxABS2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return Math.abs(max - Math.min(arr[0], arr[arr.length - 1]));
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
    }
}
