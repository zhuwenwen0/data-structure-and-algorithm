package algorithm.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 排列组合
 *
 * @author zhuwenwen
 * @date 14:21 07-07-2020
 **/
public class Combination {

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * <p>
     * <p>
     * 思路，使用递归法
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if (str == null || str.equals("")) {
            return new ArrayList<>();
        }
        char[] chars = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        Permutation(result, chars, 0, chars.length - 1);
        Collections.sort(result);
        return result;
    }

    private void Permutation(ArrayList<String> result, char[] chars, int index, int length) {
        if (index == length) {
            //说明递归到最后一个元素,先判断是否含有当前排列组合的字符串，因为可能有重复字符
            if (!result.contains(new String(chars))) {
                result.add(new String(chars));
            }
        }
        //第一次固定好字符串的首字母
        for (int i = index; i <= length; i++) {
            //每个元素都和第一个元素进行交换,进行固定首字母
            swap(index, i, chars);

            //固定好首字母之后再固定第二个字母，处理交换后去掉首字母的子序列，然后对子序列进行排列组合
            Permutation(result, chars, index + 1, length);

            //然后把元素交换回去，进行下一次与第一个元素交换
            swap(index, i, chars);
        }
    }

    private void swap(int index, int i, char[] chars) {
        char temp;
        temp = chars[index];
        chars[index] = chars[i];
        chars[i] = temp;
    }


    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s = reader.readLine();
//        int[] a = new int[Integer.parseInt(s)];
//
//        for (int i = 0; i < a.length; i++) {
//            a[i] = Integer.parseInt(reader.readLine());
//        }
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
        Combination combination = new Combination();
        ArrayList<String> abc = combination.Permutation("abc");
        abc.forEach(c -> System.out.println(c));
    }
}
