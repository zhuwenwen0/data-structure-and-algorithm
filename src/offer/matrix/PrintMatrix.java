package offer.matrix;

import java.util.ArrayList;

/**
 * 打印顺时针矩阵
 *
 * @author zhuwenwen
 * @date 17:46 12-11-2019
 **/
public class PrintMatrix {


    /**
     * s形打印矩阵，比如给定一个矩阵4x4矩阵{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}
     * 打印出{1,2,3,4,8,7,6,5,9,10,11,12,16,15,14,13}
     *
     * @param matrix 矩阵
     */
    public ArrayList<Integer> print(int[][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (matrix == null || matrix.length < 0 || matrix[0].length < 0) {
            return arrayList;
        }
        for (int i = 0; i < matrix.length; i++) {
            //如果行数是偶数的话,代表从头开始打印,否则,从尾部开始打印
            if (i % 2 == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    arrayList.add(matrix[i][j]);
                }
            } else {
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    arrayList.add(matrix[i][j]);
                }
            }
        }
        return arrayList;
    }

    /**
     * 顺时针打印矩阵 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     * @param matrix matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        return arrayList;
    }

    public static void main(String[] args) {
        PrintMatrix printMatrix = new PrintMatrix();
        int[][] array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        ArrayList<Integer> print = printMatrix.print(array);
        print.forEach(c -> {
            System.out.println(c);
        });
    }
}
