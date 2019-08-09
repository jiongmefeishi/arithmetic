package cn.zqtao.code.tmp;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description:
 *
 * @version: 1.0
 */
public class Code_02_RepairRoad {

    // 想要畅通所有的村庄，那么除了 最低海拔的高度外，其他的都用上了
    // 花费 = 总海拔-最低海拔
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // todo
            System.out.println(repair(arr));
        }
    }

    public static int repair(int[] arr) {

        if (arr == null || arr.length == 0) return 0;

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }

        return sum - min;
    }

}
