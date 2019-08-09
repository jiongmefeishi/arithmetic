package cn.zqtao.code.tmp;

import java.util.*;

/**
 * @description: 教室安排
 */
public class Code_01_RoomArrange {

    // 教室安排数据结构
    public static class RoomProgram {
        public int startTime;
        public int endTime;
        public int index; // 社团编号

        public RoomProgram(int startTime, int endTime, int index) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.index = index;
        }
    }

    public static class RoomProgramComparator implements Comparator<RoomProgram> {

        @Override
        public int compare(RoomProgram o1, RoomProgram o2) {
            return o1.endTime - o2.endTime;
        }
    }

    public static PriorityQueue<Integer> bestArrange(ArrayList<RoomProgram> programs, int curTime) {
        if (programs == null || programs.size() == 0) return null;

        Collections.sort(programs, new RoomProgramComparator());

        PriorityQueue<Integer> res = new PriorityQueue<>();
        for (RoomProgram program : programs) {
            if (curTime <= program.startTime) {
                res.add(program.index);
                curTime = program.endTime;
            }
        }
        return res;
    }

   /* public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<RoomProgram> programs = null;
        while (scanner.hasNext()) {
            programs = new ArrayList<>();
            int n = scanner.nextInt();

            for (int i = 1; i <= n; i++) {
                programs.add(new RoomProgram(scanner.nextInt(), scanner.nextInt(), i));
            }
            PriorityQueue<Integer> priorityQueue = bestArrange(programs, 1);
        }
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            ArrayList<RoomProgram> input = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                input.add(new RoomProgram(sc.nextInt(), sc.nextInt(), i + 1));
            }
            // 按照start排序
            Collections.sort(input, new Comparator<RoomProgram>() {
                public int compare(RoomProgram m1, RoomProgram m2) {
                    if (m1.startTime == m2.startTime) {
                        return m1.endTime - m2.endTime;
                    } else {
                        return m1.startTime - m2.startTime;
                    }
                }
            });            // 是否可以删除
            if (dfs(input, 0, 0)) {
                if (result.size() == 0) {// 没有重叠					S
                    System.out.println(n);
                    for (int i = 0; i < n; i++) {
                        System.out.print(i + 1 + " ");
                    }
                } else {// 有一个重叠
                    System.out.println(result.size());
                    for (RoomProgram i : result) {
                        System.out.print(i.index + " ");
                    }
                }
            } else {// 不可以输出0
                System.out.println(0);
            }
        }
    }

    static List<RoomProgram> result = new ArrayList<>();

    public static boolean dfs(ArrayList<RoomProgram> list, int count, int current) {
        if (current == list.size()) {
            return true;
        }
        RoomProgram interval = list.get(current);
        int start = interval.startTime;
        int end = interval.endTime;
        for (int i = current + 1; i < list.size(); i++) {
            interval = list.get(i);
            start = interval.startTime;
            if (end > start) {
                if (count == 1) {// 两对重叠，不可以，返回false
                    return false;
                }
                list.remove(i);// 删除重复的第一个
                boolean flag1 = false;
                boolean flag2 = false;
                if (i - 2 >= 0) {
                    flag1 = dfs(list, count + 1, i - 2);
                } else {
                    flag1 = dfs(list, count + 1, 0);
                }
                if (flag1) {
                    result.add(interval);
                }                // 恢复删除
                if (i < list.size()) {// 判断是否结尾
                    list.set(i, interval);
                } else {
                    list.add(interval);
                }
                flag2 = dfs(list, count + 1, i - 1);// 删除重复的第一个
                if (flag2) {
                    result.add(list.get(i + 1));
                }
                return flag1 || flag2;
            }
            end = interval.endTime;
        }
        return true;
    }
}
