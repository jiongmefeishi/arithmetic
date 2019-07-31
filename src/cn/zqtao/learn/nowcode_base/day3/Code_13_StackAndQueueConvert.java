package cn.zqtao.learn.nowcode_base.day3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 如何仅用队列结构实现栈结构？
 * 两个队列实现栈
 * <p>
 * 如何仅用栈结构实现队列结构？
 * 两个栈实现队列
 * @version: 1.0
 */
public class Code_13_StackAndQueueConvert {
    // 两个栈实现队列
    // 1、必须满足每次transfer数据时pop队列必须是空
    // 2、必须满足每次都transfer完所有数据
    public static class TowStackToQueue {

        private Stack<Integer> pushStack; // 专用添加
        private Stack<Integer> popStack; // 专用弹出

        public TowStackToQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(int val) {
            pushStack.add(val);
        }

        public Integer poll() {
            if (pushStack.empty() && popStack.empty()) throw new RuntimeException("the queue is empty");
            transfer();
            return popStack.pop();
        }

        public Integer peek() {
            if (pushStack.empty() && popStack.empty()) throw new RuntimeException("the queue is empty");
            transfer();
            return popStack.peek();
        }

        public void transfer(){
            // 如果popStack 不为空，不能导数据
            if (!popStack.isEmpty()){
                return;
            }

            while (!pushStack.isEmpty()){
                popStack.add(pushStack.pop());
            }
        }
    }


    // 两个队列实现栈结构
    public static class TwoQueuesStack {
        private Queue<Integer> dataQueue;
        private Queue<Integer> helpQueue;

        public TwoQueuesStack() {
            dataQueue = new LinkedList<>();
            helpQueue = new LinkedList<>();
        }

        public void push(int val) {
            dataQueue.add(val);
        }

        public Integer peek() {
            if (dataQueue.isEmpty())
                throw new RuntimeException("Stack is empty!");
            while (!dataQueue.isEmpty()){
                helpQueue.add(dataQueue.poll());
            }

            Integer res = helpQueue.peek();
            swap(dataQueue, helpQueue);
            return res;
        }

        public Integer pop(){
            if (dataQueue.isEmpty())
                throw new RuntimeException("Stack is empty!");
            while (dataQueue.size() > 1){ // 只剩一个数停止
                helpQueue.add(dataQueue.poll());
            }

            Integer res = dataQueue.poll();
            swap(dataQueue, helpQueue);
            return res;
        }

        private void swap(Queue<Integer> a, Queue<Integer> b) {
            Queue<Integer> tmp = a;
            a = b;
            b = tmp;
        }
    }
}
