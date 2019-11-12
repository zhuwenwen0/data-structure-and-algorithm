package offer.matrix;

/**
 * 打印顺时针矩阵
 *
 * @author zhuwenwen
 * @date 17:46 12-11-2019
 **/
public class PrintMatrix {


    /**
     * 打印顺时针矩阵，比如给定一个矩阵4x4矩阵{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}
     * 打印出{1,2,3,4,8,7,6,5,9,10,11,12,16,15,14,13}
     *
     * @param array 矩阵
     */
    public void print(int[][] array) {
        if (array == null || array.length < 0 || array[0].length < 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            //如果行数是偶数的话,代表从头开始打印,否则,从尾部开始打印
            if (i % 2 == 0) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.println(array[i][j]);
                }
            } else {
                for (int j = array[i].length - 1; j >= 0; j--) {
                    System.out.println(array[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintMatrix printMatrix = new PrintMatrix();
        int[][] array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix.print(array);
    }
}
