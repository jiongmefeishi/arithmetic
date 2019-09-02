package cn.zqtao.learn.nowcode_other.day11;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: XOR
 * 给出n个数字a_1..... an,问最多有多少不重叠的非空区间,使得每个区间内数字的xor都等于0.
 *
 * 输入描述:第一行一个整数n; 第二行n个整数 a.1..... an;对于 30%的数据, n<=20; 对于100%的数据, n<=100000 a_i<=100000;
 *
 * 输出描述:一个整数表示最多的区间个数;
 *
 * 示例1输入
 *
 * 4
 *
 * 3 0 2 2 输出 2
 * @version: 1.0
 */
public class Code_39_XOR {

    /**
     * 思路：
     * 维护一个map和xor 变量
     *
     * xor: 0~i 异或结果
     * map：KEY --> 存储 0~i 异或结果(xor)     VALUE --> 记录结果所在的位置（实时更新）
     *
     * 每一次 0~i 上得到 xor 都向 map 中查询上一次出现 xor 结果的位置 j，那么 j+1~i 构成一个结果区域（异或为0）
     */
    public static int mostXORs0(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int ans = Integer.MIN_VALUE;
        int xor = 0;
        int[] mosts = new int[arr.length]; // 记录0~i 位置上能够得到的最多的区域是多少？
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);// 初始化map，应对第一次出现xor=0 的情况，表示0~i 位置第一次xor=0时，结果mosts[i]=0

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)){ // 之前存在xor
                int j = map.get(xor);
                mosts[i] = j == -1 ? 1 : mosts[j] + 1;
            }

            mosts[i] = i == 0 ? mosts[i] : Math.max(mosts[i], mosts[i - 1]); // 之前不存在或者是现在所划分的没有i-1 位置划分的区域多
            map.put(xor, i);
            ans = Math.max(ans, mosts[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,2,0,2,2};
        System.out.println(mostXORs0(arr));
    }
}
