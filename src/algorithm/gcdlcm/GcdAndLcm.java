package algorithm.gcdlcm;

/**
 * 最大公约数和最小公倍数
 *
 * @author zhuwenwen
 * @date 15:13 13-07-2020
 **/
public class GcdAndLcm {


    /**
     * 求几个数的最大公约数(辗转相除法,也叫欧几里德算法)
     *
     * @param a
     * @return
     */
    public int getGcd(int[] a) {
        if (a == null || a.length <= 0) {
            return 0;
        }
        int gcd = a[0];
        int start = 1;
        while (start <= a.length - 1) {
            gcd = getGcdTwoNum(gcd, a[start]);
            start++;
        }
        return gcd;
    }


    /**
     * 求几个数的最小公倍数
     *
     * @param a
     * @return
     */
    public int getLcm(int[] a) {
        if (a == null || a.length < 0) {
            return 0;
        }
        int lcm = a[0];
        for (int i = 1; i < a.length; i++) {
            lcm = lcm * a[i] / getGcdTwoNum(lcm, a[i]);
        }
        return lcm;
    }

    /**
     * 求两个数的最大公约数,辗转相除法
     *
     * @param num1 num1
     * @param num2 num2
     * @return
     */
    public int getGcdTwoNum(int num1, int num2) {
        int big = num1 > num2 ? num1 : num2;
        int small = num1 < num2 ? num1 : num2;
        while (big % small != 0) {
            int result = big % small;
            big = small > result ? small : result;
            small = small > result ? result : small;
        }
        return small;
    }

    public static void main(String[] args) {
        GcdAndLcm gcdAndLcm = new GcdAndLcm();
        int[] a = {3, 4, 6};
        System.out.println(gcdAndLcm.getGcd(a));
        System.out.println(gcdAndLcm.getLcm(a));
    }
}
