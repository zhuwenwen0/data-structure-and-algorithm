package struceure.avt;

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
     * 右旋,左左旋转
     *
     *              7                                   5
     *       5                     转换为         4               7
     *   4      6                                             6
     *
     * @param adaptiveNode 不平衡节点
     */
    public void rightRetenion(AvTreeNode adaptiveNode) {
        if (adaptiveNode != null) {
            AvTreeNode parentNode = adaptiveNode.parent;
            AvTreeNode newRootNode;
            //1. 把不平衡的节点的左子树替换成不平衡节点的位置
            if (parentNode == null) {
                //如果是当前树的根节点需要旋转
                newRootNode = adaptiveNode.lchild;
                root = newRootNode;
                //给新根节点赋值父节点
                newRootNode.parent = null;
            } else {
                newRootNode = adaptiveNode.lchild;
                //需要先判断不平衡节点处在父节点的什么位置
                if (parentNode.lchild == adaptiveNode) {
                    parentNode.lchild = newRootNode;
                } else {
                    parentNode.rchild = newRootNode;
                }
                //给新根节点赋值父节点
                newRootNode.parent = parentNode;
            }
            // 2.如果旋转后的根节点有右子树的话
            AvTreeNode rchild = newRootNode.rchild;
            //设置父节点
            adaptiveNode.lchild = rchild;
            if (rchild != null) {
                rchild.parent = newRootNode.rchild;
            }

            //3. 把不平衡的节点赋给新节点的右孩子
            newRootNode.rchild = adaptiveNode;
            //设置不平衡的节点的父节点为新的根节点
            adaptiveNode.parent = newRootNode;
        }
    }

    /**
     * 左旋,右右旋转
     *
     * @param adaptiveNode 不平衡节点
     */
    public void leftRetenion(AvTreeNode adaptiveNode) {
        if (adaptiveNode != null) {
            AvTreeNode parentNode = adaptiveNode.parent;
            //1. 把不平衡的节点的左子树替换成不平衡节点的位置
            AvTreeNode newRootNode;
            if (parentNode == null) {
                //如果是当前树的根节点需要旋转
                newRootNode = adaptiveNode.rchild;
                root = newRootNode;
                //给新根节点赋值父节点
                newRootNode.parent = null;
            } else {
                newRootNode = adaptiveNode.rchild;
                //需要先判断不平衡节点处在父节点的什么位置
                if(parentNode.rchild == adaptiveNode) {
                    parentNode.rchild = newRootNode;
                } else {
                    parentNode.lchild = newRootNode;
                }
                //给新根节点赋值父节点
                newRootNode.parent = parentNode;
            }

            // 2. 如果旋转后的根节点有左子树的话
            AvTreeNode lchild = newRootNode.lchild;
            //设置父节点
            adaptiveNode.rchild = lchild;
            if (lchild != null) {
                lchild.parent = newRootNode.lchild;
            }

            //3. 把不平衡的节点赋给新节点的右孩子
            newRootNode.lchild = adaptiveNode;
            //设置不平衡的节点的父节点为新的根节点
            adaptiveNode.parent = newRootNode;
        }
    }

    private void reBuild(AvTreeNode avTreeNode) {
        while (avTreeNode != null) {
            int lHigh = getHigh(avTreeNode.lchild);
            int rHigh = getHigh(avTreeNode.rchild);
            if (lHigh - rHigh == 2) {
                //代表左边的高度比右边的高度大2,此时代表应该右旋,至于是直接右旋还是需要先左旋再右旋还需要再一步判断
                AvTreeNode temp = avTreeNode.lchild;
                if (temp.rchild != null) {
                    //代表的是LR型，需要先左旋，再右旋
                    leftRetenion(temp);
                    rightRetenion(avTreeNode);
                } else if (temp.lchild != null) {
                    //代表的是LL型
                    rightRetenion(avTreeNode);
                }

            } else if (lHigh - rHigh == -2) {
                //代表右边的高度比左边的高度大2,此时代表应该左旋,至于是直接左旋还是需要先右旋再左旋还需要再一步判断
                AvTreeNode temp = avTreeNode.rchild;
                if (temp.lchild != null) {
                    //代表的是RL型，需要先右旋，再左旋
                    rightRetenion(temp);
                    leftRetenion(avTreeNode);
                } else if (temp.rchild != null) {
                    //代表的是LL型
                    leftRetenion(avTreeNode);
                }
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
        avTree.insert(11);
        avTree.insert(7);
        avTree.insert(15);
        avTree.insert(6);
        avTree.insert(9);
        avTree.insert(10);
        ArrayList<AvTreeNode> avTreeNodes = avTree.inOrder(avTree.root);
        for (AvTreeNode avTreeNode : avTreeNodes) {
            System.out.println(avTreeNode.value);
        }
        System.out.println("根节点：" + avTree.root.value);
        //System.out.println(avTree.getHigh(avTree.getRoot()));
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
