package offer.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuwenwen
 * @date 2019/2/1 9:10
 */
public class LinkedListCustom {

    private Node head;

    /**
     * 向单链表中插入值
     *
     * @param value 值
     */
    public void insertLinkedLIst(int value) {
        //如果单链表为空的话，那么新建头指针
        if (head == null) {
            head = new Node(value);
            head.nextNode = null;
            return;
        }
        Node temp = head;
        while (temp.nextNode != null) {
            temp = temp.nextNode;
        }
        temp.nextNode = new Node(value);
    }

    /**
     * 从后到前打印单链表，递归,不需要保存
     *
     * @param linkedHead 头结点
     * @return 链表的值集合
     */
    public void reverseLinkedList1(Node linkedHead) {
        if (linkedHead == null) {
            return;
        }
        //先递归到最后一个元素，然后再进行打印
        reverseLinkedList1(linkedHead.nextNode);
        System.out.println(linkedHead.getValue());
    }

    /**
     * 单链表从后到前打印链表
     * <p>
     * todo 以后再实现一次
     *
     * @return 单链表从后向前遍历
     */
    public List<Integer> reverseLinkedList(Node linkedHead) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (linkedHead != null) {
            //因为每一次地柜都会新创建一个空的arrayList，所以每次迭代之前都需要把之前的arrayList里面的值加进去
            arrayList.addAll(reverseLinkedList(linkedHead.nextNode));
            arrayList.add(linkedHead.getValue());
        }
        return arrayList;
    }

    /**
     * 反转链表
     * <p>
     * todo 反转链表
     *
     * @param head 链表的头
     * @return 反转后的链表
     */
    public Node reverseList(Node head) {
        Node temp = null, nextNode = null;
        //重新构建一条链表，思路是先把头结点的下一节点地址赋给nextNode,记录当前头结点下一节点的地址，
        // 然后把当前头结点的下一节点地址指向空
        //再然后把空指针指向头结点的地址，然后把头指针后移到nextNode的地址，继续下一步反转
        while (head != null) {
            nextNode = head.nextNode;
            head.nextNode = temp;
            temp = head;
            head = nextNode;
        }
        return temp;
    }

    public Node reverList(Node head) {
        if (head == null) {
            return null;
        }

        Node temp = null, nextNode = null;
        //重新构建一条链表，思路是先把头结点的下一节点地址赋给nextNode,记录当前头结点下一节点的地址，
        // 然后把当前头结点的下一节点地址指向空
        //再然后把空指针指向头结点的地址，然后把头指针后移到nextNode的地址，继续下一步反转
        while (head != null) {
            nextNode = head.nextNode;
            head.nextNode = temp;
            temp = head;
            head = nextNode;
            System.out.println(head == null ? -1 : head.getNextNode().getValue());
//            System.out.println("tttttt--sss");
//            System.out.println(temp == null ? -1 : temp.getValue());
//            System.out.println("tttttt--end");
        }
        return temp;
    }

    /**
     * o(1)复杂度实现删除链表中的节点
     *
     * @param head   头结点
     * @param target 目标节点
     * @return 头结点
     */
    public Node deleteNode(Node head, Node target) {
        if (head == null || target == null) {
            return null;
        }
        if (target.nextNode != null) {
            //删除的不是尾节点,那么就可以把下一个节点的值复制到当前节点，再把下一个节点删掉
            target.value = target.nextNode.value;
            target.nextNode = target.nextNode.nextNode;
        } else {
            //如果删除的是尾节点的话，那么时间复杂度就是o(n)
            Node temp = head;
            //遍历到尾节点的前一个节点
            while (temp.nextNode != target) {
                temp = temp.nextNode;
            }
            temp.nextNode = null;
        }
        return head;
    }

    /**
     * 在一个排序的链表中删除链表中相同节点值的节点，不保留重复节点
     *
     * @param head 头结点
     */
    public Node deleteSameNode(Node head) {
        if (head == null || head.nextNode == null) {
            return head;
        }
        Node nextNode = head.nextNode;
        //如果当前节点和下一节点的值相同
        if (head.value.equals(nextNode.value)) {
            while (nextNode != null && head.value.equals(nextNode.value)) {
                //删除相同的节点
                nextNode = nextNode.nextNode;
            }
            return deleteSameNode(nextNode);
        } else {
            head.nextNode= deleteSameNode(head.nextNode);
            return head;
        }
    }

    /**
     * 获取倒数第k个节点的值
     *
     * @param k 倒数的数量
     * @return 节点
     */
    public Node getNodeTailK(int k,Node head){
        if (k <= 0 ){
            return null;
        }
        int j=0;
        if (head == null){
            return null;
        }
        Node temp=head;
        while (temp != null){
            temp=temp.nextNode;
            j++;
        }
        if (j<k){
            return null;
        }
        Node node=head;
        for (int i = 0; i <j-k ; i++) {
            node=node.nextNode;
        }
        return node;
    }

    /**
     * 使用双指针获取倒数第k个节点的值，思路是让p1先走k个节点，
     * 然后让p2才开始走，这样当p1走到结尾的时候，p2正好是第k个节点
     *
     * @param k 倒数第k
     * @param head 头结点
     * @return 倒数第k个节点的值
     */
    public Node findKthToTail(int k,Node head){
        if (head == null || k <= 0){
            return null;
        }
        Node p1=head,p2=head;
        //先让p1走k步
        while (p1 != null && k-- > 0){
            p1=p1.nextNode;
        }
        //如果链表长度小于k
        if (k > 0){
            return null;
        }
        while (p1 != null){
            p1=p1.nextNode;
            p2=p2.nextNode;
        }
        return p2;
    }

    /**
     * 获得环的第一个节点，让快指针走两步，慢指针走一步，当两指针第一次相遇时，让快指针指向头结点，然后走一步，
     * 慢指针继续走，再次相遇就是环的入口节点，如果第一次相遇是在头结点的话，
     * 那么整个链表就是一个环，直接返回头结点即可
     *
     * @param head 链表的头结点
     * @return 环的头结点
     */
    public Node getCycleNode(Node head){
        if (head == null){
            return null;
        }
        int k=0;
        Node fastNode=head,lowNode=head,temp=head;
        while (fastNode.nextNode != null && fastNode.nextNode.nextNode != null ){
            if (k == 1){
                //若是在头结点相遇,那么这个链表就是一个环
                if (fastNode == head ){
                    return head;
                }
                temp=temp.nextNode;
                lowNode=lowNode.nextNode;
                if (temp == lowNode){
                    return temp;
                }
                continue;
            }
            fastNode=fastNode.nextNode.nextNode;
            lowNode=lowNode.nextNode;
            if (fastNode == lowNode){
                k++;
            }
        }
        return null;
    }

    /**
     * 合并两个有序链表,递归
     *
     * @param head1 链表1的头节点
     * @param head2 链表2的头结点
     * @return 合并后的链表头结点
     */
    public Node mergeTwoLinked(Node head1,Node head2){
        if (head1 == null){
            return head2;
        }
        if (head2 == null){
            return head1;
        }
        if (head1.getValue() <= head2.getValue()){
            head1.nextNode=mergeTwoLinked(head1.nextNode,head2);
            return head1;
        }else {
            head2.nextNode=mergeTwoLinked(head1,head2.nextNode);
            return head2;
        }
    }

    /**
     * 遍历
     */
    public void traversingLinkedList(Node head) {
        if (head == null) {
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getValue());
            temp = temp.nextNode;
        }
    }


    public static void main(String[] args) {
        LinkedListCustom linkedListCustom = new LinkedListCustom();
        linkedListCustom.insertLinkedLIst(1);
        linkedListCustom.insertLinkedLIst(2);
        linkedListCustom.insertLinkedLIst(3);
        linkedListCustom.insertLinkedLIst(4);
        linkedListCustom.insertLinkedLIst(5);
        linkedListCustom.insertLinkedLIst(6);
        Node head = linkedListCustom.reverList(linkedListCustom.getHead());
//        while (head != null) {
//            System.out.println(head.getValue());
//            head = head.getNextNode();
//        }

//        linkedListCustom.getHead().nextNode.nextNode.nextNode.nextNode.nextNode.nextNode=linkedListCustom.getHead();
//        System.out.println(linkedListCustom.getCycleNode(linkedListCustom.getHead()).getValue());
        //linkedListCustom.deleteNode(linkedListCustom.getHead(), linkedListCustom.getHead().nextNode.nextNode);
        //Node node = linkedListCustom.reverseList(linkedListCustom.getHead());
        //linkedListCustom.deleteSameNode(linkedListCustom.getHead());
        //Node nodeTailK = linkedListCustom.findKthToTail(3, linkedListCustom.getHead());
        //linkedListCustom.traversingLinkedList(linkedListCustom.getHead());
        //linkedListCustom.reverseLinkedList1(linkedListCustom.getHead());
    }


    public Node getHead() {
        return head;
    }

    static class Node {
        private Integer value;

        private Node nextNode;

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
