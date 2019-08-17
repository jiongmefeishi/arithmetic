package cn.zqtao.learn.nowcode_other.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @auther: zqtao
 * @description: PM and Idea
 * SED: Software Develop Engineer
 * PM: Project Manager
 * @version: 1.0
 */
public class Code_27_SDEandPM {

    /**
     * 项目的数据结构
     * 封装项目的基本属性
     */
    public static class Program {
        public int index; // 项目编号
        public int pmId; // 归属于哪个 pm, pmId: Project Manager的ID
        public int startTime; // 项目可以开始做的时间，start之前的时间段不能做
        public int rank; // 项目的优先级
        public int costTime; // 项目花费时间

        public Program(int index, int pmId, int beginTime, int rank, int costTime) {
            this.index = index;
            this.pmId = pmId;
            this.startTime = beginTime;
            this.rank = rank;
            this.costTime = costTime;
        }
    }


    /**
     * 比较器
     * pm 拥有众多项目，按照比较器规则排序最喜欢的项目
     * <p>
     * 规则：
     * 1、优先级高
     * 2、花费时间短
     * 3、最先想出的
     */
    public static class PmLoveRule implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            if (o1.rank != o2.rank) {
                return o1.rank - o2.rank;
            } else if (o1.costTime != o2.costTime) {
                return o1.costTime - o2.costTime;
            } else {
                return o1.startTime - o2.startTime;
            }
        }
    }


    /**
     * 大结构
     * 1、对于激活状态的项目program（startTime 在 程序员苏醒范围内），进入大结构
     * <p>
     * 2、对于这个program
     * 先经过pm 喜欢的规则进行排序，选出 PM 最想做的项目
     * 然后经过SDE 喜欢的规则进行排序，最后弹出是程序员最想做的项目
     * <p>
     * 3、这个大结构唯一暴露出去的只有两个接口，一个是add()  一个是pop()
     */
    public static class BigQueues {
        private List<PriorityQueue<Program>> pmQueues;// PM 组成的队列集合，每一个PM独立为一个堆
        private Program[] heap; // 存放每一个PM最想做的项目，排序规则按照SdeLoveRule, 选出程序员最想做的项目
        private int[] indexes;
        private int heapSize; // 大结构里项目个数

        public BigQueues(int size) { // size是PM 的个数
            this.heapSize = 0;
            this.heap = new Program[size]; // 每个PM都贡献一个自己最想要做的项目
            this.indexes = new int[size + 1];
            for (int i = 0; i <= size; i++) {
                indexes[i] = -1;
            }

            pmQueues = new ArrayList<>();
            for (int i = 0; i <= size; i++) { // size 个PM，每一个都独立一个堆
                pmQueues.add(new PriorityQueue<>(new PmLoveRule()));
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }


        // 新增一个项目进大结构
        public void add(Program program) {
            PriorityQueue<Program> queue = pmQueues.get(program.pmId);
            queue.add(program);

            // 大结构中新增元素后，heap可能会发生变化
            Program head = queue.peek();
            int heapindex = indexes[head.pmId];
            if (heapindex == -1) {
                heap[heapSize] = head;
                indexes[head.pmId] = heapSize;
                heapInsert(heapSize++);
            } else {
                heap[heapindex] = head;
                heapInsert(heapindex);
            }
        }


        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (sdeLoveRule(heap[parent], heap[index]) > 0) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }


        private void swap(int index1, int index2) {
            Program p1 = heap[index1];
            Program p2 = heap[index2];
            heap[index1] = p2;
            heap[index2] = p1;
            indexes[p1.pmId] = index2;
            indexes[p2.pmId] = index1;
        }


        // SDE 排序规则
        private static int sdeLoveRule(Program o1, Program o2) {
            if (o1.costTime != o2.costTime) {
                return o1.costTime - o2.costTime;
            } else {
                return o1.pmId - o2.pmId;
            }
        }


        public Program pop() {
            Program head = heap[0];
            PriorityQueue<Program> queue = pmQueues.get(head.pmId);
            queue.poll();
            if (queue.isEmpty()) {
                swap(0, heapSize - 1);
                heap[--heapSize] = null;
                indexes[head.pmId] = -1;
            } else {
                heap[0] = queue.peek();
            }
            heapify(0);
            return head;
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int best = index;
            while (left < heapSize) {
                if (sdeLoveRule(heap[left], heap[index]) < 0) {
                    best = left;
                }
                if (right < heapSize && sdeLoveRule(heap[right], heap[best]) < 0) {
                    best = right;
                }
                if (best == index) {
                    break;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
                right = index * 2 + 2;
            }
        }

    }

    public static class StartRule implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.startTime - o2.startTime;
        }

    }

    public static int[] workFinish(int pms, int sdes, int[][] programs) {
        PriorityQueue<Program> programsQueue = new PriorityQueue<>(new StartRule());
        for (int i = 0; i < programs.length; i++) {
            Program program = new Program(i, programs[i][0], programs[i][1], programs[i][2], programs[i][3]);
            programsQueue.add(program);
        }
        PriorityQueue<Integer> sdeWakeQueue = new PriorityQueue<>();
        for (int i = 0; i < sdes; i++) {
            sdeWakeQueue.add(1);
        }
        BigQueues bigQueues = new BigQueues(pms);
        int finish = 0;
        int[] ans = new int[programs.length];
        while (finish != ans.length) {
            int sdeWakeTime = sdeWakeQueue.poll();
            while (!programsQueue.isEmpty()) {
                if (programsQueue.peek().startTime > sdeWakeTime) {
                    break;
                }
                bigQueues.add(programsQueue.poll());
            }
            if (bigQueues.isEmpty()) {
                sdeWakeQueue.add(programsQueue.peek().startTime);
            } else {
                Program program = bigQueues.pop();
                ans[program.index] = sdeWakeTime + program.costTime;
                sdeWakeQueue.add(ans[program.index]);
                finish++;
            }
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int pms = 2;
        int sde = 2;
        int[][] programs = {{1, 1, 1, 2}, {1, 2, 1, 1}, {1, 3, 2, 2}, {2, 1, 1, 2}, {2, 3, 5, 5}};
        int[] ans = workFinish(pms, sde, programs);
        printArray(ans);
    }
}
