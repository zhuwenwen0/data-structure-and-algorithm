package algorithm.sort;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用数组进行堆排序,大顶堆
 *
 * @author zhuwenwen
 * @date 14:43 03-07-2020
 **/
public class HeapSort {

    /**
     * 堆排序下标从1开始,下标为0的不使用
     */
    private static int[] a = new int[11];

    /**
     * 数组a使用的游标的下标位置
     */
    private static AtomicInteger course = new AtomicInteger(0);


    public static void insert(int value) {
        if (course.getAndIncrement() > 10) {
            return;
        }
        a[course.get()] = value;
        //进行上浮
        swim(course.get());
    }

    public static int del() {
        int top = a[1];
        a[1] = a[course.get()];
        //如果是对象的话,需要令最后一个元素为空，方便垃圾回收，要不然就会存在数组的引用,导致垃圾不能对这个对象进行回收
        a[course.get()] = 0;
        //数组存储的下标减一
        course.getAndDecrement();
        //进行下沉,维护堆的性质
        sink(1);
        return top;
    }

    /**
     * 上浮
     *
     * @param index 下标位置
     */
    public static void swim(int index) {
        //如果子节点比其父节点大,并且index不能是头结点
        while (a[index] > a[index / 2] && index != 1) {
            swap(index, index / 2);
            index = index / 2;
        }
    }

    /**
     * 下沉位置
     *
     * @param index
     */
    public static void sink(int index) {
        while (index * 2 <= course.get() && (a[index] < a[index * 2] || (index * 2 + 1 <= course.get() && (a[index] < a[index * 2 + 1] )))) {
            if (a[index * 2] >= a[index * 2 + 1]) {
                swap(index, index * 2);
                index = index * 2;
            } else {
                swap(index, index * 2 + 1);
                index = index * 2 + 1;
            }
        }
    }

    public static void swap(int index1, int index2) {
        if (index1 <= 0 || index2 <= 0) {
            return;
        }
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public static void main(String[] args) {
        insert(1);
        insert(3);
        insert(5);
        insert(4);
        insert(8);
        insert(7);
        insert(6);
        insert(2);

        del();
        del();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
