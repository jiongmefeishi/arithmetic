package cn.zqtao.learn.nowcode_base.day5;

import java.util.HashMap;

/**
 * @auther: zqtao
 * @description: 设计RandomPool结构
 * 【题目】 设计一种结构，在该结构中有如下三个功能：
 * insert(key)：将某个key加入到该结构，做到不重复加入。
 * delete(key)：将原本在结构中的某个key移除。
 * getRandom()： 等概率随机返回结构中的任何一个key。
 *
 * 【要求】 Insert、delete和getRandom方法的时间复杂度都是 O(1)
 *
 * 思路： 准备两个map，一个size表示进入的数量
 * @version: 1.0
 */
public class Code_32_RandomPool {
    public static class RandomPool<K>{
        private HashMap<K, Integer> key_index;
        private HashMap<Integer, K> index_key;
        private int size;

        public RandomPool(){
            this.key_index = new HashMap<>();
            this.index_key = new HashMap<>();
            size = 0;
        }

        public void insert(K key){
            if (!this.key_index.containsKey(key))
            this.key_index.put(key, this.size);
            this.index_key.put(this.size++, key);
        }

        public K getRandom(){
            if (this.size == 0){
                return null;
            }
            int key = (int) (Math.random() * this.size);
            return this.index_key.get(key);
        }

        public void delete(K key) {
            if (this.key_index.containsKey(key)){
                int deleteIndex = this.key_index.get(key);
                int lastIndex = --this.size;
                K lastKey = this.index_key.get(lastIndex);
                this.key_index.put(lastKey, deleteIndex);
                this.index_key.put(deleteIndex, lastKey);
                this.key_index.remove(key);
                this.index_key.remove(lastIndex);
            }
        }
    }

    public static void main(String[] args) {
        RandomPool<String> pool = new RandomPool<>();
        pool.insert("test");
        pool.insert("fuck");
        pool.insert("code");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }
}
