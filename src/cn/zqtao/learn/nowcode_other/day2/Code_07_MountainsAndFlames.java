package cn.zqtao.learn.nowcode_other.day2;

import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 小B首都防卫工作
 * @version: 1.0
 */
public class Code_07_MountainsAndFlames {

    // 数据结构化处理
    public static class Pair {
        public int value; // 元素值
        public int times; // 元素在数组中出现的次数

        public Pair(int value) {
            this.value = value;
        }
    }

    // 根据一个元素当前在栈中出现的次数，算出本身存在的对数
    // 如 2 2 2 是 3 对 C(3,2)
    public static long getInternalSum(int times) {
        return times == 1 ? 0L : (long) times * (long) (times - 1) / 2L;
    }

    /**
     * 返回下一个元素的下标  3 5 1  环形结构，1 的下一个数3 的下标 0
     *
     * @param size 数组长度
     * @param i    当前下标
     * @return 下一个下标
     */
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? i + 1 : 0;
    }

    /**
     * 第一步：找到最大值
     * 第二步：以最大值开始进行遍历入栈
     * 依次入栈并处理对数
     * 第三步：栈中剩余的元素对数
     */
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) { // 找打最大数
            maxIndex = arr[maxIndex] < arr[i] ? maxIndex = i : maxIndex;
        }

        int value = arr[maxIndex];
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));

        int size = arr.length;
        int index = nextIndex(size, maxIndex);

        long res = 0L;
        while (index != maxIndex) { // 数组循环，存在元素
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) { // 大于
                int times = stack.pop().times; // 在栈中已经存在的次数
                long internalSum = getInternalSum(times); // 同数组成的对数
                // 栈中数 5 222  来了value=3 对数=222组成的对数 + 222 分别同 5 和 3 组成的对数（times * 2）
                res += internalSum + times * 2;
            }

            if (!stack.isEmpty() && stack.peek().value == value) { // 等于
                stack.peek().times++;
            } else { // 小于 ，入栈
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }

        while (!stack.isEmpty()) { // 经过上述过程，数组元素遍历完毕，但是栈中还有元素
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) { // 排除是最后栈底元素情况
                res += times; // 先加一个方向和下一个数的对数

                if (stack.size() > 1) { // 不是最后两位数
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0; // 栈底元素只有入一次栈
                    // 5 222   2只能向一个方向找到5，另一个方向找到的是同一个5，不需要。
                }
            }
        }
        return res;
    }
}
