package algorithm.twodirections;

import java.util.ArrayList;

/**
 * @author zhuwenwen
 * @date 2019/1/31 22:31
 */
public class StringReplaceSpace {

    public static void main(String[] args) {

        StringBuffer b=new StringBuffer("wode make save");
        System.out.println(replaceSpace(b));
    }

    /**
     * 把一组字符串里的空格替换成%20
     *
     * @param str 字符串
     * @return 替换空格的结束字符串
     */
    private static String replaceSpace(StringBuffer str) {
        if (str == null || str.length()<= 0){
            return "";
        }
        int p1 = str.length();
        for (int i = 0; i < p1; i++) {
            if (str.charAt(i) == ' '){
                str.append("  ");
            }
        }
        int p2=str.length();
        while (p1>=0 && p2>p1){
            //从最开始字符串的最后一位开始计算
            char c = str.charAt(--p1);
            //如果最后以为是空字符的话
            if (c == ' '){
                //那么就从后往前设置字符,因为p1和p2都是字符串的长度，所以需要提前减一，字符串和数组一样，下标都是从0开始的
                str.setCharAt(--p2,'0');
                str.setCharAt(--p2,'2');
                str.setCharAt(--p2,'%');
            }else {
                str.setCharAt(--p2,c);
            }
        }
        return str.substring(0,str.length());
    }



    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        if(array == null || array.length <= 0) {
            return new ArrayList();
        }
        ArrayList<Integer> list = new ArrayList();
        int i = 0; int k = array.length -1;
        while (i != k) {
            if(array[i] + array[k] == sum) {
                list.add(array[i]);
                list.add(array[k]);
                break;
            } else if (array[i] + array[k] > sum) {
                //如果大于的话，说明这个不符合，让k-1, i重新开始遍历
                k--;
                i = 0;
            } else {
                //如果小于的话，说明i需要加1
                i++;
            }
        }
        return list;
    }
}
