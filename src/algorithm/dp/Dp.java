package algorithm.dp;

import algorithm.string.Solution;

/**
 * 动态规划
 *
 * @author zhuwenwen
 * @date 18:16 29-06-2020
 **/
public class Dp {

    /**
     * 如果一个字符串S是由两个字符串T连接而成,即S = T + T, 我们就称S叫做平方串,例如"","aabaab","xxxx"都是平方串.
     * 牛牛现在有一个字符串s,请你帮助牛牛从s中移除尽量少的字符,让剩下的字符串是一个平方串。换句话说,就是找出s的最长子序列并且这个子序列构成一个平方串。
     *
     * 输入：输入一个字符串s,字符串长度length(1 ≤ length ≤ 50),字符串只包括小写字符。
     *
     * 输出：输出一个正整数,即满足要求的平方串的长度。
     *
     * eg: 输入：bfrankfurt
     *
     * 输出：4
     */

    public int getSqrtStringLength(String str) {
        return 0;
    }


    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     *
     * 入栈的废弃了,实现困难太大
     * 使用动态规划： 动态规划最主要的就是状态转移,
     *
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
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
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
}
