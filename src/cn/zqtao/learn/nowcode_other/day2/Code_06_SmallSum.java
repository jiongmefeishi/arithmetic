package cn.zqtao.learn.nowcode_other.day2;

/**
 * @auther: zqtao
 * @description: 小和问题
 * 改写归并排序即可
 *
 * 数组小和的定义如下:
 *
 * 例如,数组s=[1, 3, 5, 2, 4, 6],
 *
 * 在s[0]的左边小于或等于s[0]的数的和为0,
 *
 * 在s[1]的左边小于或等于s[1]的数的和为1,
 *
 * 在s [2]的左边小于或等于s[2]的数的和为1+3-4,
 *
 * 在s[3]的左边小于或等于s[3的数的和为1,
 *
 * 在s[4]的左边小于或等于s[4]的数的和为1+3+2-6,
 *
 * 在s[5]的左边小于或等于s[5]的数的和为 1+3+5+2+4-15,
 *
 *
 * 所以s的小和为0+1+4+1+6+15-27.给定一个数组s,实现函数返回s的小和。
 */
public class Code_06_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergesort(arr, 0, arr.length - 1);
    }

    public static int mergesort(int[] arr, int L, int R) {
        if (L == R) return 0;

        int mid = L + ((R - L) >> 1);
        return mergesort(arr, L, mid) +
                mergesort(arr, mid + 1, R) +
                merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        if (L == R) {
            return 0;
        }

        int[] help = new int[R - L + 1];
        int i = 0;

        int smallSum = 0;
        int p1 = L; // 双指针外排方式排序
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            smallSum = arr[p1] <= arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid)
            help[i++] = arr[p1++];
        while (p2 <= R)
            help[i++] = arr[p2++];

        for (i = 0; i < help.length; i++)
            arr[L + i] = help[i];
        return smallSum;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 7};
        System.out.println(smallSum(arr));
    }
}
