package cn.zqtao.learn.nowcode_other.day5;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 最大矩形
 *  时间复杂度 O(N*M)
 * @version: 1.0
 */
public class Code_13_MaximalRectangle {

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }

        int maxArea = 0; // 结果
        int[] height = new int[map[0].length]; // 数组压缩时的辅助数组，理解为矩形高度

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) { // 矩阵压缩
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1; // 遇0，拆解当前位置所有的小矩形
            }

            // 求解当前行为底构成的直方图面积，即最大矩形
            maxArea = maxRecFromBottom(height);
        }
        return maxArea;
    }

    //
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;

        Stack<Integer> toBiggerStack = new Stack<>(); // 递增单调栈, 存储的是下标
        for (int i = 0; i < height.length; i++) {
            while (!toBiggerStack.isEmpty() && height[i] <= height[toBiggerStack.peek()]) {
                int index = toBiggerStack.pop();
                // 左边最近小于弹出的数的下标
                int leftSmallNumIndex = toBiggerStack.isEmpty() ? -1 : toBiggerStack.peek();
                int curArea = (i - leftSmallNumIndex - 1) * height[index]; // 关键i - leftSNI - 1
                maxArea = Math.max(maxArea, curArea);
            }
            toBiggerStack.push(i);
        }

        while (!toBiggerStack.isEmpty()) { // 单调栈中依然有元素未弹出
            int index = toBiggerStack.pop();
            int leftSmallNumIndex = toBiggerStack.isEmpty() ? -1 : toBiggerStack.peek();
            // 注意，此时可以肯定index后的元素一定都是大于等于height[index] 的
            // 所以本身加上后面的长度是height.length - leftSNI - 1
            // 如直方图全是递增数 4 5 6 7 ,单调栈剩余 4 5 ，弹出5时 ，左边最近比5 小的下标是0
            // 4 - 0 -1 = 3 就是 5 6 7 三列的宽度；
            int curArea = (height.length - leftSmallNumIndex - 1) * height[index];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0},};
        System.out.println(maxRecSize(map));
    }
}
