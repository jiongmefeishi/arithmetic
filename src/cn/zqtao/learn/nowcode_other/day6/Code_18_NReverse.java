package cn.zqtao.learn.nowcode_other.day6;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @auther: zqtao
 * @description:
 * 小易有一个长度为n的整数序列, a_1. .., an。然后考虑在一个空序列b上进行n次以下操作:1、将ai放入b序列的末尾2、逆置b序列小易需要你计算输出操作n次之后的b序列。
 *
 * 输入描述:输入包括两行,第一行包括一个整数n(2 第二行包括n个整数ai(1 < a_i < 10~9),即序列a中的每个整数,以空格分割
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
