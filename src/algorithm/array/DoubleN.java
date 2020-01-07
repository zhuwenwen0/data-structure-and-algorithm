package algorithm.array;

import java.util.Stack;

/**
 * 求一个double的n次方
 *
 * @author zhuwenwen
 * @date 2019/11/11 21:40
 **/
public class DoubleN {

    /**
     * 求一个浮点型base的整数次方
     *
     * @param base     base
     * @param exponent exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (base == 0 && exponent == 0) {
            throw new IllegalArgumentException();
        }
        if (base == 0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        double result = 1;
        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                result = result * base;
            }
            return result;
        }
        for (int i = 0; i < -exponent; i++) {
            result = result * base;
        }
        return 1 / result;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * 思路：可以使用两个栈就可以轻易实现，不过遍历n,也可以使用一个栈，但是需要遍历两次
     *
     * @param array array
     */
    public void reOrderArray(int [] array) {
        if (array == null || array.length == 0) {
            return;
        }
        //奇数栈
        Stack<Integer> oddNumber = new Stack<>();
        //偶数栈
        Stack<Integer> evenNumbers = new Stack<>();
        for (int i = array.length -1; i>= 0; i--) {
            if (array[i] % 2 == 0) {
                evenNumbers.push(array[i]);
            } else {
                oddNumber.push(array[i]);
            }
        }
        int d = 0;
        while (!oddNumber.empty()) {
            array[d] = oddNumber.pop();
            d++;
        }
        while (!evenNumbers.empty()) {
            array[d] = evenNumbers.pop();
            d++;
        }

    }

    public static void main(String[] args) {
        DoubleN doubleN = new DoubleN();
//        double power = doubleN.Power(-2, -3);
//        System.out.println(power);
        int[] array = {1, 4, 2, 3, 8, 6, 5, 7};
        doubleN.reOrderArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}
