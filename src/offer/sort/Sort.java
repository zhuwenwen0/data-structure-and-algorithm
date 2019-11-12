package offer.sort;

public class Sort {
    /**
     * 快速排序
     *
     * @param a
     * @param low
     * @param high
     */
    public void quickSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int first = low, last = high;
        int key = a[first];
        while (first != last) {
            while (first < last && a[last] >= key) {
                last--;
            }
            a[first] = a[last];
            while (first < last && a[first] < key) {
                first++;
            }
            a[last] = a[first];
        }
        a[first] = key;
        quickSort(a, low, first - 1);
        quickSort(a, last + 1, high);
    }

    /**
     * 二分查找
     *
     * @param a
     * @param low
     * @param high
     * @param target
     * @return
     */
    public boolean binarySearch(int[] a, int low, int high, int target) {
        //可以low等于high,所以不能用 >=
        if (low > high) {
            return false;
        }
        int mid = (low + high) / 2;
        if (a[mid] == target) {
            return true;
        }
        if (target < a[mid]) {
            return binarySearch(a, low, mid - 1, target);
        }
        return binarySearch(a, mid + 1, high, target);
    }


    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] a = {3, 3, 9, 6, 8, 5, 7, 10, 17, 12, 15};
        sort.quickSort(a, 0, a.length - 1);
//        for (int i = 0; i <a.length ; i++) {
//            System.out.println(a[i]);
//        }
        System.out.println(sort.binarySearch(a, 0, a.length - 1, 11));
    }
}
