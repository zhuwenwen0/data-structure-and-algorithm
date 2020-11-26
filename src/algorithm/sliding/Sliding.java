package algorithm.sliding;

import com.sun.deploy.util.StringUtils;

/**
 * 滑动窗口先关类型
 *
 * @author zhuwenwen
 * @date 11:09 26-11-2020
 **/
public class Sliding {


    /**
     * 查找最长不重复子串
     *
     * @param str
     * @return
     */
    public static Integer getMaxUniqueSubString(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int max = 0, start = 0 , end = 1 ;
        while (end < str.length()) {
            String temp = str.substring(start, end);
            if (temp.indexOf(str.charAt(end)) < 0) {
                end ++;
            } else {
                //进行滑动
                int i = temp.indexOf(str.charAt(end));
                start = start + i + 1;
                max = max >= temp.length() ? max : temp.length();
                end++;
            }
        }
        return max >= (end - start) ? max : (end - start);
    }

    public static void main(String[] args) {
        System.out.println(getMaxUniqueSubString("bbbbb"));
    }
}
