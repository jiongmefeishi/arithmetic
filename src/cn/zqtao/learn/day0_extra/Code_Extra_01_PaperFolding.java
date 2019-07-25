package cn.zqtao.learn.day0_extra;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_Extra_01_PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true); // 第一层，下
    }

    /**
     * @param level 当前是第几层
     * @param n     总共几层（即折几次）
     * @param down  当前层是否是下
     */
    private static void printProcess(int level, int N, boolean down) {
        if (level > N) {
            return;
        }

        printProcess(level + 1, N, true);
        System.out.println(down ? "down" : "up");
        printProcess(level + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
