package struceure.bst;

/**
 * @author zhuwenwen
 * @date 12:22 20-07-2020
 **/


public class Solution {

    class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     *
     *
     * @param l1 ListNode类
     * @param l2 ListNode类
     * @return ListNode类
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode first = l1;
        ListNode second = l2;
        //是否存在进位
        boolean carry = false;
        ListNode head = null;
        ListNode temp = null;
        while (first != null || second != null) {
            int val = (first == null ? 0 : first.val) + (second == null ? 0 : second.val) + (carry ? 1 : 0);
            if (head == null) {
                if (val >= 10) {
                    val = val - 10;
                    carry = true;
                }
                head = new ListNode(val);
                temp = head;
                first = first.next;
                second = second.next;
                continue;
            }
            if (val >= 10) {
                val = val - 10;
                carry = true;
            } else {
                carry = false;
            }
            ListNode listNode = new ListNode(val);
            temp.next = listNode;
            temp = listNode;
            first = first.next;
            second = second.next;
        }
        if (carry) {
            temp.next = new ListNode(1);
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
