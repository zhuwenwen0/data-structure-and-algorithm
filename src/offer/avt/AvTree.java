package offer.avt;

import java.util.ArrayList;

/**
 * 平衡二叉树的实现
 *
 * @author zhuwenwen
 * @date 2019/11/23 21:03
 **/
public class AvTree {

    private AvTreeNode root;

    private Integer high;

    private ArrayList<AvTree.AvTreeNode> avTreeNodes = new ArrayList<>();

    /**
     * 递归构建二叉搜索树
     *
     * @param value 节点的值
     */
    public void insert(Integer value) {
        if (value == null) {
            return;
        }
        AvTreeNode insertNode = new AvTreeNode(value);
        if (root == null) {
            root = insertNode;
            return;
        }
        AvTreeNode adaptAvtNode = getAdaptAvtNode(root, insertNode);
        insertNode.parent = adaptAvtNode;
        if (value > adaptAvtNode.value) {
            adaptAvtNode.rchild = insertNode;
        } else {
            adaptAvtNode.lchild = insertNode;
        }
        //重新构建平衡二叉树
        reBuild(adaptAvtNode);
    }

    /**
     * 左旋
     *
     * @param avTreeNode 不平衡节点
     */
    public void leftRetenion(AvTreeNode avTreeNode) {
        if (avTreeNode != null) {

        }
    }

    /**
     * 右旋
     *
     * @param avTreeNode 不平衡节点
     */
    public void rightRetenion(AvTreeNode avTreeNode) {
        if (avTreeNode != null) {

        }
    }

    private void reBuild(AvTreeNode avTreeNode) {
        while (avTreeNode != null) {
            int lHigh = getHigh(avTreeNode.lchild);
            int rHigh = getHigh(avTreeNode.rchild);
            if (lHigh - rHigh == 2) {
                //代表左边的高度比右边的高度大2,此时代表应该右旋,至于是直接右旋还是需要先左旋再右旋还需要再一步判断
                //todo
            } else if (lHigh - rHigh == -2) {
                //代表右边的高度比左边的高度大2,此时代表应该左旋,至于是直接左旋还是需要先右旋再左旋还需要再一步判断
                //todo
            }
            //向上回溯,找出不平衡的点进行调整
            avTreeNode = avTreeNode.parent;
        }
    }



    /**
     * 判断以avTreeNode为根节点的二叉搜索树是否平衡,如果平衡则返回true,不平衡则返回false
     *
     * @param avTreeNode 二叉搜索树的节点
     * @return boolean
     */
    public boolean isBalance(AvTreeNode avTreeNode) {
        if (avTreeNode == null) {
            return true;
        }
        int lHigh = getHigh(avTreeNode.lchild);
        int rHigh = getHigh(avTreeNode.rchild);
        //如果节点的左右高度差大于1,那么说明此节点是不平衡的节点
        if (Math.abs(lHigh - rHigh) > 1) {
            return false;
        } else {
            boolean lBalance = isBalance(avTreeNode.lchild);
            boolean rBalance = isBalance(avTreeNode.rchild);
            if (!lBalance) {
                return false;
            }
            if (!rBalance) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取二叉树的深度
     *
     * @param avTreeNode 二叉搜索树的节点
     * @return 深度
     */
    public int getHigh(AvTreeNode avTreeNode) {
        if (avTreeNode == null) {
            return 0;
        }
        int lHigh = getHigh(avTreeNode.lchild);
        int rHigh = getHigh(avTreeNode.rchild);
        return ((lHigh >= rHigh) ? lHigh : rHigh) + 1;
    }

    /**
     * 寻找应该插入的节点的位置
     *
     * @param avTreeNode 开始的位置
     * @return 应该插入的位置
     */
    private AvTreeNode getAdaptAvtNode(AvTreeNode avTreeNode, AvTreeNode insertNode) {
        if (avTreeNode == null || insertNode == null) {
            throw new IllegalArgumentException("IllegalArgument");
        }
        if (insertNode.value > avTreeNode.value) {
            if (avTreeNode.rchild == null) {
                return avTreeNode;
            }
            return getAdaptAvtNode(avTreeNode.rchild, insertNode);
        } else {
            if (avTreeNode.lchild == null) {
                return avTreeNode;
            }
            return getAdaptAvtNode(avTreeNode.lchild, insertNode);
        }
    }

    /**
     * 二叉搜索树的中序遍历,二叉搜索树的中序遍历是递增的
     *
     * @param pRoot 二叉搜索树的根节点
     * @return 二叉搜索树的中序遍历列表
     */
    public ArrayList<AvTreeNode> inOrder(AvTreeNode pRoot) {
        if (pRoot == null) {
            return avTreeNodes;
        }
        inOrder(pRoot.lchild);
        avTreeNodes.add(pRoot);
        inOrder(pRoot.rchild);
        return avTreeNodes;
    }


    public static void main(String[] args) {
        AvTree avTree = new AvTree();
        avTree.insert(5);
        avTree.insert(4);
        avTree.insert(7);
        avTree.insert(9);
        avTree.insert(3);
        avTree.insert(1);
        avTree.insert(10);
        ArrayList<AvTreeNode> avTreeNodes = avTree.inOrder(avTree.root);
        for (AvTreeNode avTreeNode : avTreeNodes) {
            System.out.println(avTreeNode.value);
        }
        System.out.println(avTree.getHigh(avTree.getRoot()));
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public AvTreeNode getRoot() {
        return root;
    }

    public void setRoot(AvTreeNode root) {
        this.root = root;
    }

    static class AvTreeNode {

        private Integer value;

        private AvTreeNode parent;

        private AvTreeNode lchild;

        private AvTreeNode rchild;

        public AvTreeNode(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public AvTreeNode getParent() {
            return parent;
        }

        public void setParent(AvTreeNode parent) {
            this.parent = parent;
        }

        public AvTreeNode getLchild() {
            return lchild;
        }

        public void setLchild(AvTreeNode lchild) {
            this.lchild = lchild;
        }

        public AvTreeNode getRchild() {
            return rchild;
        }

        public void setRchild(AvTreeNode rchild) {
            this.rchild = rchild;
        }
    }
}
