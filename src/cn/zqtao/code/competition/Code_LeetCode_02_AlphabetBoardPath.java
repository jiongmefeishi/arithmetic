package cn.zqtao.code.competition;

/**
 * @auther: zqtao
 * @description: 字母板上的路径
 *
 * 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].
 * 我们可以按下面的指令规则行动：
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 *
 * 61 / 61 个通过测试用例
 * 执行用时：3 ms
 * @version: 1.0
 */
public class Code_LeetCode_02_AlphabetBoardPath {

    public static String alphabetBoardPath(String target) {
        char[] chars = target.toCharArray();
        int tIndex = 0; // 桶指针
        int pIndex = 0; // 桶内具体位置指针
        int tong = 0; // 桶号
        int p = 0; // 桶内位置号

        String res = "";
        for (char c : chars) {
            int num = c - 'a';
            p = num % 5; // 桶内目标位置
            tong = num / 5; // 桶

            // 移动桶
            String stong = tIndex >= tong ? "U" : "D"; // 上下方向
            int tmp1 = Math.abs(tIndex - tong);
            // 移动位
            String snum = pIndex >= p ? "L" : "R"; // 左右方向
            int tmp2 = Math.abs(pIndex - p);

            if (tong == 5 && Math.abs(p - pIndex) > 0) { // 针对最后一个z 进行处理，先进行左右移动，在进行上下移动
                while (tmp1 > 1) {
                    res += stong;
                    tmp1--;
                }
            } else {
                while (tmp1 > 0) {
                    res += stong;
                    tmp1--;
                }
            }

            while (tmp2 > 0) {
                res += snum;
                tmp2--;
            }

            if (tmp1 > 0) { // z 处理最后一次上下移动
                res += stong;
            }

            tIndex = tong; // 更新桶位置
            pIndex = p; // 更新移动位置

            res += "!";
        }
        return res;
    }
 /*   public static String alphabetBoardPath1(String target) {

        char[] chars = target.toCharArray();

        int tIndex = 0; // 桶指针
        int pIndex = 0; // 桶内具体位置指针

        String res = "";

        int tong = 0; // 桶号
        int p = 0; // 桶内位置号

        for (char c : chars) {
            int num = c - 'a';
            System.out.println("num: " + num);

            p = num % 5; // 桶内目标位置
            tong = num / 5; // 桶

            System.out.println("tong " + tong);
            System.out.println("p " + p);

            // 移动桶
            String stong = tIndex >= tong ? "U" : "D"; // 上下方向
            int tmp1 = Math.abs(tIndex - tong);
            System.out.println("上下移动：" + stong + "多少步 : " + tmp1);


            // 移动位
            String snum = pIndex >= p ? "L" : "R"; // 左右方向
            int tmp2 = Math.abs(pIndex - p);
            System.out.println("左右移动：" + snum + "多少步 : " + tmp2);


            if (tong == 5 && Math.abs(p - pIndex) > 0) { // 针对最后一个z 进行处理，先进行左右移动，在进行上下移动

                while (tmp1 > 1) {
                    res += stong;
                    tmp1--;
                }

            } else {
                while (tmp1 > 0) {
                    res += stong;
                    tmp1--;
                }
            }

            while (tmp2 > 0) {
                res += snum;
                tmp2--;
            }

            if (tmp1 > 0) { // 处理最后一次上下移动
                res += stong;
            }

            tIndex = tong; // 更新桶位置
            pIndex = p; // 更新移动位置

            res += "!";

            System.out.println("\n\n");
        }
        return res;
    }*/

    public static void main(String[] args) {
        System.out.println(alphabetBoardPath("code"));
        System.out.println("=============");

        System.out.println(alphabetBoardPath("leet"));
        System.out.println("=============");

        System.out.println(alphabetBoardPath("z"));
        System.out.println("=============");

        System.out.println(alphabetBoardPath("zdz"));
        System.out.println("=============");

    }
}
