package struceure.datastruct;


import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author zhuwenwen
 * @date 2019/2/1 10:23
 */
public class BinaryTree {

    private TreeNode root;
    /**
     * 用来存放中序遍历的下标值
     */
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

//    /**
//     * 根据前序和中序重建二叉树
//     *
//     * @param pre 前序
//     * @param in 中序
//     */
//    public TreeNode reConstructBinaryTree(int[] pre,int[] in){
//        if (pre.length == 0 || in.length == 0 || pre.length != in.length){
//            return null;
//        }
//        return reConstructBinaryTree(pre,in,0,pre.length-1,0,in.length-1);
//    }
//    private TreeNode reConstructBinaryTree(int[] pre,int[] in,int preStart,int preEnd,int inStart,int inEnd){
//        if (preStart>preEnd){
//            return null;
//        }
//        int spilt=0;
//        //每次递归的时候，preStart都会变，所以spilt也会变,所以会出错
//        for (int i = 0; i <= inEnd ; i++) {
//            if (pre[preStart] == in[i]){
//                spilt=i;
//            }
//        }
//        TreeNode root=new TreeNode(pre[preStart]);
//        root.lchild=reConstructBinaryTree(pre,in,preStart+1,spilt,inStart,spilt-1);
//        root.rchild=reConstructBinaryTree(pre,in,spilt+1,preEnd,spilt+1,inEnd);
//        return root;
//    }

