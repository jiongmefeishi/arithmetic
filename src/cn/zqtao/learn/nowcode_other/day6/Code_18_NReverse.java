package cn.zqtao.learn.nowcode_other.day6;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_18_NReverse {

    public static int[] reverse(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();// 双端队列
        boolean convert = false; //  标记是否需要逆序
        for (int i = 0; i < arr.length; i++) {
            if (convert) {
                deque.addLast(arr[i]);
            } else { // 不需要转换就加入双端队列的头部
                deque.addFirst(arr[i]);
            }
            convert = !convert;
        }

        // 查看结果是否需要逆序
        int i = 0;
        if (convert) {
            while (!deque.isEmpty())
                arr[i++] = deque.pollFirst();
        } else {
            while (!deque.isEmpty()) {
                arr[i++] = deque.pollLast();
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        reverse(arr);
        System.out.println(Arrays.toString(arr));

        StringBuffer stringBuffer = new StringBuffer();
    }
}
