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
        return 0;
    }


    /**
     * 求几个数的最小公倍数
     *
     * @param a
     * @return
     */
    public int getLcm(int[] a) {
        return 0;
    }

    public int getGcdTwoNum(int num1, int num2) {
        if (num1 < num2) {
            int tem = num1;
            num1 = num2;
            num2 = tem;
        }
        while (num1 % num2 != 0) {
            num1 = num1 % num2;
        }
        return 0;
    }
}
