package cn.zqtao.leetcode.day2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zqtao
 * @description: 每日一题：拥有最多糖果的孩子
 * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 * @Date: 2020/6/23
 */
public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        /*
            思路：
            1.获取数组最大值，得到目前做多糖果数量 max
            2.遍历数组，将额外糖果 extraCandies 分配给每一个孩子，比较是否大于等于 max
            3.是，当前孩子可以成为拥有最多糖果的孩子
            4.否，当前孩子不可以成为拥有最多糖果的孩子
         */

        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }

        List<Boolean> maxChild = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            if ((candies[i] + extraCandies) >= max) {
                maxChild.add(true);
            } else {
                maxChild.add(false);
            }
        }
        return maxChild;
    }
}
