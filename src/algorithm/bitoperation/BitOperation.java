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
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     * 1 & 1 = 1;   1 & 0 = 0;   0 & 0 = 0
     * 1 | 1 = 1;   1 | 0 = 1;   0 | 0 = 0
     * 1 ^ 1 = 0;   1 ^ 0 = 1;   0 ^ 0 = 0; 相异为真
     * ~1 = 0;      ~0 = 1;
     *
     * 1 + 1 = 0;(有进位)  1 + 0 = 1；  0 + 0 = 0；(无进位)
     * 所以加法就是异或加上进位的标志位
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        int result = 0;
        int carry = 0;
        do{
            //不带进位的加法
            result = num1 ^ num2;
            //进位,比如11 + 7 ， 1011 + 0111 num1 & num2是11，所以需要在左移一位,然后左移1为代表进位为110
            carry = (num1 & num2) << 1;

            //把不带进位的结果赋值成num1,然后加上进位需要加上的数,如果carry为6(110),则代表之前有两个位置需要进位110,然后再继续相加直到不存在进位则加法运算结束
            num1 = result;
            num2 = carry;
            //进位不为0则继续执行加法处理进位
        }while(carry != 0);
        return result;
    }


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
