package cn.zqtao.learn.nowcode_other.day9;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: topK问题
 * 如果N个元素，时间复杂度可以达到O(NlogK)
 * @version: 1.0
 */
public class Code_30_TopKTimes_1 {

    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class StrTimesComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    /**
     * 给定String类型数组arr， 给定整数 topK ,请严格按照排名顺序打印出现次数 前 topK名的字符串
     * <p>
     * 1、哈希表统计每一个Str出现的次数，只需要遍历一遍数组，时间复杂度 O(N),因为哈希表存取都是O(1)
     * 2、小根堆存 topK 个，依次让哈希表中统计的Str进入
     * 小根堆更新规则，堆顶次数小于新元素次数，弹出，替换；大于，不更新。
     */
    public static void printStrTopKTimes(String[] arr, int topK) {
        if (arr == null || topK < 1) {
            return;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String cur = arr[i];
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }

        Node[] minHeap = new Node[topK]; // 数组模拟小根堆
        int index = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int times = entry.getValue();

            Node node = new Node(str, times);
            if (index != topK) { // 堆未满
                minHeap[index] = node;
                heapInsert(minHeap, index++);
            } else { // 堆已满
                // 堆已满，比较堆顶元素次数和新来元素次数
                if (minHeap[0].times < node.times) {
                    minHeap[0] = node;
                    heapify(minHeap, 0, topK);
                }
            }
        }

        // 将小根堆排序，结果是大到小
        int heapSize = minHeap.length;
        swap(minHeap, 0, --heapSize);
        while (heapSize > 0) {
            heapify(minHeap, 0, heapSize);
            swap(minHeap, 0, --heapSize);
        }

        for (int i = 0; i < minHeap.length; i++) {
            System.out.println(minHeap[i].str + " : " + minHeap[i].times);
        }

    }

    /**
     * 调整更新元素后的小根堆
     *
     * @param heap     小根堆
     * @param index    更新位置
     * @param heapSize 堆容量
     */
    public static void heapify(Node[] heap, int index, int heapSize) {
        // 对于数组模拟堆结构，任意一个节点的左右子节点的位置都可以通过下标变换得到
        int left = 2 * index + 1;
        int right = left + 1; // 等同 2*index + 2
        int smallest = index; // 标记最小

        while (left < heapSize) {
            if (heap[left].times < heap[index].times) {
                smallest = left;
            }

            if (right < heapSize && heap[right].times < heap[smallest].times) {
                smallest = right;
            }

            if (smallest != index) {
                swap(heap, smallest, index);
            } else {
                break;// 自己就是最小，不用调整
            }
            index = smallest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public static void heapInsert(Node[] heap, int index) {
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[parent].times > heap[index].times) { // 新增元素的次数小于父节点次数
                swap(heap, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void swap(Node[] heap, int index1, int index2) {
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    public static void main(String[] args) {
        String[] str = {"0","6","4","8","4","9","1","5","5","5","0","7","10","4","1","1","9","0","0","9","8","5","4","8","10","0","3","9","9","3","8","2","8","9","5","5","3","3","3","1","4","4","1","2","6","2","5","10","1","8"};
        printStrTopKTimes(str, 3);
    }
}
