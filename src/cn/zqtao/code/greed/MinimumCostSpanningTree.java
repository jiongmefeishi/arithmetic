package cn.zqtao.code.greed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 最小代价生成树  prim算法实现
 *
 * Prim算法
 * 贪心准则：将整个图分成两部分，一部分已选入生成树，另一部分在生成树之外。
 * 每次选的边要求一头在生成树之内，一头在生成树之外，并保证当前边是满足上述条件中最短的一条。重
 * 复上述操作，直到选出n-1条边为止。
 *
 *
 * 数据结构
 *
 * 图的邻接表示
 * 图用一个Map< String,List>表示，其中String表示节点的编号，List中存储以该节点为起点的所有边节点。
 *
 * mark：
 * Map<String,Boolean> mark = new HashMap<>();
 *      记录指定节点是否已经在生成树中。
 *      key 表示节点编号
 *      value boolean 型，表示是否已经选入到生成树中。
 *
 * nearest:
 * Map<String, String> nearest;
 *      用于记录最小代价生成树的那条路径
 *      key 表示指定节点的编号。
 *      value 表示在最小代价生成树中，该节点的前驱节点的编号
 *
 * lowcost:
 * Map<String, Integer> lowcost;
 *      用于记录制定节点为终点的最小权值
 *      key 表示指定节点的编号
 *      valeu 表示在最小代价生成树中，以该节点为终点的边的权值
 *
 * k节点：
 *      最新选入生成树的节点。
 *
 * @version: 1.0
 */
public class MinimumCostSpanningTree {

    public static void main(String[] args) {
        final Map<String, List<Enode>> graph = new MinimumCostSpanningTree().getGraph();
        new MinimumCostSpanningTree().prim(graph);

    }

    Map<String, List<Enode>> getGraph(){
//        int[] arr1 = {-1,23, -1, -1, -1, 28, 36};
//        int[] arr2 = {23, -1, 20, -1, -1, -1, 1};
//        int[] arr3 = {-1, 20, -1, 15, -1, -1, 4};
//        int[] arr4 = {-1, -1, 15, -1, 3, -1, 9};
//        int[] arr5 = {-1, -1, -1, 3, -1, 17, 16};
//        int[] arr6 = {28, -1, -1, -1, 17, -1, 25};
//        int[] arr7 = {36, 1, 4, 9, 16, 25, -1};

        int[][] arr = {{},
                {-1,-1, 23, -1, -1, -1, 28, 36},
                {-1,23, -1, 20, -1, -1, -1, 1},
                {-1,-1, 20, -1, 15, -1, -1, 4},
                {-1,-1, -1, 15, -1, 3, -1, 9},
                {-1,-1, -1, -1, 3, -1, 17, 16},
                {-1,28, -1, -1, -1, 17, -1, 25},
                {-1,36, 1, 4, 9, 16, 25, -1}};


        Map<String, List<Enode>> graph = new HashMap<>();


        for (int i = 1; i <= 7; i++) {
            List<Enode> es = new ArrayList<>();
            for (int j = 1; j <= 7; j++) {
                Enode e = new Enode();
                // -1
                e.id = j + "";
                if (arr[i][j] != -1){
                    e.weight = arr[i][j];
                }else {
                    e.weight = Integer.MAX_VALUE;
                }

                es.add(e);

            }
            graph.put(i + "", es);
        }

        return graph;
    }

    /**
     * prim算法
     * @param graph 表示图的邻接矩阵
     */
    void prim(Map<String, List<Enode>> graph){

        // 初始化
        Map<String, String> nearest = new HashMap<>();
        Map<String, Integer> lowcost = new HashMap<>();
        Map<String, Boolean> mark = new HashMap<>();

        // 最新选入生成树的节点
        String k = null;
        // 记录最后一个节点的id，用于从后向前输入结果
        String end = null;

        for (String id: graph.keySet()){
            nearest.put(id, null);
            lowcost.put(id, Integer.MAX_VALUE);
            mark.put(id, false);
//            k = id;
        }

        k = "1";
        // 第一个选入的节点
        mark.put(k, true);

        // 寻找生成树的 n - 1 条边
        for (int i = 1; i <= graph.size() - 1; i++) {

            // 更新与 k 相邻的 nearest
            List<Enode> edges = graph.get(k);
            for (Enode edge: edges){

                // 节点尚未被选中到最小生成树
                // 边节点权重小于 lowcost 中存储的权重值
                // 设j是节点k相邻节点，并且如果< k,j>这条边的权值小于lowcost[j]，则更新lowcost[j]=w< k,j>、nearest[j]=k。
                if (!mark.get(edge.id) && edge.weight < lowcost.get(edge.id)){
                    lowcost.put(edge.id, edge.weight);
                    nearest.put(edge.id, k);
                }
            }


            // 寻找当前lowcost 中最短的边，即寻找当前存储的最小权值
            int min = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> entry : lowcost.entrySet()){
                if (entry.getValue() < min){
                    min = entry.getValue();
                    // 变更当前选中加入最小生成树的节点
                    k = entry.getKey();
                }
            }

            // 标记最新加入到最小生成树的节点
            mark.put(k, true);
            end = k;
        }


        // 打印结果
        for (int i = 0; i < graph.size(); i++) {
            System.out.println(nearest.get(end) + "-" + end + "权重: " + lowcost.get(end));
            end = nearest.get(end);
        }

    }

}

// 边节点（由一条边和一个终止节点组成）
class Enode {
    String id;// 终止节点的编号
    int weight;// 边权重
}


// 总结：结果错误，在节点7 --> 2 时出错，指向错误，主要原因测试数据出错。