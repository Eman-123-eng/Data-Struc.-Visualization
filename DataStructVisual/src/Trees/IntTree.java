package Trees;

public class IntTree {
    protected BTNode<Integer> root;

    public IntTree() {
    }

    public IntTree(int data) {
        root = new BTNode<>(data, null, null);
    }

    public void add(int element) { //done with root must be initialized in constructor
        recAdd(element, root);
    }

    private BTNode<Integer> recAdd(int element, BTNode<Integer> start) {
        if (start == null) {
            start = new BTNode<>(element, null, null);
            if (root == null)
                root = start;
        } else if (((Comparable<Integer>) element).compareTo(start.getData()) > 0)
            start.setRight(recAdd(element, start.getRight()));
        else
            start.setLeft(recAdd(element, start.getLeft()));
        return start;
    }

    public boolean remove(int target) {
        BTNode<Integer> targetNode = removeRec(root, target);
        return (targetNode != null);
    }

    private BTNode<Integer> removeRec(BTNode<Integer> node, int target) {
        if (node == null)
            return null;
        if (((Comparable<Integer>) target).compareTo(node.getData()) > 0)
            node.setRight(removeRec(node.getRight(), target));
        else if (((Comparable<Integer>) target).compareTo(node.getData()) < 0)
            node.setLeft(removeRec(node.getLeft(), target));
        else {// if key is same as node's key, then This is the node to be deleted
            // node with only one child or no child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null)
                return node.getLeft();
            else {
                // node with two children: Get the inorder successor (smallest in the right subtree)
                node.setData(minValue(node.getRight()));
                // Delete the inorder successor
                node.setRight(removeRec(node.getRight(), node.getData()));
            }
        }
        return node;
    }

    public boolean search(BTNode<Integer> node, int key) {
        if (node == null) return false;
        if (node.getData() == key) return true;
        else if ((((Comparable<Integer>) key).compareTo(node.getData())) < 0) return search(node.getLeft(), key);
        else return search(node.getRight(), key);
    }

    public int size() { //done
        return BTNode.treeSize(root);
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int getRoot(){
        return root.getData();
    }

    private int minValue(BTNode<Integer> root) {
        int minVal = root.getData();
        while (root.getLeft() != null) {
            minVal = root.getLeft().getData();
            root = root.getLeft();
        }
        return minVal;
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.print(0);
    }
    public void clearTree(){ root=null;}


}

