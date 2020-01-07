package struceure.bst;


import java.util.ArrayList;

/**
 * @author zhuwenwen
 * @date 2019/2/15 11:38
 * <p>
 * 二叉搜索树
 */
public class BinarySearchTree {

    private BstNode root;

    private ArrayList<BstNode> bstNodes = new ArrayList<>();

    /**
     * 向二叉搜索树中插入一个节点
     *
     * @param bstNode 二叉搜索树的节点
     */
    public void insertBst(BstNode bstNode) {
        if (root == null) {
            root = bstNode;
            return;
        }
        BstNode current = root;
        while (true) {
            if (bstNode.value >= current.value) {
                if (current.rchild == null) {
                    current.rchild = bstNode;
                    return;
                }
                current = current.rchild;
            } else {
                if (current.lchild == null) {
                    current.lchild = bstNode;
                    return;
                }
                current = current.lchild;
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param batNode 二叉搜素树的根节点
     */
    public void preOrder(BstNode batNode) {
        if (batNode == null) {
            return;
        }
        System.out.print(batNode.getValue() + " ");
        preOrder(batNode.lchild);
        preOrder(batNode.rchild);
    }

    /**
     * 判断这个数组是不是某个二叉搜索树的后序遍历
     * 解题思路:递归判断
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean isAfterOrder(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        if (array.length < 3) {
            return true;
        }
        return checkIsAfterOrder(array, 0, array.length - 1);
    }

    public static boolean checkIsAfterOrder(int[] array, int low, int high) {
        if (high - low <= 1) {
            return true;
        }
        int currentIndex = low;
        int rootVal = array[high];
        //先找出第一个比根节点大的数值
        while (currentIndex < high && array[currentIndex] <= rootVal) {
            currentIndex++;
        }
        //然后向后遍历，如果有小于的话，就说明不是二叉搜索树
        for (int i = currentIndex + 1; i < high; i++) {
            if (array[i] <= rootVal) {
                return false;
            }
        }
        //递归遍历左右子数组
        return checkIsAfterOrder(array, low, currentIndex - 1) && checkIsAfterOrder(array, currentIndex, high - 1);
    }

    /**
     * 求二叉搜索树的深度
     *
     * @param root 根节点
     * @return 二叉搜索树的深度
     */
    public int bstTreeDepth(BstNode root) {
        return root == null ? 0 : 1 + Math.max(bstTreeDepth(root.lchild), bstTreeDepth(root.rchild));
    }

    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。
     * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
     *
     * @param pRoot 根节点
     * @param k     第k个节点
     * @return 节点
     */
    public BstNode kthNode(BstNode pRoot, int k) {
        if (k <= 0) {
            return null;
        }
        ArrayList<BstNode> bstNodes = inOrder(pRoot);
        if (k - 1 >= bstNodes.size()) {
            return null;
        }
        return bstNodes.get(k - 1);
    }

    /**
     * 二叉搜索树的中序遍历,二叉搜索树的中序遍历是递增的
     *
     * @param pRoot 二叉搜索树的根节点
     * @return 二叉搜索树的中序遍历列表
     */
    public ArrayList<BstNode> inOrder(BstNode pRoot) {
        if (pRoot == null) {
            return bstNodes;
        }
        inOrder(pRoot.lchild);
        bstNodes.add(pRoot);
        inOrder(pRoot.rchild);
        return bstNodes;
    }

    public static void main(String[] args) {
//        BinarySearchTree tree = new BinarySearchTree();
//        tree.insertBst(new BstNode(8));
//        tree.insertBst(new BstNode(6));
//        tree.insertBst(new BstNode(9));
//        tree.insertBst(new BstNode(7));
//        tree.insertBst(new BstNode(1));
//        ArrayList<BstNode> bstNodes = tree.inOrder(tree.root);
//        bstNodes.forEach(bstNode -> {
//            System.out.println(bstNode.value);
//        });
        int[] array = new int[]{1, 2, 4, 3, 8, 10, 9, 5};
        boolean afterOrder = isAfterOrder(array);
        System.out.println(afterOrder);

    }

    static class BstNode {
        private Integer value;

        private BstNode lchild;

        private BstNode rchild;

        public BstNode(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public BstNode getLchild() {
            return lchild;
        }

        public void setLchild(BstNode lchild) {
            this.lchild = lchild;
        }

        public BstNode getRchild() {
            return rchild;
        }

        public void setRchild(BstNode rchild) {
            this.rchild = rchild;
        }
    }
}
