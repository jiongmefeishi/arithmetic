package cn.zqtao.code.nowcoder.offer.queue;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 剑指offer-用两个栈来实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 选择一个栈作为推，另一个栈作为弹
 * 每次弹出时，将剩余的元素再回写到第一个栈中
 */
public class QueueUseTowStack {
    // push用
    Stack<Integer> stack1 = new Stack<Integer>();
    // pop
    Stack<Integer> stack2 = new Stack<Integer>();

    // 推
    public void push(int node) {
        stack1.push(node);
    }

    // 弹
    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        // 弹出栈顶
        int pop = stack2.pop();

        // 回写到stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return pop;
    }
}
