package cn.zqtao.learn.nowcode_other.day2;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 递归逆序栈
 * @version: 1.0
 */
public class Code_05_ReverseStackByRecur {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int lastElement = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(lastElement); // 回写
    }

    // 获取并删除栈底元素，其他元素保持不变
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        Integer res = stack.pop();

        if (stack.isEmpty()) {
            return res;
        } else {
            int lastElement = getAndRemoveLastElement(stack);
            stack.push(res);
            return lastElement;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
