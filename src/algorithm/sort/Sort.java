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
     * 堆排序,大顶堆
     *
     * @param nums
     */
    public void heapSort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            swim(i, nums);
        }
    }

    public void swim(int index, int[] nums) {
        //如果子节点比其父节点大,并且index不能是头结点
        while (nums[index] > nums[index / 2] && index != 1) {
            swap(index, index / 2, nums);
            index = index / 2;
        }
    }

    public void swap(int index1, int index2, int[] nums) {
        if (index1 <= 0 || index2 <= 0) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public int del(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[1];
        }
        int top = nums[1];
        nums[1] = nums[nums.length -1];
        //令最后一个元素为空,方便回收
        nums[nums.length -1] = 0;
        //进行下沉,维护堆的性质
        sink(1, nums);
        return top;
    }

    public void sink(int index, int[] nums) {
        while (index * 2 <= nums.length -1 && (nums[index] < nums[index * 2] || (index * 2 + 1 <= nums.length -1  && (nums[index] < nums[index * 2 + 1] )))) {
            if (nums[index * 2] >= nums[index * 2 + 1]) {
                swap(index, index * 2, nums);
                index = index * 2;
            } else {
                swap(index, index * 2 + 1, nums);
                index = index * 2 + 1;
            }
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
//        int[] ints = sort.mergeSort(a, 0, a.length - 1);
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(ints[i]);
//        }
        //sort.rec(10);

        int[] b = {0, 1, 3, 5, 4, 8, 7, 6, 2};
        sort.heapSort(b);

        sort.del(b);
        sort.del(b);
        sort.del(b);
        for (int i = 1; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }


    void rec(int n) {
        //为了区分这两个递归，分别为它们取个别名好了
        if (n>0){
            rec(n - 1);//rec1
            rec(n - 10);//rec2
        }
        System.out.println("n =" + n);
        System.out.println("最后一句了");
    }
}
