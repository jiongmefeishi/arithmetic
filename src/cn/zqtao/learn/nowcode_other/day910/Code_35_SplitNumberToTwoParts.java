package cn.zqtao.learn.nowcode_other.day910;

import java.util.ArrayList;

/**
 * 思路：背包问题
 * 背包问题就是行是可选择的数，列是能组合成的各种可能结果
 */
public class Code_35_SplitNumberToTwoParts {
    /**
     * 将num 分解为独立的数
     * 求和，然后求是否能够组合成 sum/2
     * <p>
     * dp[][]
     */
    public static boolean isMagic(int num) {
        int sum = 0;
        ArrayList<Integer> nums = new ArrayList<>();
        while (num != 0) {
            int n = num % 10;
            nums.add(n);
            sum += n;
            num /= 10;
        }

        if ((sum & 1) == 1) return false; // 和是奇数无法加出 sum / 2
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.size()][sum + 1];
        dp[0][0] = true;
        if (nums.get(0) <= sum) dp[0][nums.get(0)] = true; // 初始化第一行，只有第一个数时，这个数不能超过 sum
        for (int i = 1; i < nums.size(); i++) {
            for (int j = sum; j >= 0; j--) {
                dp[i][j] = dp[i - 1][j] || (j - nums.get(i) >= 0 ? dp[i - 1][j - nums.get(i)] : false);
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (dp[i][sum]) {
                return true;
            }
        }
        return false;
    }

    /**
     * dp[]
     * 降维处理，节约空间资源
     *
     * 从右往左进行填充，不要从左往右进行填充，
     * i 表示第 i 行，那么从左往右填充时，它依赖的上一行的数已经改变
     * 例如
     *
     * 224
     *      0 1 2 3 4 5 6 7 8
     * 2    T F F T F F F F F
     * 2
     * 4
     *
     * 如上从左往右进行遍历时，i=1,arr[i]=2,那么j=5号位置的填充，
     * 需要依赖的是上一行i=0时的 j-arr[i]=3 和 5 两个位置，
     * 由于从左往右进行更新，那么 3 号位置的信息已经被修改，不再是原来上一行的信息了
     */
    public static boolean isMagic2(int num) {
        int sum = 0;
        int tmp = num;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        if ((sum & 1) == 1) return false;// 奇数
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        num = tmp;
        int cur = 0;
        while (num != 0) {
            cur = num % 10;
            for (int i = sum; i >= 0; i--) { // 从右往左进行填表
                dp[i] = dp[i] || (i - cur >= 0 ? dp[i - cur] : false);
            }
            if (dp[sum]) return true; // 只要加出sum 立即结束
            num /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int num = (int) (Math.random() * 100000 + 1);
            try {
                if (num != 0 && isMagic(num) != isMagic2(num)) {
                    System.out.println(num);
                    System.out.println(isMagic(num));
                    System.out.println(isMagic2(num));
                }
            } catch (Exception e) {
                System.out.println(num + "\t\t" + e.toString());
            }
        }
    }
}
