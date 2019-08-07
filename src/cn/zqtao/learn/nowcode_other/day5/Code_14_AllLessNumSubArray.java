package cn.zqtao.learn.nowcode_other.day5;

import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description: 最大值减去最小值小于或等于num的子数组数量
 * 本题需要滑动窗口和两个双端队列配合
 * 维护两个双端队列来 实时 更新滑动窗口的最大值和最小值
 *
 * @version: 1.0
 */
public class Code_14_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 维护两个双端队列
        LinkedList<Integer> qmin = new LinkedList<>(); // 头小尾大
        LinkedList<Integer> qmax = new LinkedList<>(); // 头大尾小

        // 滑动窗口
        int L = 0;
        int R = 0;

        int res = 0;
        while (L < arr.length) {

            while (R < arr.length) {

                // 小值队列更新维护
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast(); // 小值双端队列更新，遇大则弹出队列中的元素，直到合适
                }
                qmin.push(R);

                // 大值队列更新维护
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                    qmax.pollLast();
                }
                qmax.push(R);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break; // 如果子数组的最大值-最小值都不满足条件，那么无论窗口怎样滑动都不可能满足题目要求，跳出
                }
                R++;
            }

            // 排除掉无效的下标，上一个循环 L 向右移动可能导致双端队列中的最大值和最小值失效
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }

            // 滑动窗口如果是满足的 那么以 L为开头的子数组有 R-L个
            res += R - L;
            L++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));
    }
}
