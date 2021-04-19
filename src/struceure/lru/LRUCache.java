package struceure.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuwenwen
 * @date 30-03-2021 18:20
 **/
public class LRUCache {

    private Map<Integer, LruNode> map = new HashMap<>();

    private Integer size;

    private LruNode head;

    private LruNode tail;

    private Integer linkSize;

    public LRUCache(Integer size) {
        if (size <= 0) {
            throw new IllegalArgumentException("参数无实际意义");
        }
        this.size = size;
        linkSize= 0;
        //初始化两个节点,无实际意义
        head = new LruNode();
        tail = new LruNode();
    }

    public void put(int key , int value) {
        LruNode newNode = new LruNode(key, value);
        map.put(key, newNode);
        linkSize++;
        //头插入插入一个节点,维护链表
        newNode.next = head.next;
        head.next.pre = newNode;

        head.next = newNode;
        newNode.pre = head;

        if (linkSize > size) {
            //进行淘汰末尾节点
        }
    }

    public Integer get(int key) {
        LruNode lruNode = map.get(key);
        //然后把lruNode 移动到链表的头结点
        //思路是先删除当前节点，然后从新头插入插入这个节点
        return null;
    }


    class LruNode {

        private Integer key;

        private Integer value;

        private LruNode next;

        private LruNode pre;

        public LruNode() {
        }

        public LruNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public LruNode getNext() {
            return next;
        }

        public void setNext(LruNode next) {
            this.next = next;
        }

        public LruNode getPre() {
            return pre;
        }

        public void setPre(LruNode pre) {
            this.pre = pre;
        }
    }
}
