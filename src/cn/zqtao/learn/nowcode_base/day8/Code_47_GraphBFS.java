package cn.zqtao.learn.nowcode_base.day8;

import cn.zqtao.learn.nowcode_base.day8.graph.Graph;
import cn.zqtao.learn.nowcode_base.day8.graph.GraphGenerator;
import cn.zqtao.learn.nowcode_base.day8.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther: zqtao
 * @description: 宽度优先遍历
 * @version: 1.0
 */
public class Code_47_GraphBFS {
    public static void bfs(Node node) {
        if (node == null) return;

        Queue<Node> queue = new LinkedList<>();
        // 表示一个点进没进队列,set 是队列的一个注册 ，进过队列的用set保留下来
        HashSet<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);

            for(Node next: cur.nexts) {
                if (!set.contains(next)){ // 没有加入到队列的
                    // 加入并且注册
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        // {from, to, weight}
        Integer[][] matrix = {
                {1, 2, 3},
                {1, 3, 4},
                {1, 4, 2},
                {2, 7, 1},
                {3, 5, 5},
                {4, 6, 1}
        };

        Graph graph = GraphGenerator.createGraph(matrix);
        bfs(graph.nodes.get(1));
        // 1 2 3 4 7 5 6
    }
}
