package algorithm.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 排列组合
 *
 * @author zhuwenwen
 * @date 14:21 07-07-2020
 **/
public class Combination {

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     *
     * @param str
     * @return
     */
//    public ArrayList<String> Permutation(String str) {
//        char[] chars = str.toCharArray();
//    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] a = new int[Integer.parseInt(s)];

        for (int i = 0 ; i < a.length; i ++) {
            a[i] = Integer.parseInt(reader.readLine());
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
