package cn.zqtao.learn.day3;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description:
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返 回栈中最小元素的操作。
 * 【要求】
 * 1．pop、push、getMin操作的时间复杂度都是O(1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 * @version: 1.0
 */
public class Code_12_GetMinStack {

    /**
     * 准备两个栈
     * 一个栈用来存依次进行的数据
     * 一个栈用来存最小值
     *
     * 两个栈存数据都是同步的
     */
    public static class MinStack{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MinStack(){
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val){

            // 最小值栈如果是空栈，直接入栈
            if (minStack.isEmpty()){
                minStack.add(val);
            } else if (minStack.peek() <= val){
                minStack.add(minStack.peek());
            } else {
                minStack.add(val);
            }
            dataStack.add(val);
        }

        public Integer pop(){
            if (dataStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            minStack.pop();
            return dataStack.pop();
        }

        public Integer peek(){
            if (dataStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            return dataStack.peek();
        }

        public Integer getMinNum(){
            if (dataStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            return minStack.peek();
        }

    }

    /**
     * 第二种方式
     *
     * 也是准备两个栈
     * 和第一个不同的是，minStack 只有在出现比当前栈顶小的数的时候才向minStack 中添加数据
     * 也就是两个栈添加和弹出元素不是同步的，
     * getMin 每次使用 peek 方法返回栈顶
     */
    public static class MinStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MinStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        // 弹出dataStack中的栈顶元素时，更新minStack的状态
        // 如果弹出的元素和minStack的栈顶元素一样则证明是最小值，此时需要同步弹出minStack栈顶元素
        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        System.out.println(minStack.getMinNum());
        minStack.push(4);
        System.out.println(minStack.getMinNum());
        minStack.push(1);
        System.out.println(minStack.getMinNum());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMinNum());
    }
}
