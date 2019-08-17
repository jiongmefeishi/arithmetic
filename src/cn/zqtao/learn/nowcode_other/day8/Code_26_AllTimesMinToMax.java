package cn.zqtao.learn.nowcode_other.day8;

import java.util.Stack;

/**
 * 最小数*区间所有数的和
 *
 * 给定一个数组序列,需要求选出一个区间,使得该区间是所有区间中经过如下计算的值最大的一个:
 *
 * 区间中的最小数*区间所有数的和最后程序
 *
 * 输出经过计算后的最大值即可,不需要输出具体的区间。如给定序列[6 2 1]则根据上述公式,
 * 可得到所有可以选定各个区间的计算值:
 *
 * [6] =6* 6=36;
 *
 * [2] =2*2=4 ；
 *
 * [1] =11=1;
 *
 * [6,2] =2 8= 16;
 *
 * [2,1] =13=3;
 *
 * [6, 2, 1] =1 * 9= 9;
 *
 * 从上述计算可见选定区间[6] ,计算值为36,则程序输出为36区间内的所有数字都在[0, 100]的范围内;
 */
public class Code_26_AllTimesMinToMax {

	// 暴力
	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) { // 0~N-1
			for (int j = i; j < arr.length; j++) { // i~N-1
				int minNum = Integer.MAX_VALUE;
				int sum = 0;

				for (int k = i; k <= j; k++) { // i~j 之间子数组
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, minNum * sum);
			}
		}
		return max;
	}

	// 单调栈
	public static int max2(int[] arr) {
		int size = arr.length;
		int[] sums = new int[size]; // 预处理数组
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];// 计算arr数组每一项和
			// 6 2 8
			// 6 8 16
		}
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>(); // 单调栈：递减栈，快速找到一个元素两边最近比它大的数
		// 这里单调栈存放下标

		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop(); // 当前区间最小
				max = Math.max(max,
						(stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()]))
								* arr[j]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) { // 余栈处理
			int j = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? sums[size - 1]
					: (sums[size - 1] - sums[stack.peek()])) * arr[j]);
		}
		return max;
	}

	public static int[] gerenareRondomArray() {
		int[] arr = new int[(int) (Math.random() * 20) + 10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 101);
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerenareRondomArray();
			if (max1(arr) != max2(arr)) {
				System.out.println("FUCK!");
				break;
			}
		}

	}

}
