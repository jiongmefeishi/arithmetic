package cn.zqtao.learn.nowcode_other.day9;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: topK问题
 * 进阶
 * 设计并实现TopKRecord 结构，可以不断的向其中加入字符串
 * 并且可以根据字符串出现的情况随时打印加入次数最多的前 K 个字符串，
 * 具体为：
 * 1、K在TopKRecord 实力生成时指定，并且不再变化（K是构造参数）
 * 2、含有add(String str) 方法，含有printTopK(),打印加入次数最多的前 K 个字符串
 * 不需要严格按照排名顺序打印
 * <p>
 * 【要求】
 * 1、在任何时刻，add方法的时间复杂度不超过 O(logK)
 * 2、在任何时刻，printTopK方法的时间复杂度不超过 O(K)
 */
public class Code_31_TopKTimes_2 {

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    /**
     * TopKRecord 结构
     * 维护三个结构
     * 小根堆：存储 K 个目的元素
     * 词频统计map：维护元素的词频统计
     * 堆位置map：维护元素在heap 上的状态
     */
    public static class TopKRecord {
        private Node[] heap;
        private int size; // heap size
        // 词频统计 str 对应的 node 可以看做str对应node.times
        private HashMap<String, Node> strCountNodeMap;
        // node 对应在heap 上的位置，是否在上面 -1 不在，其他代表在heap 上所处的位置
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int size) {
            heap = new Node[size]; // topK 固定
            this.size = 0;
            strCountNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null; // 维护新进的元素
            int preIndex = -1; // 维护新进的元素在heap 中的位置

            if (!strCountNodeMap.containsKey(str)) {// 如果没有这个str
                curNode = new Node(str, 1);// 临时新建记录
                strCountNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);// 两个map 同时新增，默认暂时不在堆上
            } else {// 已经存在，更新
                curNode = strCountNodeMap.get(str); // 得到代表str的node节点
                curNode.times++;// 次数++
                preIndex = nodeIndexMap.get(curNode);// 同时获得在heap上的位置状态
            }

            // heap 结构的更新
            if (preIndex == -1) {
                if (size == heap.length) { // 堆满
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, size);
                    }
                } else {
                    nodeIndexMap.put(curNode, size);
                    heap[size] = curNode;
                    heapInsert(size++);
                }
            } else {
                heapify(preIndex, size);
            }
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopKRecord record = new TopKRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();

    }
}