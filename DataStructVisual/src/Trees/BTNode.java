package Trees;

class BTNode<E> {
    private E data;
    private BTNode<E> left;
    private BTNode<E> right;

    public BTNode(E initData, BTNode<E> initLeft, BTNode<E> initRight) {
        data = initData;
        left = initLeft;
        right = initRight;
    }
    public BTNode<E> removeLeftmost( )
    {
        if (left == null)
        { // The leftmost node is at the root because there is no left child.
            return right;
        }
        else
        { // A recursive call removes the leftmost node from my left subtree.
            left = left.removeLeftmost( );
            return this;
        }
    }

    public E getData() {
        return data;
    }

    public BTNode<E> getLeft() {
        return left;
    }

    public BTNode<E> getRight() {
        return right;
    }

    public void setData(E newData) {
        data = newData;
    }

    public void setLeft(BTNode<E> newLeft) {
        left = newLeft;
    }

    public void setRight(BTNode<E> newRight) {
        right = newRight;
    }

    public void print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(data);
        if (left != null)
            left.print(depth + 1);
        else if (right != null) {
            for (int i = 0; i < (depth + 1); i++) {
                System.out.print("    ");
            }
            System.out.println("--");
        }
        if (right != null)
            right.print(depth + 1);
        else if (left != null) {
            for (int i = 0; i < (depth + 1); i++) {
                System.out.print("    ");
            }
            System.out.println("--");
        }
    }

    public static <E> int treeSize(BTNode<E> root) {
        if (root == null)
            return 0; //no nodes
        else
            return 1 + treeSize(root.left) + treeSize(root.right);
    }

}
