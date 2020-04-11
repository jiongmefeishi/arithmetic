package cn.zqtao.offer.meituan;

/**
 * @auther: zqtao
 * @description: 二分查找第一个大于 k 的数
 * @version: 1.0
 */
public class BinaryFindFirstMoreNum {

    int binaryFindFirstMoreNum(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] > k)
                right = middle;  //保留大于n的下标以防这是第一个
            else if (arr[middle] <= k)  //将小于改为小于等于
                left = middle + 1;
        }
        if (arr[left] > k)
            return left;
        else
            return -1;
    }
}
