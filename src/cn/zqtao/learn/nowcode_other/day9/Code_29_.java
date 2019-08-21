package cn.zqtao.learn.nowcode_other.day9;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_29_ {

    public static class Dot{
        public int w;
        public int h;

        public Dot(int w, int h) {
            this.w = w;
            this.h = h;
        }

        @Override
        public String toString() {
            return "Dot{" +
                    "w=" + w +
                    ", h=" + h +
                    '}';
        }
    }

    public static class DotComparator implements Comparator<Dot> {
        @Override
        public int compare(Dot o1, Dot o2) {
            if (o1.w != o2.w){
                return o1.w - o2.w;
            } else {
                return o2.h - o1.h;
            }
        }
    }

    public static int maxEnvelopes(int[][] es) {
        if (es == null || es.length == 0 || es[0] == null || es[0].length != 2) {
            return 0;
        }
        Dot[] dots = new Dot[es.length];
        for (int i = 0; i < es.length; i++) {
            dots[i] = new Dot(es[i][0], es[i][1]);
        }

        Arrays.sort(dots, new DotComparator());
        System.out.println(Arrays.toString(dots));

        int[] ends = new int[es.length]; // ends[i] 含义：长度为 i+1 的所有递增子序列的最小结尾
        int L = 0;
        int R = 0;
        int mid = 0;
        int right = 0; // ends 边界

        for (int i = 1; i < dots.length; i++) {
            L = 0;
            R = right;
            while (L <= R) { // 二分法求取第一个大于等于 dots[i] 大的数
                mid = L + (R - L) / 2;
                if (ends[mid] < dots[L].h){
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }

                right = Math.max(right, L);
                ends[L] = dots[i].h;
            }
        }
        return right + 1;
    }

    public static void main(String[] args) {
        int[][] test = { { 4, 3 }, { 1, 2 }, { 5, 7 }, { 5, 3 }, { 1, 1 }, { 4, 9 } };
        int[][] test2 = {
                {2,3},
                {1,4},
                {1,2},
                {2,6},
                {1,3}
        };
//        System.out.println(maxEnvelopes(test));
        System.out.println(maxEnvelopes(test2));
    }
}
