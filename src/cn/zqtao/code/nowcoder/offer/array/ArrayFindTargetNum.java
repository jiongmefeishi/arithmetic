package cn.zqtao.code.nowcoder.offer.array;

/**
 * @auther: zqtao
 * @description: 二维数组中查找目标值
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * <p>
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class ArrayFindTargetNum {

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};

        System.out.println(Find(6, arr));
//        System.out.println(Find(13, arr));
    }

    public static boolean Find(int target, int[][] array) {
        if (array.length == 0) return false;

        // 双指针, 从右上角开始寻找
        int i = 0; // 行
        int j = array[0].length - 1; // 列

        while (i < array.length && j >= 0) {
            if (target > array[i][j]) {
                i++;
            } else if (target < array[i][j]) {
                j--;
            } else {
                return true;
            }
        }

        return false;
    }
}
