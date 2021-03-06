package algorithm.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * 求最长不重复子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring (String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                start = Math.max(map.get(s.charAt(i)) + 1 , start);
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i - start + 1);
        }
        return result;
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
     * <p>
     * 入栈的废弃了,实现困难太大
     * todo 使用递归
     *
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
     * 滑动窗口的最大值,
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
     * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (num == null || num.length <= 0) {
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

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) != null) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > array.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 判断扑克牌是否是顺子
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length < 5) {
            return false;
        }
        //冒泡排序
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    //每次把最大的向后冒泡
                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        //把排序完成的牌，放到数组中,然后计算是否有缺失的牌，如果没有直接返回true,如果有缺失的牌,然后缺失的牌和鬼牌一样多的话，也是顺子
        int ghostCardNum = 0;
        int missingCard = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                ghostCardNum++;
            } else {
                //如果这张牌和后一张牌一样，直接返回false
                if (numbers[i] == numbers[i + 1]) {
                    return false;
                }
                missingCard = missingCard + numbers[i + 1] - numbers[i] - 1;
            }
        }
        if (ghostCardNum >= missingCard || missingCard == 0) {
            return true;
        }
        return false;
    }


    /**
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列?
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum <= 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 1; i < sum; i++) {
            int count = 1;
            int sumI = i;
            while (sumI < sum) {
                sumI = sumI + i + count;
                count++;
            }
            if (sumI == sum) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    arrayList.add(i + j);
                }
                arrayLists.add(arrayList);
            }
        }
        return arrayLists;
    }


    /**
     * 统计一个数字在排序数组中出现的次数。
     * 使用二分查找,先找出其中k的位置，然后向两边扩散统计
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        if (array[0] == k) {
            return countNum(array, 0);
        }
        if (array[array.length - 1] == k) {
            return countNum(array, array.length - 1);
        }
        //进行二分查找,找到k
        int start = 0;
        int end = array.length - 1;
        int position = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else {
                position = mid;
                break;
            }
        }
        if (position != 0) {
            return countNum(array,position);
        }
        return 0;
    }

    private int countNum(int[] array, int start) {
        int left = start - 1;
        int right = start + 1;
        boolean leftBreak = false;
        boolean rightBreak = false;
        int count = 1;
        while (true) {
            if (!leftBreak && left >= 0 && array[start] ==array[left]) {
                count++;
                left --;
            } else {
                leftBreak = true;
            }
            if (!rightBreak && right <= array.length - 1 && array[start] ==array[right]) {
                count++;
                right ++;
            } else {
                rightBreak = true;
            }
            if (leftBreak && rightBreak) {
                break;
            }
        }
        return count;
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

//        int[] nums = {2,3,4,2,6,2,5,1};
//        ArrayList<Integer> integers = sum.maxInWindows(nums, 3);
//        for (int i = 0; i < integers.size(); i++) {
//            System.out.print(integers.get(i));
//        }

//        ArrayList<ArrayList<Integer>> arrayLists = sum.FindContinuousSequence(100);
//        int printI = 0;
//        for (int i = 0; i < arrayLists.size(); i++) {
//            for (int j = 0; j < arrayLists.get(i).size(); j++) {
//                if (printI == i) {
//                    System.out.print(arrayLists.get(i).get(j) + " ");
//                } else {
//                    System.out.println();
//                    System.out.print(arrayLists.get(i).get(j) + " ");
//                    printI = i;
//                }
//            }
//        }
//        int[] a = {1,3, 5,6,7,7,7,7,9,10};
//        System.out.println(sum.GetNumberOfK(a, 7));
        System.out.println(sum.lengthOfLongestSubstring("abcdabcdefa"));
    }
}
