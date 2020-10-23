package struceure.lru;

/**
 * 思路，肯定需要map来实现get操作的o(1),肯定也需要链表
 *
 * @author zhuwenwen
 * @date 10:40 09-09-2020
 **/
public class LRUCache {

    private Integer size;

    private static Integer MAX_SIZE = Integer.MAX_VALUE;

    private static Integer DEFAULT_SIZE = 16;

    private Node[] table;

    public LRUCache() {
        this(DEFAULT_SIZE);
    }

    public LRUCache(Integer size) {
        this.size = size;
        table = new Node[size];
    }

    /**
     * get的操作需要早o(1)复杂度内完成
     *
     * @param key
     */
    public void get(int key) {

    }

    /**
     * set的操作也需要在o(1)复杂度内完成
     *
     * @param key
     * @param value
     */
    public void set(int key, int value) {

    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public static void main(String[] args) {

    }

    static class Node {

        private Integer key;

        private Object value;

        private Node next;

        private Node prev;

        public Node(Integer key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
