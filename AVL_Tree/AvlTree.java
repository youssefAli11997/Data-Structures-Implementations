package AVL_Tree;

public class AvlTree<Type extends Comparable<? super Type>> {

    AvlNode<Type> root;
    static final int ALLOWED_IMBALANCE = 1;

    public AvlTree() {
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty Tree");
        }
        else{
            printTree(root);
        }
    }

    public void insert(Type element) {
        root = insert(element, root);
    }

    public void remove(Type element) {
        root = remove(element, root);
    }

    public Type findMin() {
        if (isEmpty()) {
            return null;
        }
        return findMin(root).element;
    }

    public Type findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMax(root).element;
    }

    public boolean contains(Type element){
        return contains(element, root);
    }

    public void checkBalance(){
        checkBalance(root);
    }

    private AvlNode<Type> remove(Type element, AvlNode<Type> node) {
        if (node == null) {
            return node;
        }

        int compareResult = node.element.compareTo(element);

        if (compareResult > 0) {
            node.left = remove(element, node.left);
        } else if (compareResult < 0) {
            node.right = remove(element, node.right);
        } else if (node.left != null && node.right != null) { // has two children
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else { // has only one child
            node = (node.left == null) ? node.right : node.left;
        }

        return balance(node);
    }

    private AvlNode<Type> findMin(AvlNode<Type> node) {
        if(node == null)
            return node;
        while(node.left != null)
            node = node.left;
        return node;
    }

    private AvlNode<Type> findMax(AvlNode<Type> node) {
        if(node == null)
            return node;
        while(node.right != null)
            node = node.right;
        return node;
    }

    private AvlNode<Type> insert(Type element, AvlNode<Type> node) {
        if (node == null) {
            return new AvlNode<>(element);
        }

        int compareResult = node.element.compareTo(element);

        if (compareResult > 0) {
            node.left = insert(element, node.left);
        } else if (compareResult < 0) {
            node.right = insert(element, node.right);
        } else {
            // Duplicates, do nothing
        }

        return balance(node);
    }

    private boolean contains(Type element, AvlNode<Type> node) {
        if (node == null) {
            return false;
        }

        int compareResult = node.element.compareTo(element);

        if (compareResult > 0) {
            return contains(element, node.left);
        } else if (compareResult < 0) {
            return contains(element, node.right);
        } else {
            return true;
        }
    }

    private AvlNode<Type> balance(AvlNode<Type> node) {
        if(node == null){
            return node;
        }

        if(height(node.left) - height(node.right) > ALLOWED_IMBALANCE)
            if(height(node.left.left) >= height(node.left.right))
                node = rotateWithLeftChild(node);
            else
                node = doubleWithLeftChild(node);
        else
        if(height(node.right) - height(node.left) > ALLOWED_IMBALANCE)
            if(height(node.right.right) >= height(node.right.left))
                node = rotateWithRightChild(node);
            else
                node = doubleWithRightChild(node);

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private int checkBalance(AvlNode<Type> node) {
        if(node == null){
            return -1;
        }

        int hl = checkBalance(node.left);
        int hr = checkBalance(node.right);
        if(Math.abs(height(node.left) - height(node.right)) > 1
                || height(node.left) != hl || height(node.right) != hr)
            System.out.println("OOPS! Unbalanced!");

        return height(node);

    }

    private AvlNode<Type> rotateWithRightChild(AvlNode<Type> root) {
        AvlNode<Type> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(root.height, height(newRoot.right)) + 1;
        return newRoot;
    }

    private AvlNode<Type> rotateWithLeftChild(AvlNode<Type> root) {
        AvlNode<Type> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), root.height) + 1;
        return newRoot;
    }

    private AvlNode<Type> doubleWithRightChild(AvlNode<Type> root) {
        root.right = rotateWithLeftChild(root.right);
        return rotateWithRightChild(root);
    }

    private AvlNode<Type> doubleWithLeftChild(AvlNode<Type> root) {
        root.left = rotateWithRightChild(root.left);
        return rotateWithLeftChild(root);
    }

    private int height(AvlNode<Type> node) {
        return node == null ? -1 : node.height;
    }

    private boolean isEmpty() {
        return root == null;
    }

    private void printTree(AvlNode<Type> node) {
        if(node == null)
            return;
        printTree(node.left);
        System.out.print(node.element + " ");
        printTree(node.right);
    }

}