package cn.zqtao.learn.day0_extra;

/**
 * @auther: zqtao
 * @description: 在数组中找到一个局部最小的位置
 * @version: 1.0
 */
public class Code_Extra_02_FindOneLessValueIndex {

    public static int findOneLessValueIndex(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]){ // 只有1个元素，或者是第1个元素小于第2个元素
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1; // 最后一个元素是局部最小
        }

        // 中间位置是局部最小处理，二分法
        int L = 1;
        int R = arr.length - 2;

        int mid = 0;
        while (L < R){
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]){
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]){
                L = mid + 1;
            } else {
                return mid; // 小于左边，同时小于右边
            }
        }

        return L;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,6,3};
        System.out.println("局部最小：" + arr[findOneLessValueIndex(arr)]); // 1

        arr = new int[]{8,2,5,6,3};
        System.out.println("局部最小：" + arr[findOneLessValueIndex(arr)]); // 3

        arr = new int[]{8,2,5,6,7};
        System.out.println("局部最小：" + arr[findOneLessValueIndex(arr)]); // 2
    }
}
