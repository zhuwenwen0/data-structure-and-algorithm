package struceure.datastruct;

import java.util.HashMap;
import java.util.Stack;

/**
 * 用两个栈来实现队列的操作
 *
 * @author zhuwenwen
 * @date 2019/2/1 15:06
 */
public class StackLeetCode {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    private java.util.Map<Integer,Integer> map=new HashMap<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.isEmpty()) {
            System.out.println("栈为空");
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * @param pushA 压栈顺序数组
     * @param popA  出栈顺序数组
     * @return 是否是一个出栈的顺序
     * <p>
     * 解题思路：先判断两个数组里面的值是否都相同，找到压栈最后的元素，遍历出栈的顺序，
     * 直到找到最后一个元素，然后之后的顺序必须是压栈顺序的逆序,
     * 可以不连续，但是下标一定是递减的，
     * 否则返回false
     *
     * 简单解法：用一个栈来模拟入栈出栈
     */
    public  boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length != popA.length) {
            return false;
        }
        //todo 先要判断两个数组元素是否相同，且不能改变数组顺序
        if (pushA.length <= 2) {
            return true;
        }
        //把压入顺序放到map中
        for (int i = 0; i < pushA.length; i++) {
            map.put(pushA[i],i);
        }

        int spilt = 0;
        //先找到最后一个元素的下标
        for (int i = 0; i < popA.length; i++) {
            if (popA[i] == pushA[pushA.length - 1]) {
                spilt = i;
                break;
            }
        }

        if (popA.length - 1 - spilt <= 1) {
            return true;
        }
        //查看出栈后面的元素下标是否正确
        while (++spilt+1 < popA.length){
            if (map.get(popA[spilt])<map.get(popA[spilt+1])){
                return false;
            }
        }
        return true;
    }

    public Boolean IsPopOrder(int[] pushA, int[] popA){
        if(pushA == null || popA == null || pushA.length == 0 || popA.length == 0 || pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> st = new Stack<Integer>();
        int i = 0;
        int j = 0;
        st.push(pushA[i++]);
        while(j <= popA.length-1){
            while(popA[j] != st.peek()){
                if(i == pushA.length) {
                    return false;
                }
                st.push(pushA[i++]);
            }
            j++;
            st.pop();
        }
        return true;
    }

    public static void main(String[] args) {
        int[] ya={1,2,3,4,5};
        int[] po={4,3,5,1,2};
        StackLeetCode stackLeetCode=new StackLeetCode();
        System.out.println(stackLeetCode.isPopOrder(ya,po));
    }
}

