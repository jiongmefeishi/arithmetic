package cn.zqtao.examintion;

/**
 * 数组|s1-s2|最小值
 */
public class Code_09_ArrayTowPartMinSum {
    public static int method(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 2) {
            return Math.abs(arr[0] - arr[1]);
        }

        int[] sL = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sL[i] = sum;
        }

        sum = 0;
        int[] sR = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sum += arr[i];
            sR[i] = sum;
        }

        int s1 = 0;
        int s2 = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length - 1; i++) {
            s1 = sL[i];
            s2 = sR[i + 1];
            min = Math.min(min, Math.abs(s1 - s2));
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,999};
        System.out.println(method( arr));
        int[] arr2 = {2,4,5,6,9};
        System.out.println(method(arr2));
        int[] arr3 = {2,2,2,2};
        System.out.println(method(arr3));
    }
}