package cn.zqtao.learn.nowcode_other.day10;

import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 最短回文长度
 * @version: 1.0
 */
public class Code_38_ShortestMakePalindrome {

    /**
     * 思路：必须包含最后一个字符的情况下，求最长的回文子串，其中，不是回文串的范围字符，逆序填在后面即可
     * 如 abc1234321
     * 必须包含最后一个字符 4 的情况下，最长的回文子串是 1234321
     * 将不包含在最长回文子串内的其他字符abc逆序cba --> 添加在后面  abc1234321cba
     *
     * 算法被分解为，怎么求一个字符串的最长回文子串，子串要求连续
     * 1、暴力解
     * 以每一个字符向两边进行扩散
     * 121
     *  null<-1->2     ---->0
     *  1<-2->1        ---->3
     *  2<-1->1        ---->0
     *  所以扩散的最长回文长度就是 3
     *  但是问题来了，对于这种对称扩散寻找，121 奇数个，是以实轴进行的扩散，
     *  然而 1221 偶数个，是虚轴扩散才能找到真正的值4，不然就是 0
     *
     *  处理方式：不管是奇数个还是偶数个，都进行扩容处理，求解完毕后除以 2 得到的就是解
     *  121 --> #1#2#1#  最长对称是 7  7/2=3
     *  1221--> #1#2#2#1# 最长对称是 9  9/2=4
     *
     *  注意：添加的字符是 任意的，可以是存在的字符，如 2，不影响相对的结果
     *  时间复杂度 O(N^2)
     *
     *
     * 2、Manacher 算法
     *
     * 1）、回文半径：以每个位置的字符为回文中心求出的回文半径长度；
     *      维护一个回文半径数组，记录每一个位置可以扩散的回文半径
     * 2）、回文最右边界：这个位置及之前的位置的回文子串，所到达的最右边的地方，同时记录最右回文中心；
     *      如果有两个位置扩散到同一个右边界，只记录最早的那个。
     *
     *      如  # 1 # 2 # 2 # 1 #
     *          0 1 2 3 4 5 6 7 8
     *          4号位置# 的回文最右边界达到了8号位置
     *          7号位置1 的回文最右边界达到了8号位置
     *          最右回文中心只记录 4 号位置，不记录 7 号位置
     *
     *算法出现的几种情况
     * a、当前所求的位置，不在左右边界里，此时和暴力方法一样，向两边依次检查
     *      如  # 1 # 2 # 2 # 1 #
     *          0 1 2 3 4 5 6 7 8
     *          0号位置，不在边界，扩
     *          1号位置，不在边界，向两边依次检查，扩到了2 号位置，此时更新左右边界为 2
     * b、在最右回文右边界里面
     * c、在最右回文右边界外
     * d、压线
     *
     * 其中一点就是找到回文的最右边界就停, 同时记录这个最大的回文半径
     *
     */

    // 预处理字符串，排除奇偶个字符的影响
    public static char[] getPreprocessedStr(String str){
        char[] tmp = new char[str.length() * 2 + 1];
        int j = 0;
        for (int i = 0; i < tmp.length; i++) {
            if ((i & 1) == 0) {
                tmp[i] = '#';
            } else {
                tmp[i] = str.charAt(j);
            }
            tmp[i] = (i & 1) == 0 ? '#' : str.charAt(j++);
        }
        return tmp;
    }

    public static int manacherStr(String str) {
        if (str == null || str.length() == 0) return 0;

        char[] preArr = getPreprocessedStr(str);// 预处理字符串，排除奇偶影响

        int index = -1; // 最右回文对称中心
        int maxR = -1; // 最右回文延伸边界
        int max = -1; // 最大延伸长度
        int[] radius = new int[preArr.length]; // 记录每个字符可以延伸的最大回文边界

        for (int i = 0; i < preArr.length; i++) {
            // 找到当前点关于最右回文对称中心的对称点位置
//            int symmetryNode = radius[2 * index - i];
            // 当前点的状态，1、在最右回文边界之外，暂时假设只有自己是回文字符串，记录为1
            // 2、在最右回文边界之内，记录为对称点的回文边界和最右边界-i 中最小的
            radius[i] = maxR > i ? Math.min(radius[2 * index - i], maxR - i) : 1;

            // 检查并更新当前下标为中心的回文串最远延伸的长度
            while (i + radius[i] < preArr.length // 当前位置扩出的最大右边界不能超出数组范围
                && (i - radius[i] + 1) > 0 // 当前位置存在左边点 ：i-radius[i] <-i-> i+radius[i], 可以继续向两边扩
            ) {
                // 继续向外扩充，寻找最大的回文半径
                if (preArr[i + radius[i]] == preArr[i - radius[i]]){
                    radius[i]++;
                } else {
                    break;
                }
            }

            if (i + radius[i] > maxR) {
                maxR = i + radius[i]; // 更新最大回文右边界
                index = i; // 更新最大回文右边界
            }

            if (maxR == preArr.length) { // 最大回文右边界已经扩到了最后一个字符
                max = radius[i]; // 记录最大回文半径
                break;
            }

            System.out.println(Arrays.toString(radius));
        }

        System.out.println("max: " + max);
        return str.length() * 2 - max + 1;
    }

    public static void main(String[] args) {
        System.out.println(manacherStr("abb"));
    }
}
