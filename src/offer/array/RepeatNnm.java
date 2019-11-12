package offer.array;

/**
 * 在一个长度为n的数组里面，里面的数字都是小于n-1的数字，判断有没有重复元素,并返回重复元素
 *
 * @author zhuwenwen
 * @date 2019/1/31 16:55
 */
public class RepeatNnm {


    public static void main(String[] args) {
        int[] a = {2, 5, 3, 0, 8, 3, 6, 1, 4};
        int[] temp = new int[1];
        if (solution(a, temp)) {
            System.out.println(temp[0]);
        } else {
            System.out.println(false);
        }
    }

    private static Boolean solution(int[] n, int[] temp) {
        if (n == null || n.length <= 0) {
            return false;
        }
        for (int i = 0; i < n.length; i++) {
            //如果数组里面的元素和下标不对应，那就一直处理以下逻辑
            while (n[i] != i) {
                //如果当前的值和当前值对应的下标的值相等的话，就直接返回
                if (n[i] == n[n[i]]) {
                    temp[0] = n[i];
                    return true;
                }
                //如果当前的值和当前值对应的下标的值不相等的话，就把当前下标值和当前下标的值交换
                swap1(n, i, n[i]);
            }
        }
        return false;
    }

    /**
     * 使奇数位排在数组前半部分，偶数位在后半部分，并且相对位置不变
     *
     * 思路：定义两个指针，一个遍历前半部分，一个遍历后半部分，然后交换
     *
     * @param array
     */
    public void reOrderArray(int[] array) {

    }

    private static void swap1(int[] n, int i, int j) {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }

}
