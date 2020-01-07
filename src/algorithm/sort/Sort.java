package algorithm.sort;

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
     * 归并排序(分治思想)
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    public int[] mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
        return a;
    }

    /**
     * 把从low 到mid的数组和从mid + 1到high的数组进行合并,这两个数组的是有序的,然后进行合并
     *
     * @param a    数组a
     * @param low  低下标
     * @param mid  中间的下标
     * @param high 高下标
     */
    public void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
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
        //sort.quickSort(a, 0, a.length - 1);
//        for (int i = 0; i <a.length ; i++) {
//            System.out.println(a[i]);
//        }
        //System.out.println(sort.binarySearch(a, 0, a.length - 1, 11));
        int[] ints = sort.mergeSort(a, 0, a.length - 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
