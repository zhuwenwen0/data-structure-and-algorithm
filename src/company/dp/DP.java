package company.dp;

/**
 * @author zhuwenwen
 * @date 14:00 20-07-2020
 **/
public class DP {


    /**
     * 在Linux Shell命令下通配符'*'表示0个或多个字符, 现编写一段代码实现通配符'*'的功能，注意只需要实现'*', 不用实现其他通配符。
     * <p>
     * <p>
     * 例如：在Linux Shell命令下通配符'*'表示0个或多个字符, 现编写一段代码实现通配符'*'的功能，注意只需要实现'*', 不用实现其他通配符。
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean isMatch(String str, String pattern) {
        boolean[][] dp = new boolean[str.length() + 1][pattern.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && str.charAt(i - 1) == pattern.charAt(j - 1);
                }
            }
        }
        return dp[str.length()][pattern.length()];
    }


    public static void main(String[] args) {
        System.out.println(isMatch("mqweckjln", "m*c*n"));
    }

}
