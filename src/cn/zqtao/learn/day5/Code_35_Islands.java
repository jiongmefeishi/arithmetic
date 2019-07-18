package cn.zqtao.learn.day5;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_35_Islands {

    public static int countIslands(int[][] m){
        if (m == null || m[0] == null) {
            return 0;
        }

        int r = m.length; // 矩阵行数
        int c = m[0].length; // 矩阵列数

        int res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 1){
                    res++;
                    infect(m, i, j, r, c);// 感染函数，将每一个感染点置为2
                }
            }
        }

        return res;
    }

    /**
     * 感染函数，递归感染连续的 1 区域
     *
     * 按照当前点的上下左右进行扩散感染
     *
     * 终止条件
     * 1、不要越界
     * 2、当前点已经被感染 为 2
     * 3、当前点为 0
     *
     * @param m 矩阵
     * @param i 点横坐标
     * @param j 点纵坐标
     * @param r 矩阵行数
     * @param c 矩阵列数
     */
    public static void infect(int[][] m, int i, int j, int r, int c){
//        if (i < 0 || i >= r || j < 0 || j >= c // 坐标不越界
//                || m[i][j] != 1 // 当前点0或2，不是1
//        ){
//            return; // 不需要感染
//        }

        if (i < 0 || i >= r || j < 0 || j >= c || m[i][j] != 1){
            return;
        }
        m[i][j] = 2;

        infect(m, i + 1, j, r, c);
        infect(m, i - 1, j, r, c);
        infect(m, i, j + 1, r, c);
        infect(m, i, j - 1, r, c);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }

}
