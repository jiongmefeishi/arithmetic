package cn.zqtao;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。
 * 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperatures {

    public static int[] method(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] split = line.split(",");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                String trim = split[i].trim();
                if (trim.startsWith("[")){
                    String replace = trim.replace("[", "");
                    arr[i] = Integer.valueOf(replace);
                } else if (trim.endsWith("]")) {
                    String replace = trim.replace("]", "");
                    arr[i] = Integer.valueOf(replace);
                } else {
                    arr[i] = Integer.valueOf(trim);
                }
            }
            System.out.println(Arrays.toString(method(arr)));
        }
        in.close();
    }
}
