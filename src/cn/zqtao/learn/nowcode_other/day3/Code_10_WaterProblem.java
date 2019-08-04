package cn.zqtao.learn.nowcode_other.day3;

/**
 * @auther: zqtao
 * @description: 直方图装水问题
 * @version: 1.0
 */
public class Code_10_WaterProblem {

    // 暴力求解
    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) { // 只有两端是无法进行装水的
            return 0;
        }

        int value = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = 0; j < i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }

            for (int j = i + 1; j < arr.length; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }

            value += Math.max(0, Math.min(maxLeft, maxRight) - arr[i]);
        }
        return value;
    }

    // 预处理数组
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }

        int n = arr.length - 2; // 左右两端就是当前最大，不需要求
        int[] maxLefts = new int[n]; // 左边最大预处理数组
        int[] maxRights = new int[n]; // 右边最大预处理数组

        // 初始化
        maxLefts[0] = arr[0]; // 左端
        maxRights[n - 1] = arr[arr.length - 1]; // 右端

        for (int i = 1; i < n; i++) {
            maxLefts[i] = Math.max(maxLefts[i - 1], arr[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            maxRights[i] = Math.max(maxRights[i + 1], arr[i + 2]);
        }
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(maxLefts[i - 1], maxRights[i - 1]) - arr[i]);
        }
        return value;
    }

    // 预处理数组，省去一个数组，只需要右预处理数组
    public static int getWater3(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length - 2;
        int[] maxRights = new int[n];
        maxRights[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRights[i] = Math.max(maxRights[i + 1], arr[i + 2]);
        }
        int maxLeft = arr[0];
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(maxLeft, maxRights[i - 1]) - arr[i]);
            maxLeft = Math.max(maxLeft, arr[i]);
        }
        return value;
    }

    // 双指针 O(1) 空间复杂度 O(N) 时间复杂度
    public static int getWater4(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;

        // 双指针
        int L = 1;
        int R = arr.length - 2;

        int maxL = arr[0];
        int maxR = arr[arr.length - 1];

        while (L <= R) {
            if (maxL <= maxR) {
                value += Math.max(0, maxL - arr[L]);
                maxL = Math.max(maxL, arr[L++]);
            } else {
                value += Math.max(0, maxR - arr[R]);
                maxR = Math.max(maxR, arr[R--]);
            }
        }
        return value;
    }

    // for test
    public static int[] generateRandomArr() {
        int[] arr = new int[(int) (Math.random() * 98) + 2]; // 产生0 ~ 100中长度至少为3的随机数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 200) + 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            int[] arr = generateRandomArr();
//            int[] arr = {9,1,2,6,4};
            int val1 = getWater1(arr);
            int val2 = getWater2(arr);
            int val3 = getWater3(arr);
            int val4 = getWater4(arr);

            if (val1 != val2 || val3 != val4 || val1 != val3) {
                System.out.println("算法出错 start");
                System.out.println("val1=" + val1 + "  val2=" + val2 + "  val3=" + val3 + "  val4=" + val4);
                System.out.println("算法出错 end");
            }
        }
    }
}
