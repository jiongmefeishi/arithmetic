package cn.zqtao.code.greed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @auther: zqtao
 * @description: 一般背包问题
 * 有一个背包，最多放M kg的物体（物体大小不限）；
 *   有n个物体，每个物体的重量为Wi，每个物体完全放入背包后可获得收益Pi。问：如何放置能获得最大的收益？
 *
 *
 * 注：背包问题分为两种，若每个物体不可分割，则称为0/1背包问题，这种问题无法用贪心法求的最优解，
 * 只能求的近似解。而若每个物体可以切分，则称为一般背包问题，可以使用贪心法求的最优解。下面讨论的就是一般背包问题。
 *
 * 结果集
 *
 * 一般背包问题中，结果集可以用一个n元组表示：
 * 1. x的下标i表示物体的序号；
 * 2. xi表示第i个物体加入背包的部分（0<=xi<=1）
 *
 *
 * 目标函数
 * 使用贪心法解决最优化问题的第一步，就是要从题目中抽象出目标函数，这是一个数学建模的过程。
 * 本题中，目标函数就是当前背包收益的最大值： max += p[i] * xi
 *
 * 约束条件
 * 所选的物体放入背包后，不能超过背包载重M：sum { w[i] * xi} <= M
 *
 * 最优量度准则1：重量小的物体优先
 *
 *
 *   将所有物体按照重量递增的顺序排序，每次选重量最小的放入背包。
 *
 *
 * 这个最优量度标准显然无法得到整体最优解，因为重量小的物体并不一定价值高。
 *      最优解与价值、重量这两个维度产生关系，而这个最优量度标准仅考虑了一个维度，因此这样选择并不能导致整体最优解。
 *
 *
 *
 * 最优量度准则2：价值高的物体优先
 *
 *      这种选法也无法达到整体最优解，理由同上。
 *
 *
 *
 * 最优量度准则3：性价比高的物体优先
 *
 *
 *   首先计算所有物体的性价比（重量和收益的比值），每次优先将性价比高的物体放入背包。
 * @version: 1.0
 */
public class CommonPackage {

    public static void main(String[] args) {
        int[] ws = {10, 30, 400, 20};
        int[] ps = {20, 200, 50, 20};
        int m = 300;
        List<Obj> objs = new CommonPackage().commonPackage(ws, ps, m);
        System.out.println(objs.size());
        for (Obj obj : objs)
            System.out.println(obj);
    }

    /**
     * @param ws 物体重量数组
     * @param ps 物体价值数组
     * @param m 背包的载重
     * @return 最有结果集
     */
    public List<Obj> commonPackage(int[] ws, int[]ps, int m) {

        // 初始化物体
        List<Obj> objs = initObj(ws, ps);

        // 按照性价比进行排序比较(高到低)
        Collections.sort(objs, new Comparator<Obj>() {
            @Override
            public int compare(Obj o1, Obj o2) {
                return o2.p / o2.w - o1.p / o1.w ;
            }
        });

        // 结果集
        List<Obj> res = new ArrayList<>();
        // 背包剩余载重
        int residue = m;

        // n 步寻找最优局部解
        for (int i = 0; i < objs.size(); i++) {
            if (residue < objs.get(i).w)
                break;
            // 当前局部最优解
            Obj curObj = objs.get(i);
            res.add(curObj);
            residue -= curObj.w;
        }

        return res;
    }

    /**
     * 关联物体的重量和价值
     */
    List<Obj> initObj(int[] ws, int[]ps){
        List<Obj> objs = new ArrayList<>();
        for (int i = 0; i < ws.length; i++) {
            objs.add(new Obj(i, ws[i], ps[i]));
        }

        return objs;
    }

}
class Obj{
    int id; // 物体序号
    int w; // 物体的重量
    int p; // 物体的价值

    public Obj(int id, int w, int p) {
        this.id = id;
        this.w = w;
        this.p = p;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "id=" + id +
                ", w=" + w +
                ", p=" + p +
                '}';
    }
}