    /**
     * 根据前序和中序重建二叉树
     * <p>
     * <p>
     * todo 以后重新实现一遍
     *
     * @param pre 前序
     * @param in  中序
     * @return 二叉树的头结点
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //先把中序遍历的值和其下标放到map中去
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        //传入的是前序遍历的左下标、右下标、中序遍历的左下标
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        //取出中序遍历根节点的下标位置
        TreeNode root = new TreeNode(pre[preL]);
        Integer inIndex = indexForInOrders.get(pre[preL]);
        //取出左子树的长度
        int leftTreeSize = inIndex - inL;
        root.lchild = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.rchild = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    /**
     * 前序遍历
     *
     * @param treeNode 二叉树的头结点
     */
    public void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.getValue() + " ");
        preOrder(treeNode.lchild);
        preOrder(treeNode.rchild);
    }

    /**
     * 中序遍历
     *
     * @param treeNode 二叉树的头结点
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.lchild);
        System.out.print(treeNode.getValue() + " ");
        inOrder(treeNode.rchild);
    }

    /**
     * 后续遍历
     *
     * @param treeNode 根节点
     */
    public void afterOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        afterOrder(treeNode.lchild);
        afterOrder(treeNode.rchild);
        System.out.print(treeNode.getValue() + " ");
    }

    /**
     * 层次遍历
     *
     * @param treeNode 根节点
     */
    public void cengOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        //每一层都是先进先出
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(treeNode);
        while (!linkedList.isEmpty()) {
            //把链表中的节点移除
            TreeNode remove = linkedList.remove();
            System.out.print(remove.getValue() + " ");
            if (remove.lchild != null) {
                linkedList.add(remove.lchild);
            }
            if (remove.rchild != null) {
                linkedList.add(remove.rchild);
            }
        }
    }

    /**
     * 从上到下按层打印二叉树
     *
     * @param treeNode 根节点
     */
    public ArrayList<ArrayList<Integer>> print(TreeNode treeNode) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        //每一层都是先进先出
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(treeNode);
        while (!linkedList.isEmpty()) {
            //把每一层的节点放进list中去
            ArrayList<Integer> list = new ArrayList<>();
            int size = linkedList.size();
            //先把本层的节点个数计算出来，然后进行出队列，保证只出本层的元素
            while (size-- > 0) {
                TreeNode node = linkedList.remove();
                if (node == null) {
                    continue;
                }
                list.add(node.getValue());
                linkedList.add(node.lchild);
                linkedList.add(node.rchild);
            }
            if (list.size() > 0) {
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 层序遍历，按之字形打印二叉树
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> printByZ(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.value);
                queue.add(node.lchild);
                queue.add(node.rchild);
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 二叉树中序遍历的下一节点
     *
     * @param treeNode 二叉树的头结点
     * @return
     */
    public TreeNode getNext(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        //如果当前节点有右子树的话，那么找到右子树的最左节点
        if (treeNode.rchild != null) {
            TreeNode temp = treeNode.rchild;
            while (temp.lchild != null) {
                temp = temp.lchild;
            }
            return temp;
        }
        //如果当前节点没有右子树的话,需要先判断当前节点是不是最后一个节点,如果不是最后一个节点，那么找他的上一级
        while (treeNode.next != null) {
            TreeNode parentNode = treeNode.next;
            if (parentNode.lchild == treeNode) {
                return parentNode;
            }
            treeNode = treeNode.next;
        }
        return null;
    }

    /**
     * 判断一个二叉树是不是另一个二叉树的子树
     *
     * @param root1 二叉树1
     * @param root2 二叉树2
     * @return boolean
     */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        //递归进行判断root2是不是root1的子树
        return isSubTree(root1, root2) || hasSubtree(root1.lchild, root2) || hasSubtree(root1.rchild, root2);
    }

    /**
     * 用来比较两棵树是否相同
     *
     * @param root1 第一棵树的根节点
     * @param root2 第二颗树的根节点
     * @return boolean
     */
    private boolean isSubTree(TreeNode root1, TreeNode root2) {
        //必须先判断root2,否则会出现错误
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        //先比较两棵树的根节点是否相同
        if (root1.value != root2.value) {
            return false;
        }
        //在比较两棵树的左右节点是否都相同
        return isSubTree(root1.lchild, root2.lchild) && isSubTree(root1.rchild, root2.rchild);
    }

    /**
     * 二叉树的镜像，把二叉树里所有的左右节点交换
     */
    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        swap(root);
        mirror(root.lchild);
        mirror(root.rchild);
    }

    private void swap(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.lchild;
        root.lchild = root.rchild;
        root.rchild = temp;
    }

    /**
     * 判断二叉树是否左右对称
     *
     * @param root 二叉树的根节点
     * @return boolean
     */
    public boolean isSymmetrical(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetrical(root.lchild, root.rchild);
    }

    private boolean isSymmetrical(TreeNode lchild, TreeNode rchild) {
        if (lchild == null && rchild == null) {
            return true;
        }
        if (lchild == null || rchild == null) {
            return false;
        }
        if (lchild.value != rchild.value) {
            return false;
        }
        return isSymmetrical(lchild.lchild, rchild.rchild) && isSymmetrical(lchild.rchild, rchild.lchild);
    }

    /**
     * 判断一个二叉树是不是平衡二叉搜索树
     *
     * @param root 二叉树的根节点
     * @return boolean
     */
    public boolean isBalanceTree(TreeNode root) {
        //如果根节点为null的话，则返回true
        if (root == null) {
            return true;
        }
        return isBalanceTree(root.lchild) && isBalanceTree(root.rchild)
                && Math.abs(binaryTreeDepth(root.lchild) - binaryTreeDepth(root.rchild)) <= 1;
    }

    /**
     * 求二叉树的深度
     *
     * @param root 二叉树的根节点
     * @return 二叉树的深度
     */
    public int binaryTreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(binaryTreeDepth(root.lchild), binaryTreeDepth(root.rchild));
    }

    /**
     * 获取二叉树中路径之和为num的所有路径,
     *
     * @param target target
     * @return 路径的列表
     */
    public ArrayList<ArrayList<Integer>> getPathSumOfNum(TreeNode root, Integer target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (target == null) {
            return list;
        }
        findPath(root, target, new ArrayList<>(), list);
        list.sort(Comparator.comparing(fun -> fun.size()));
        Collections.reverse(list);
        return list;
    }

    public void findPath(TreeNode node, Integer target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> list) {
        if (node == null) {
            return;
        }
        path.add(node.value);
        target = target - node.value;
        if (target == 0 && node.lchild == null && node.rchild == null) {
            list.add(new ArrayList<>(path));
        } else {
            findPath(node.lchild, target, path, list);
            findPath(node.rchild, target, path, list);
        }
        path.remove(path.size() - 1);
    }




    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        int[] pre = {1, 2, 4, 5, 3, 4, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
//        int[] in = {9,8,4,2,7,10,11};
//        int[] pre = {10,8,9,2,4,7,11};
        TreeNode treeNode = binaryTree.reConstructBinaryTree(pre, in);
////
//        int[] pre1 = {8,9,2};
//        int[] in1 = {9,8,2};
//        TreeNode treeNode2 = binaryTree.reConstructBinaryTree(pre1, in1);
        ArrayList<ArrayList<Integer>> pathSumOfNum = binaryTree.getPathSumOfNum(treeNode, 8);
        for (ArrayList<Integer> integers : pathSumOfNum) {
            for (int i = 0; i < integers.size(); i++) {
                System.out.println(integers.get(i));
            }
        }
//        System.out.println("--------二叉树的深度-------");
//        System.out.println(binaryTree.binaryTreeDepth(treeNode));
//        System.out.println("------前序遍历---------");
//        binaryTree.preOrder(treeNode);
//        System.out.println(" ");
//        System.out.println("------中序遍历---------");
//        binaryTree.inOrder(treeNode);
//        System.out.println(" ");
//        System.out.println("--------后序遍历-----------");
//        binaryTree.afterOrder(treeNode);
//        System.out.println(" ");
//        System.out.println("--------层次遍历-----------");
//        binaryTree.cengOrder(treeNode);
//        System.out.println("----------层次遍历，打印每一层----------");
//        ArrayList<ArrayList<Integer>> print = binaryTree.print(treeNode);
//        print.stream().forEach(list -> {
//            System.out.println("---层分级----");
//            list.stream().forEach(System.out::print);
//        });
    }


    static class TreeNode {
        private int value;

        private TreeNode lchild;

        private TreeNode rchild;

        private TreeNode next;

        public TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLchild() {
            return lchild;
        }

        public void setLchild(TreeNode lchild) {
            this.lchild = lchild;
        }

        public TreeNode getRchild() {
            return rchild;
        }

        public void setRchild(TreeNode rchild) {
            this.rchild = rchild;
        }

        public TreeNode getNext() {
            return next;
        }

        public void setNext(TreeNode next) {
            this.next = next;
        }
    }


}
