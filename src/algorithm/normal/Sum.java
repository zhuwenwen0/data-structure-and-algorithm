package algorithm.normal;

/**
 * @author zhuwenwen
 * @date 13:57 24-06-2020
 **/
public class Sum {

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     *
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + Sum_Solution(n - 1);
    }


    /**
     * 每年六一儿童节,都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
     * 准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
     * 然后,他随机指定一个数 m,让编号为0的小朋友开始报数。每次喊到 m-1 的那个小朋友要出列唱首歌,
     * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
     * 可以不用表演,并且拿到“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到 n-1)
     * <p>
     * 如果没有小朋友，请返回-1
     *
     *
     *
     *
     * 假设f(n, m) 表示最终留下元素的序号。比如上例子中表示为:f(5,3) = 3
     *
     * 首先，长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列。那么，我们可以递归地求解 f(n - 1, m)，
     * 就可以知道对于剩下的 n - 1 个元素，最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
     *
     * 由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
     * 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n。
     *
     * 当n等于1时，f(1,m) = 0
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {

        if (n <= 0) {
            return -1;
        }
        int index = 0;
        for (int i=2; i<=n; ++i) {
            index = (index + m) % i;
        }
        return index;
    }


    /**
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     * 输入：+2147483647
     * 1a33
     * <p>
     * 输出：
     * 2147483647
     * 0
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        long result = 0;
        //是否为正数
        boolean isGtZero = true;
        boolean skip = false;
        char[] nums = str.toCharArray();
        if (nums[0] == '-') {
            isGtZero = false;
            skip = true;
        } else if (nums[0] == '+') {
            skip = true;
        }
        if (skip) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] >= 48 && nums[i] <= 57) {
                    double v = result + (nums[i] - 48) * Math.pow(10, (double) nums.length - i -1);
                    result = (long) v;
                } else {
                    return 0;
                }
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= 48 && nums[i] <= 57) {
                    double v = result + (nums[i] - 48) * Math.pow(10, (double) nums.length - i - 1);
                    result = (long) v;
                } else {
                    return 0;
                }
            }
        }
        //判断是否超出int类型的表示范围
        if (isGtZero) {
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
        } else {
            if (-result < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return isGtZero ? (int) result : (int) -result;
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        int i = sum.Sum_Solution(3);
        //System.out.println(i);
        int i1 = sum.StrToInt("1a90");
        System.out.println(i1);


        //表示-1 * 10的-1次方,也可以写成大写的E, e后面的数值必须为整数
        double v = -1E-1;
        double v1 = 10e2;
        System.out.println();
        System.out.println("ss:" + v);


    }
}
