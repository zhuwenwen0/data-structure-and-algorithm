package algorithm.dp;

/**
 * 动态规划
 *
 * @author zhuwenwen
 * @date 18:16 29-06-2020
 **/
public class Dp {

    /**
     * 平方串问题：如果一个字符串S是由两个字符串T连接而成,即S = T + T, 我们就称S叫做平方串,例如"","aabaab","xxxx"都是平方串.
     * 牛牛现在有一个字符串s,请你帮助牛牛从s中移除尽量少的字符,让剩下的字符串是一个平方串。换句话说,就是找出s的最长子序列并且这个子序列构成一个平方串。
     * <p>
     * 输入：输入一个字符串s,字符串长度length(1 ≤ length ≤ 50),字符串只包括小写字符。
     * <p>
     * 输出：输出一个正整数,即满足要求的平方串的长度。
     * <p>
     * eg: 输入：bfrankfurt
     * <p>
     * 输出：4
     * <p>
     * 思路：把字符串切割成两个字符串，然后求两个字符串的最大子序列
     */

    public int getSqrtStringLength(String str) {
        if (str == null || str == "") {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < str.length(); i++) {
            //截取字符串
            String a = str.substring(0, i);
            String b = str.substring(i);
            res = Math.max(res, dp(a, b));
        }
        return res;
    }

    private int dp(String a, String b) {
        //计算两字符串的含有的最大子字符串
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        //需要多申请一位,下面计算的时候会用到
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (ca[i - 1] == cb[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a.length()][b.length()] * 2;
    }


    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     * <p>
     * 入栈的废弃了,实现困难太大
     * 使用动态规划： 动态规划最主要的就是状态转移,
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        //定义一个二维数组,数组长度为m+1 和 n+1
        boolean[][] f = new boolean[m + 1][n + 1];
        //如果s和p都为空的话,返回true,即两个都为空字符的时候,是匹配的
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    //如果s[i]和p[j-1]不匹配的话,那么f[i][j]即为f[i][j-2]的值
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        //如果s[i]和p[j-1]匹配的话,那么f[i][j] = ( f[i][j-2] || f[i-1][j] )
                        // 注：f[i-1][j]代表如果匹配了，那么就删除这个字符，并且j的*组合可以继续匹配
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * @param s 字符串
     * @return 回文子串   回文 ： 类似 ： 12321 这种
     */
    public String longestPalindrome(String s) {

        return null;
    }


    /**
     * 一个数组有 N 个元素，求连续子数组的最大和。 例如：[-1,2,1]，和最大的连续子数组为[2,1]，其和为 3
     *
     * @param a 整形数组
     * @return 连续子数组的最大和
     *
     * todo 会导致内存使用过大，牛客上可以，leetcode数组过大会导致内存超过
     *
     */
    public int maxSum(int[] a) {
        //空间复杂度 O(n2)
//        if (a == null || a.length <= 0) {
//            return 0;
//        }
//        int length = a.length;
//        //申请动态规划存储连续子数组的最大和数组
//        int[][] dp = new int[length + 1][length + 1];
//        int max = a[0];
//        for (int i = 0; i <= length; i++) {
//            for (int j = 1; j + i <= length; j++) {
//                int k = j + i;
//                //代表从i到k位置的连续子数组的最大和,进行动态规划
//                dp[i][k] = dp[i][k - 1] + a[k - 1];
//                if (max < dp[i][k]) {
//                    max = dp[i][k];
//                }
//            }
//        }
//        return max;

        //空间复杂度O(n)
        int[] dp = new int[a.length];
        dp[0] = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            //dp[i-1]就是前面数组连续子数组的最大值
            dp[i] = Math.max(dp[i- 1] + a[i], a[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Dp dp = new Dp();
//        boolean aaa = dp.isMatch("aaa", "ab*ac*a");
//        System.out.println(aaa);

//        int sqrtStringLength = dp.getSqrtStringLength("bfrankfurt");
//        System.out.println("平方串:" + sqrtStringLength);
        int[] a= {-1};
        int i = dp.maxSum(a);
        System.out.println(i);
    }
}
