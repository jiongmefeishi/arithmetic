package cn.zqtao.learn.nowcode_base.day6;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @auther: zqtao
 * @description: 会议时间安排
 *
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目 的宣讲。
 *
 * 给你每一个项目开始的时间和结束的时间(给你一个数 组，里面 是一个个具体的项目)，
 * 你来安排宣讲的日程，要求会 议室进行 的宣讲的场次最多。
 * 返回这个最多的宣讲场次。
 *
 * 贪心策略：每次选取结束时间最早的
 * @version: 1.0
 */
public class Code_40_TX_BestArrange {

    // 构建会议数据结构
    public static class Program{
        public int startTime;
        public int endTime;

        public Program(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    // 会议比较器，按照项目结束时间进行排序，结束时间早放前
    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.endTime - o2.endTime;
        }
    }

    /**
     * @param programs 具体的会议信息
     * @param curTime 当前时间
     * @return 返回这个最多的宣讲场次
     */
    public static int bestArrange(Program[] programs, int curTime){
        if (programs == null || programs.length == 0) return 0;

        Arrays.sort(programs, new ProgramComparator()); // 按照结束时间排序

        int res = 0;
        for (int i = 0; i < programs.length; i++) {
            if (curTime <= programs[i].startTime){ // 可以进行的会议，其他都排除
                res++;
                curTime = programs[i].endTime; // 重置当前时间为会议结束时间
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Program[] programs = new Program[9];
        int[] startTime = {7, 7, 8, 9, 10, 10, 12, 14, 16};
        int[] endTime = {9, 8, 11, 10, 12, 13, 15, 19, 17};

        for (int i = 0; i < startTime.length; i++) {
            programs[i] = new Program(startTime[i], endTime[i]);
        }

        int res = bestArrange(programs, 6);
        System.out.println(res);
    }
}
