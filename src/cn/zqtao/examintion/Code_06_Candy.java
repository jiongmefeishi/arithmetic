package cn.zqtao.examintion;

/**
 * @auther: zqtao
 * @description: 分糖果问题
 * @version: 1.0
 */
public class Code_06_Candy {

    public static int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex1(arr, 0); // 上来是否就下坡
        int res = rightCands(arr, 0, index++); // 上来就是下坡结果

        int lbase = 1; // 左坡度：爬坡层级。也可以理解为标记爬坡过程到第 i 位置时应该分配给第 i 个孩子的糖果数量
        /*
        举例  9 1 2 3
        下标  0 1 2 3
        0 位置 lbase = 1
        1 位置 lbase = 1
        2 位置 lbase = 2
        3 位置 lbase = 3
         */
        int next = 0;
        int rcands = 0; // 每次下坡过程取得的糖果数量
        int rbase = 0; // 下坡坡度
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase; // 爬坡
                index++;
            } else if (arr[index] < arr[index - 1]) { // 下坡
                next = nextMinIndex1(arr, index - 1); // 找到下坡的终止点如 4 3 2 1 7  ---> 找到 1 下标
                rcands = rightCands(arr, index - 1, next++); // 计算这一段下坡分的糖果数量
                rbase = next - index + 1; // 计算右坡度：下坡层级
                // res+= 下坡总数量 - 当前位置重复计算的数量
                // i 位置的正确数量等于 坡度较大的那一个
                /*
                 1 2 3 4 2 1
                 爬坡计算 1 2 3 4
                 下坡计算 3 2 1
                 对于 4 也就是 3 号位置的计算取值，爬坡坡度为 4 ，下坡坡度
                 */
                res += rcands + (rbase > lbase ? -lbase : -rbase);
                lbase = 1; // 重置爬坡需要的 基础坡度
                index = next; // 下一次爬坡位置
            } else { // 等于状态
                res += 1;
                lbase = 1;
                index++;
            }
        }
        return res;
    }

    public static int nextMinIndex1(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int rightCands(int[] arr, int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

    public static int candy2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex2(arr, 0);
        int[] data = rightCandsAndBase(arr, 0, index++);
        int res = data[0];
        int lbase = 1;
        int same = 1;
        int next = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                same = 1;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex2(arr, index - 1);
                data = rightCandsAndBase(arr, index - 1, next++);
                if (data[1] <= lbase) {
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same;
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                res += lbase;
                same++;
                index++;
            }
        }
        return res;
    }

    public static int nextMinIndex2(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int[] rightCandsAndBase(int[] arr, int left, int right) {
        int base = 1;
        int cands = 1;
        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                cands += base;
            } else {
                cands += ++base;
            }
        }
        return new int[] { cands, base };
    }

    public static void main(String[] args) {
        int[] test1 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy1(test1));

        int[] test2 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy2(test2));
    }

}
