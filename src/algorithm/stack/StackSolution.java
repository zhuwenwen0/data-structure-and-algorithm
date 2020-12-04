package algorithm.stack;

import java.util.Stack;

/**
 * @author zhuwenwen
 * @date 17:51 27-11-2020
 **/
public class StackSolution {

    /**
     * 给出一个仅包含字符'('和')'的字符串，计算最长的格式正确的括号子串的长度。
     * 对于字符串"(()"来说，最长的格式正确的子串是"()"，长度为2.
     * 再举一个例子：对于字符串")()())",来说，最长的格式正确的子串是"()()"，长度为4.
     * "((()()(((()" 这个字符串连续格式正确的也是4
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        // write code here
        if (s == null || s.length() < 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0, left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    if (!stack.isEmpty()) {
                        max = Math.max(max, i - stack.peek());
                    } else {
                        max = Math.max(max, i - left);
                    }
                } else {
                    left = i;
                }
            }
        }
        return max;
    }

    /**
     * 使用动态规划解决
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        // write code here
        int[] dp = new int[s.length()];
       return 0;
    }

    public static void main(String[] args) {
        StackSolution stackSolution = new StackSolution();
        System.out.println(stackSolution.longestValidParentheses("((()()(((()"));

    }
}
