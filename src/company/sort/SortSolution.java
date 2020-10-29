package company.sort;

/**
 * @author zhuwenwen
 * @date 13:23 29-10-2020
 **/
public class SortSolution {

    /**
     * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
     *
     * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
     *
     * 示例1   输入： [1,3,5,2,2] , 5, 3
     * 输出： 2
     *
     * @param a
     * @param n
     * @param K
     * @return
     */
    public int findKth(int[] a, int n, int K) {
        // write code here
        if(n <= 0 || K < 0) {
            return -1;
        }
        return findKth(0, n-1, a, K);
    }

    private int findKth(int index, int length, int[] a, int k) {
        int start = index, end = length;
        int key = a[start];
        while(start != end) {
            while(start < end && a[end] >= key) {
                end--;
            }
            a[start] = a[end];
            while(start < end && a[start] < key) {
                start++;
            }
            a[end] = a[start];
        }
        a[start] = key;

        if(length - end + 1 == k) {
            return a[end];
        } else if(length - end + 1> k) {
            return findKth(end + 1, length, a, k);
        } else {
            return findKth(index, end-1, a, k - (length - end + 1));
        }

    }

}
