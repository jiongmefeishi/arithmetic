package cn.zqtao.learn.nowcode_other.day6;

/**
 * @auther: zqtao
 * @description: 纸牌博弈
 * 排成一条线的纸牌博弈问题【题目】给定一个整型数组arr,代表数值不同的纸牌排成一条线。
 * 玩家 A和玩家B依次拿走每张纸牌,规定玩家A先拿,玩家B后拿,
 * 但是每个玩家每次只能拿走最左或最右的纸牌,玩家A和玩家B都绝顶聪明。
 * 请返回最后获胜者的分数。【举例】arr=[1, 2, 100, 4]。
 * 开始时玩家A只能拿走1或4。如果玩家A拿走1,则排列变为 2, 100, 4],接下来玩家B可以拿走2或4,然后继续轮到玩家A。
 * @version: 1.0
 */
public class Code_20_SelectedCards {

    // 暴力尝试
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    // 单独调用自己的暴力尝试
    public static int process(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        if (i == j - 1) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(arr[i] + Math.min(process(arr, i + 2, j), process(arr, i + 1, j - 1)),
                arr[j] + Math.min(process(arr, i + 1, j - 1), process(arr, i, j - 2))
        );
    }

    // 动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }
}
