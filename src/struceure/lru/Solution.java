package struceure.lru;

import java.util.*;


public class Solution {

    class LFUCache{
        class Node{
            int k;
            int val;
            int freq;
            public Node(){}
            public Node(int k, int val, int freq){
                this.k = k;
                this.val = val;
                this.freq = freq;
            }
        }
        private int capacity;
        private Map<Integer, Node> map = new HashMap<>();
        private Map<Integer, LinkedList<Node>> freqMap = new HashMap<>();
        private int minFreq;
        public LFUCache(int capacity){
            this.capacity = capacity;
            this.minFreq = 1;
        }
        public void update(Node node){
            LinkedList<Node> list = freqMap.get(node.freq);
            list.remove(node);
            if (list.isEmpty() && node.freq == minFreq){
                minFreq++;
            }
            node.freq++;
            if (!freqMap.containsKey(node.freq)){
                freqMap.put(node.freq, new LinkedList<>());
            }
            freqMap.get(node.freq).addLast(node);

        }
        public int get(int k){
            if (!map.containsKey(k)){
                return -1;
            }
            Node node = map.get(k);
            update(node);
            return node.val;
        }
        public void put(int k, int val){
            if (map.containsKey(k)){
                Node node = map.get(k);
                node.val = val;
                update(node);
                map.put(k, node);
                return;
            }
            if (map.size() == capacity){
                Node node = freqMap.get(minFreq).removeFirst();
                map.remove(node.k);
            }
            Node node = new Node(k, val, 1);
            map.put(k, node);
            if (!freqMap.containsKey(1)){
                freqMap.put(1, new LinkedList<>());
            }
            freqMap.get(1).addLast(node);
            minFreq = 1;
        }
    }

    /**
     * lfu design
     * @param operators int整型二维数组 ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LFU (int[][] operators, int k) {
        // write code here
        LFUCache cache = new LFUCache(k);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < operators.length; i++){
            if (operators[i][0] == 1){
                cache.put(operators[i][1], operators[i][2]);
            }else{
                res.add(cache.get(operators[i][1]));
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
}