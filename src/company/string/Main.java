package company.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhuwenwen
 * @date 10:44 21-07-2020
 **/
public class Main {

    /**
     * 一个字符串删除一个字符之后变成回文字符串，如果字符串本身就是回文字符串，那么就返回-1
     *
     * @param str
     * @return
     */
    public static int getIndexAfterDeleteMatch(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        int start = 0, end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
                continue;
            }
            break;
        }
        if (start >= end) {
            return -1;
        }
        boolean match = isMatch(str.substring(start, end));
        if (match) {
            return end;
        } else {
            return start;
        }
    }


    private static boolean isMatch(String str) {
        //判断一个字符串是否是回文字符串
        if (str == null) {
            return false;
        }
        if ("".equals(str)) {
            return true;
        }
        int start = 0, end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }

        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strNum = reader.readLine();
        for (int i = 0; i < Integer.parseInt(strNum); i++) {
            String str = reader.readLine();
            System.out.println(getIndexAfterDeleteMatch(str));
        }
    }
}
