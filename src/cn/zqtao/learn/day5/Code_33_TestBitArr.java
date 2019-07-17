package cn.zqtao.learn.day5;

/**
 * @auther: zqtao
 * @description: 测试比特数组
 * 怎么做出长度为0~m-1的比特类型的数组？？？
 *
 * 假设一个数组（0，m-1），每一个位置上不是整数、字符串，这个数组的每一个位置上，
 * 它是比特，即每一个位置上只有两种状态0和1
 *
 * int[]  int 类型4个字节，32个比特
 * 一个整数可以表示32个比特
 * 如果声明一个长度1000的数组，它其实可以表示1000 * 32 个比特位
 * @version: 1.0
 */
public class Code_33_TestBitArr {
    public static void main(String[] args) {
        int[] arr = new int[1000]; // 可以表示32000个比特位，每一个arr[i] 表示一个大桶,一个大桶表示有32个比特位

        int index = 30000; // 想要把30000位描黑

        int intIndex = index / 32; // 计算30000在哪个大桶;intIndex =

        int bitIndex = index % 32; // 计算30000具体在某个大桶里面哪一个比特位

        System.out.println(intIndex); // 937
        System.out.println(bitIndex); //16
        // 30000比特位在937号桶的16号比特位，这个位置需要被描黑
        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));
        /*
        做比特类型的数组
        （拿基础类型拼，这里用int，32位，当然如果你想要省空间可以使用long类型，64位）
        如果还嫌空间大，可以使用矩阵
        long[][] map = new long[1000][1000]
        map 可以表示的比特位64 * 1000 * 1000
         */
    }
}
