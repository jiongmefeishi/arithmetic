package cn.zqtao.learn.nowcode_other.day12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 找到数组中出现次数大于 N/K 的数
 * @version: 1.0
 */
public class Code_48_FindKMajority {

    /**
     * 找到数组中出现次数大于一半的数
     * 思路：遍历一遍数组，每次都删除（理解上认为是删除）两个不同的数，因为存在次数大于一半
     * 那么最终剩余下来的就是这个数。
     */
    public static void findHalfMajority(int[] arr) {
        int pre = 0; // 候选数
        int times = 0; // 标记点数
        for (int i = 0; i < arr.length; i++) {
            if (times == 0) { // 表示
                pre = arr[i];
                times = 1;
            } else if (pre == arr[i]) {
                times++;
            } else {
                times--;// 模拟删除
            }
        }

        times = 0;
        // 检查pre 这个数是否超过了一半
        for (int i = 0; i < arr.length; i++) {
            if (pre == arr[i])
                times++;
        }

        if (times > arr.length / 2)
            System.out.println(pre);
        else
            System.out.println("no such number!");
    }

    public static void findKMajority(int[] arr, int k) {
        if (k < 2) {
            System.out.println("k is invalid");
            return;
        }

        // 确定 k-1 个候选数，以及点数
        HashMap<Integer, Integer> pres = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (pres.containsKey(arr[i])) {
                pres.put(arr[i], pres.get(arr[i]) + 1);
            } else {
                if (pres.size() == k - 1) { // 已经存在k-1 个，所有数的点数减一
                    allPresMinusOne(pres);
                } else {
                    pres.put(arr[i], 1);// 未满，填
                }
            }
        }

        HashMap<Integer, Integer> reals = getReals(arr, pres);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : pres.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / 2) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number!!!");
    }

    // 所有的pres -1
    private static void allPresMinusOne(HashMap<Integer, Integer> map) {
        LinkedList<Integer> removeList = new LinkedList<>();
        Integer key = null;
        Integer value = null;
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            key = set.getKey();
            value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }

        for(Integer removeKey : removeList){
            map.remove(removeKey);
        }
    }

    // 返回实际出现的次数
    private static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> pres){
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (pres.containsKey(cur)){
                if (reals.containsKey(cur)) {
                    reals.put(cur, reals.get(cur) + 1);
                } else {
                    reals.put(cur, 1);
                }
            }
        }
        return reals;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        findHalfMajority(arr);
        int K = 4;
        findKMajority(arr, K);
    }
}
