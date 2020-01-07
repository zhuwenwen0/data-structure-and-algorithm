package algorithm.fibonacci;

/**
 * 斐波那契数列
 *
 * @author zhuwenwen
 * @date 2019/2/1 15:15
 */
public class NormalFibonacci {
    /**
     * 递归求斐波那契数列，会重复计算一部分值，可以试试动态规划
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n <= 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /**
     * 把计算出的数值存到数组中，这样就不需要重复计算自运算的值
     *
     * @param n
     * @return
     */
    public static int fibonacciBy(int n){
        if (n <= 1){
            return n;
        }
        int[] array=new int[n+1];
        array[1]=1;
        for (int i = 2; i <= n; i++) {
            array[i]=array[i-1]+array[i-2];
        }
        return array[n];
    }


    /**
     * 青蛙跳台阶问题,解题思路，青蛙想跳上n阶阶梯，可以从n-1阶跳上去，也可以从n-2阶跳上去
     * 所以就是跳到n-1阶梯的跳法加上n-2阶阶梯的跳法
     *
     * @param n 台阶数
     * @return 跳法
     */
    public int jumpFloor(int n){
        if (n <= 1){
            return n;
        }
        if (n == 2){
            return 2;
        }
        return jumpFloor(n-1)+jumpFloor(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacciBy(6));
    }
}
