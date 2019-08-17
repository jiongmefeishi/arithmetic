package cn.zqtao.learn.nowcode_other.day7;

/**
 * @auther: zqtao
 * @description: 戳气球
 * @version: 1.0
 */
public class Code_24_MaxCoins {

    // 暴力尝试
    public static int maxCoins1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length - 1] = 1;
        for (int i = 0; i < arr.length; i++) {
            help[i + 1] = arr[i];
        }
        return process(help, 1, help.length - 2);
    }

    // 尝试最后打的位置获得的收益
    // 不尝试以某个位置第一个打获得的最大收益
    // 尝试以某一个位置为最后一个打，获得的最大收益，
    // 把每一个位置都作为最后一个打掉的位置尝试一遍就是解
    public static int process(int[] arr, int l, int r) {
        if (l == r) { // 这个范围只有一个数
            return arr[l - 1] * arr[l] * arr[r + 1];
        }

        // 尝试，最后打L 获得的收益，最后打L+1获得的收益...最后打R获得的收益
        // 这些收益中最大的，就是L到R范围上收益最大的
        int max = Math.max(
                arr[l - 1] * arr[l] * arr[r + 1] + process(arr, l + 1, r), // 最后打L
                arr[l - 1] * arr[r] * arr[r + 1] + process(arr, l, r - 1)); // 最后打R，两者取最大

        // 尝试中间作为最后打掉获得的最大收益
        // L~i~R  尝试i是最后一个打掉的
        // 那么收益是 L~i-1 范围最大收益 + i+1~R上最大收益 + i 位置收益
        for (int i = l + 1; i < r; i++) {
            max = Math.max(
                    max,
                    arr[l - 1] * arr[i] * arr[r + 1] // i 位置收益
                            + process(arr, l, i - 1)  // L~i-1 范围收益
                            + process(arr, i + 1, r)); // i+1~R 范围收益
        }
        return max;
    }

    public static int maxCoins2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int[] all = new int[size + 2];
        all[0] = 1;
        all[size + 1] = 1;
        for (int i = 0; i < size; i++) {
            all[i + 1] = arr[i];
        }
        int[][] dp = new int[size][size];
        for (int row = size - 1; row >= 0; row--) {

            dp[row][row] = all[row] * all[row + 1] * all[row + 2];// base case

            for (int col = row + 1; col < size; col++) {
                int coins = 0;

                for (int k = row; k <= col; k++) { // 以 i 位置为最后一个打的最大收益
                    coins = all[row] * all[k + 1] * all[col + 2];
                    coins += k != row ? dp[row][k - 1] : 0; // 依赖左边一行
                    coins += k != col ? dp[k + 1][col] : 0; // 依赖下边一列
                    dp[row][col] = Math.max(dp[row][col], coins);


                    /*
                    就是暴力递归里面的 以 i 位置为最后一个获得的收益
                    max = Math.max(
                        max,
                        arr[l - 1] * arr[i] * arr[r + 1] // i 位置收益
                                + process(arr, l, i - 1)  // L~i-1 范围收益
                                + process(arr, i + 1, r)); // i+1~R 范围收益
                     */
                }
            }

        }
        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 6, 4, 2, 7, 4, 7, 9 };
        System.out.println(maxCoins1(arr));
        System.out.println(maxCoins2(arr));
    }
}