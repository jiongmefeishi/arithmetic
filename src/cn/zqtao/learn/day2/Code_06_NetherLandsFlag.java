package cn.zqtao.learn.day2;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 荷兰国旗问题
 * 给定一个数组arr，和一个数num，
 * 请把小于num的数放在数组的 左边，
 * 等于num的数放在数组的中间，
 * 大于num的数放在数组的 右边。
 *
 *
 * 思路
 *
 * 类似快排
 * less 指针指向小于 num 区域的最后一个数
 * more 指针指向大于 num 区域的第一个数
 * cur 指针指向当前数
 *
 * 如 ： 8 2 4 5 6 1 5   num = 5
 * 初始状态，小于区域没有元素， 大于区域也没有元素
 * less = -1
 * more = arr.length
 * cur = 0  --- > 8 位置
 *
 * arr[cur] 等于num 时 -->  不需要其他操作，只需要当前指针前移即可  cur++
 * arr[cur] 小于num 时 -->  交换 小于区域右边的一个数和 cur 指向的数，并且less++ , cur++
 * arr[cur] 大于num 时 -->  交换 大于区域左边的一个数和 cur 指向的数, 并且more--
 *
 *
 * 常规问题：将问题迁移到常规方面，即对任意数组的任意区域进行荷兰国旗，
 * 即数组 arr 的 L ~  R 区域进行荷兰国旗
 *
 * less = L -1
 * more = R + 1
 * cur = L
 * 算法过程和上述一致
 *
 * @version: 1.0
 */
public class Code_06_NetherLandsFlag {

    public static int[] netherLandsFlag(int[] arr, int num) {
        return partition(arr, 0, arr.length - 1, num);
    }

    /**
     * 任意一个数组的L，到R 区间都可以实现荷兰国旗
     * v2: 精简代码
     */
    public static int[] partition2(int[] arr, int L, int R, int num) {

        int less = L - 1; // 小于区域初始指针位置
        int more = R + 1; // 大于区域初始指针位置
        int cur = L;

        while (cur < more) {
            if (arr[cur] == num)
                cur++;
            else if (arr[cur] < num)
                swap(arr, ++less, cur++);
            else if (arr[cur] > num)
                swap(arr, cur, --more);
        }
//        return arr;
        // 返回等于区域的开始和结束位置
        return new int[]{less + 1, more - 1};
    }

    /**
     * 任意一个数组的L，到R 区间都可以实现荷兰国旗
     */
    public static int[] partition(int[] arr, int L, int R, int num) {

        int less = L - 1; // 小于区域初始指针位置
        int more = R + 1; // 大于区域初始指针位置
        int cur = L;

        while (cur < more) {
            if (arr[cur] == num) cur++;
            else if (arr[cur] < num) {
                swap(arr, less + 1, cur);
                less++;
                cur++;
            } else if (arr[cur] > num) {
                swap(arr, cur, more - 1);
                more--;
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 5, 7, 9, 1, 19, 4, 32, 7, 7, 7};
        int[] landsFlag = netherLandsFlag(arr, 7);
        System.out.println(Arrays.toString(landsFlag));
        // [5, 2, 5, 1, 4, 7, 7, 7, 7, 32, 19, 9]
    }
}
