package cn.zqtao.code.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 说明:
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * @version: 1.0
 */
public class ValidSuDu {
    public static void main(String[] args) {
        test();

        char[][] board = {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        char[][] board2 = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(isValidSudoku(board));

        System.out.println(isValidSudoku(board2));
    }

    /**
     * 思路：
     *
     * 将每行，每列，每宫，分别存储在一个map中
     * 计算每一点所在宫  boxIndex = (row / 3) * 3 + column /3
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        if (board.length == 0) return false;

        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        // 验证数独
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 每一点
                char c = board[i][j];
                if (c != '.'){
                    int num = c;

                    // 计算此点所在宫
                    int boxIndex = (i / 3) * 3 + j / 3;

                    // 存储当前点，行，列，宫
                    rows[i].put(num, rows[i].getOrDefault(num, 0) + 1);
                    columns[j].put(num, columns[j].getOrDefault(num, 0) + 1);
                    boxes[boxIndex].put(num, boxes[boxIndex].getOrDefault(num, 0) + 1);

                    // 检查当前点是否已经存在
                    if (rows[i].get(num) > 1 || columns[j].get(num) > 1 || boxes[boxIndex].get(num) > 1)
                        return false;
                }
            }
        }


        return true;
    }

    /**
     * 测试Map 方法
     * getOrDefault()
     */
    public static void test() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, map.getOrDefault(1, 5) + 1);
        System.out.println(map.get(1));

        map.put(1, map.getOrDefault(1, 5) + 1);
        System.out.println(map.get(1));

        // 结论：先验证KEY 是否存在，存在则返回VALUE；不存在则返回默认值
    }
}
