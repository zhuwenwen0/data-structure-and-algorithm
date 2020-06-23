package algorithm.matrix;


import java.util.Stack;

/**
 * @author zhuwenwen
 * @date 2019/2/13 15:05
 * <p>
 * 矩阵，深度遍历
 */
public class Matrix {

    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;


    //-------------------------------------------------------------------------------------------------------------------------------

    /**
     * 第一种方式,递归的方法   题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始
     * ，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
     * a b c e
     * ​s f c s
     * a d e e
     * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     *
     * @param array
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtracking(matrix, str, marked, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtracking(char[][] matrix, char[] str,
                                 boolean[][] marked, int pathLen, int r, int c) {

        if (pathLen == str.length) {
            return true;
        }
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || matrix[r][c] != str[pathLen] || marked[r][c]) {

            return false;
        }
        marked[r][c] = true;
        for (int[] n : next) {
            if (backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    //第二种解法,使用栈实现深度遍历----------------------------

    public boolean hasPath2(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //如果第一个元素匹配的话，就通过栈判断其是否含有此路径
                if (matrix[i][j] == str[0]) {
                    //如果包含此路径的话,就直接返回true,否则返回false
                    if (backtrackingWithStock(matrix, str, marked, i, j)) {
                        return true;
                    }
                    continue;
                }
            }
        }

        return false;
    }

    /**
     * 判断矩阵中的一个符合字符串首字符的节点是否包含字符串的路径
     *
     * @param matrix 矩阵
     * @param str    字符串路径数组
     * @param marked 标记矩阵
     * @param r      起始地址的行
     * @param c      起始地址的列
     * @return 是否包含
     */
    private boolean backtrackingWithStock(char[][] matrix, char[] str,
                                          boolean[][] marked, int r, int c) {
        Stack<MatrixPosition> stack = new Stack<>();
        //进行标记
        marked[r][c] = true;
        stack.push(new MatrixPosition(r, c));
        while (!stack.isEmpty()) {
            for (int i = stack.size(); i < str.length; i++) {
                //判断起始点的的后一个位置是否包含对应位置的字符
                boolean add = false;
                loop1:
                for (int j = 0; j < next.length; j++) {
                    //弹出栈顶元素，但是元素不出栈
                    MatrixPosition peek = stack.peek();
                    int nextX = peek.getX() + next[j][0];
                    int nextY = peek.getY() + next[j][1];
                    //限制下一步的行进范围在矩阵中
                    if (nextX >= matrix.length || nextY >= matrix[0].length || nextX < 0 || nextY < 0 || marked[nextX][nextY]) {
                        continue;
                    }
                    //如果是符合字符串结果就进栈
                    if (matrix[nextX][nextY] == str[i]) {
                        stack.push(new MatrixPosition(nextX, nextY));
                        //标记已访问
                        marked[nextX][nextY] = true;
                        add = true;
                        //如果是字符串的最后一个字符找到的话，就返回true
                        if (i == str.length - 1) {
                            return true;
                        }
                        //退出循环
                        break loop1;
                    }
                }

                if (!add) {
                    //如果进行了四步都没有包含字符串所需的字符的话,就回溯上一步
                    if (stack.size() == 1) {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = array[idx++];
            }
        }
        return matrix;
    }

    //---------------------------------------------------------------------------------------------------------------------------

    /**
     * 机器人的运动范围,地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
     * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
     * 因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     * @param threshold 阈值
     * @param rows      行
     * @param cols      列
     * @return 机器人可以运动的最大范围
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold <= 0) {
            return 0;
        }
        int[][] matrix = new int[rows][cols];
        int count = 0;
        java.util.Stack<MatrixPosition> stack = new java.util.Stack<>();
        stack.push(new MatrixPosition(0, 0));
        //标记已访问
        count++;
        matrix[0][0] = 1;
        while (!stack.isEmpty()) {
            //对栈里元素进行遍历,出栈，并且删除栈内元素
            MatrixPosition pop = stack.pop();
            //对栈内的机器人进行一次运动,分为上下左右
            for (int i = 0; i < next.length; i++) {
                int nextX = pop.getX() + next[i][0];
                int nextY = pop.getY() + next[i][1];
                //规定机器人的最大运动距离
                if (nextX < 0 || nextY < 0 || nextX >= rows || nextY >= cols) {
                    continue;
                }
                if (countTheNum(nextX) + countTheNum(nextY) <= threshold && matrix[nextX][nextY] != 1) {
                    //添加到栈内
                    stack.push(new MatrixPosition(nextX, nextY));
                    //标记已访问
                    matrix[nextX][nextY] = 1;
                    //范围+1
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 计算一个数的个位相加  如  124 = 7
     *
     * @param x
     * @return
     */
    private int countTheNum(int x) {
        int sum = 0;
        do {
            sum += x % 10;

        } while ((x = x / 10) > 0);


        return sum;
    }

    class MatrixPosition {
        private int x;

        private int y;

        public MatrixPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        char[] chars = {'a','b','c','e','s','f','c','s','a','d','e','e'};
        char[] path = {'b','c','c','e','d'};
        char[] path2 = {'a','b','c','d'};
        boolean b = matrix.hasPath2(chars, 3, 4, path);
        boolean c = matrix.hasPath2(chars, 3, 4, path2);
        System.out.println(b);
        System.out.println(c);
    }
}
