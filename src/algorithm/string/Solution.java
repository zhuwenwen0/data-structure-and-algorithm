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

    //-------------------------------------------------------------

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.Insert('g');
        solution.Insert('o');
        solution.Insert('o');
        solution.Insert('g');
        solution.Insert('l');
        solution.Insert('e');
        char c = solution.FirstAppearingOnce();
        System.out.println(c);
    }
}
