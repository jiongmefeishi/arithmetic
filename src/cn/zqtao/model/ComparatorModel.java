package cn.zqtao.model;

import java.util.Comparator;

/**
 * @auther: zqtao
 * @description: 比较器模型
 * @version: 1.0
 */
public class ComparatorModel {
    /**
     * 小根堆比较器
     */
    public static class MinHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2; // < 0  o1 < o2  负数
        }
    }

    /**
     * 大根堆比较器
     */
    public static class MaxheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; // <   o2 < o1
        }

    }

}
