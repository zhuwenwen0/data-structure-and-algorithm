package algorithm.bitoperation;

import java.util.Stack;

/**
 * 位运算
 *
 * @author zhuwenwen
 * @date 15:57 13-01-2020
 **/
public class BitOperation {

    private Stack<Integer> stack = new Stack<>();


    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n input the int num
     * @return the number of 1
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }


    public static void main(String[] args) {
    }
}
