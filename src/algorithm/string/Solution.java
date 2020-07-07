package algorithm.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhuwenwen
 * @date 17:09 22-06-2020
 **/
public class Solution {

    //------------------------------------------------------------
    /**
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     *
     * 使用linkedHashMap来实现,{@link LinkedHashMap}
     *
     * @param ch
     */
    private Map<Character, Integer> map = new LinkedHashMap<>();

    /**
     * 插入一个字符
     *
     * @param ch
     */
    public void Insert(char ch) {
        if (map.get(ch) != null && map.get(ch) != 0) {
            map.put(ch, map.get(ch) + 1);
        } else {
            map.put(ch, 1);
        }
    }

    /**
     * 返回第一个只出现一次的字符
     *
     * @return
     */
    public char FirstAppearingOnce() {
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '#';
    }


    /**
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，
     * 但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     *
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return str;
        }
        String[] strArray = str.split(" ");
        StringBuilder strBuild = new StringBuilder();
        for (int i = strArray.length - 1; i >= 0; i--) {
            strBuild.append(strArray[i]);
            if(i != 0) {
                strBuild.append(" ");
            }
        }
        return strBuild.toString();
    }


    /**
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
     * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
     *
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0 || n <= 0) {
            return str;
        }
        int length = str.length();
        //需要左移的位数
        int count = n % length;
        return str.substring(count) + str.substring(0, count);
    }

    //-------------------------------------------------------------

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.Insert('g');
//        solution.Insert('o');
//        solution.Insert('o');
//        solution.Insert('g');
//        solution.Insert('l');
//        solution.Insert('e');
//        char c = solution.FirstAppearingOnce();
//        System.out.println(c);

//        String s = solution.ReverseSentence(" ");
//        System.out.println("s:" + s + ".");
        System.out.println(solution.LeftRotateString("abcXYZdef", 3));
    }
}
