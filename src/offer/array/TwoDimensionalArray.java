package offer.array;

/**
 * 二维数组中的查找
 *
 * @author zhuwenwen
 * @date 2019/1/31 21:41
 */
public class TwoDimensionalArray {

    public static void main(String[] args) {
        int[][] a={{1,2,3,4,5}, {3,5,7,9,11}, {4,7,9,11,19}};
        System.out.println(serarchInTwoDimensionArray(a,4));
    }

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
     *
     * @param array 二维数组
     * @param target 目标值
     * @return true或者false
     */
    private static Boolean serarchInTwoDimensionArray(int[][] array,int target){
        if (array == null||array.length <= 0 || array[0].length <= 0){
            return false;
        }
        int row=array.length;int column=array[0].length;
        int r=0,c=column-1;
        while (r<= row-1 && c>=0){
            if (array[r][c] ==target){
                return true;
            }else if (array[r][c] > target){
                c--;
            }else {
                r++;
            }
        }

        return false;
    }
}
