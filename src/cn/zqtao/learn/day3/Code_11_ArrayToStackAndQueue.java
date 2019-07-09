package cn.zqtao.learn.day3;

/**
 * @auther: zqtao
 * @description: 用数组结构实现大小固定的队列和栈
 *
 * 1、数组实现栈
 * 使用一个size指针，
 * 添加元素
 *      当size != arr.length 时可以向栈添加元素 size++
 *      当size == arr.lentth 时抛出数组越界异常
 * 弹出元素
 *      当size != arr.length 时可以从栈顶弹出元素，size - 1
 *      当size == arr.lentth 时抛出数组越界异常
 *
 *
 * 2、数组实现队列（这个难度要比实现栈高）
 * 使用三个指针
 * size
 * start --  表示弹出的元素在队列中所处的位置
 * end  --  表示新增的元素应该在队列中所处的位置
 *
 * @version: 1.0
 */
public class Code_11_ArrayToStackAndQueue {

    /**
     * 数组结构实现大小固定的栈
     */
    public static class ArrayStack {
        private Integer[] arr;
        private Integer size;

        public ArrayStack(int initSize) {
            if (initSize < 0)
                throw new IllegalArgumentException("The init size is less than 0");
            arr = new Integer[initSize];
            size = 0;
        }


        // 弹出栈顶元素值，但是不删除栈顶元素
        public Integer peek() {
            if (size == 0) return null;
            return arr[size - 1];
        }

        public void push(int obj) {
            if (size == arr.length)
                throw new ArrayIndexOutOfBoundsException("the stack is full");
            arr[size++] = obj;
        }

        // 弹出栈顶元素并删除栈顶元素，这里只是将下标前移
        public Integer pop() {
            if (size == 0)
                throw new ArrayIndexOutOfBoundsException("The stack is empty");
            return arr[--size];
        }
    }

    // 使用数组结构实现一个队列
    public static class ArrayQueue {
        private Integer[] arr;
        private Integer size;
        private Integer start; // 拿取一个数，拿取的是哪个位置上的数
        private Integer end; // 代表每次新增数应该存放的位置

        public ArrayQueue(int initSize) {
            if (initSize < 0)
                throw new IllegalArgumentException("The init size is less than 0");
            arr = new Integer[initSize];
            size = 0;
            start = 0;
            end = 0;
        }

        public void push(int obj) {
            // 是否满
            if (size == arr.length)
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            size++;
            arr[end] = obj;
            end = end == arr.length - 1 ? 0 : end + 1;
        }

        public Integer poll() {
            if (size == 0)
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            size--;
            int tmp = start;
            start = start == arr.length ? 0 : start + 1;
            return arr[tmp];
        }

        public Integer peek() {
            if (size == 0) throw new ArrayIndexOutOfBoundsException("The queue is empty");
            return arr[start];
        }
    }
}
