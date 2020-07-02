package algorithm.normal;

import java.util.ArrayList;

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
     * <p>
     * <p>
     * <p>
     * <p>
     * 假设f(n, m) 表示最终留下元素的序号。比如上例子中表示为:f(5,3) = 3
     * <p>
     * 首先，长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列。那么，我们可以递归地求解 f(n - 1, m)，
     * 就可以知道对于剩下的 n - 1 个元素，最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
     * <p>
     * 由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
     * 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n。
     * <p>
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
        for (int i = 2; i <= n; ++i) {
            index = (index + m) % i;
        }
        return index;
    }


    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     * <p>
     * 注意：-.123是表示数值的表示的是 -0.123
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str.length <= 0) {
            return false;
        }
        //是否存在E
        boolean existE = false;
        int ePosition = 0;
        //是否存在小数点
        boolean existPoint = false;
        //判断字符数组中是否含有e或者E
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                ePosition = i;
                existE = true;
            }
        }
        if (existE) {
            //如果e或者E是最后一位的话,直接返回false
            if (ePosition == str.length - 1) {
                return false;
            }
            return isNormalNum(str, 0, ePosition, true) && isNormalNum(str, ePosition + 1, str.length, false);
        } else {
            return isNormalNum(str, 0, str.length, true);
        }
    }

    private boolean isNormalNum(char[] str, int start, int end, boolean canHavePoint) {
        int pointNum = 0;
        for (int i = start; i < end; i++) {
            if (i == start && (str[i] == '+' || str[i] == '-')) {
                continue;
            }
            if (canHavePoint) {
                //含有小数点的情况
                if (str[i] >= 48 && str[i] <= 57) {
                    continue;
                }
                //判断含有小数点的情况
                if (str[i] == '.') {
                    if (i != end - 1 && i != start) {
                        //如果.号位于第一位或者最后一位的话，就返回false,
                        if (pointNum >= 1) {
                            return false;
                        }
                        pointNum++;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                //不含有小数点的情况
                if (str[i] < 48 || str[i] > 57) {
                    return false;
                }
            }
        }
        return true;
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
                    double v = result + (nums[i] - 48) * Math.pow(10, (double) nums.length - i - 1);
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


    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     *
     * 入栈的废弃了,实现困难太大
     * todo 使用递归
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //str未到尾，pattern到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
        if (strIndex == str.length && patternIndex != pattern.length) {
            //只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
            return false;
        }

        //str未到尾，pattern未到尾
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)//*匹配0个，跳过
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//*匹配1个，跳过
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                //直接跳过*（*匹配到0个）
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }

        if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }

        return false;
    }

    /**
     * 滑动窗口的最大值
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if(num == null || num.length <= 0) {
            return new ArrayList<>();
        }
        if (size <= 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if ((i + size - 1) >= num.length) {
                break;
            }
            int k = i + size - 1;
            int max = num[i];
            for (int j = i + 1; j <= k; j++) {
                if (num[j] > max) {
                    max = num[j];
                }
            }
            arrayList.add(max);
        }
        return arrayList;
    }




    public static void main(String[] args) {
        Sum sum = new Sum();
//        int i = sum.Sum_Solution(3);
//        //System.out.println(i);
//        int i1 = sum.StrToInt("1a90");
//        System.out.println(i1);
//
//
//        //表示-1 * 10的-1次方,也可以写成大写的E, e后面的数值必须为整数
//        double v = -1E-1;
//        double v1 = 10e2;
//        System.out.println();
//        System.out.println("ss:" + v);
//
//        double v2 = .123;
//        System.out.println("v2 is " + v2);
//
//        char[] charArray = {'+', '-', '2', '.', '3'};
//        System.out.println(sum.isNumeric(charArray));
//
//        String pattern ="ab*a";
//        String str = "aaa";
//        System.out.println("正则判断："+ sum.match(str.toCharArray(), pattern.toCharArray()));

        int[] nums = {2,3,4,2,6,2,5,1};
        ArrayList<Integer> integers = sum.maxInWindows(nums, 3);
        for (int i = 0; i < integers.size(); i++) {
            System.out.print(integers.get(i));
        }
    }
}
