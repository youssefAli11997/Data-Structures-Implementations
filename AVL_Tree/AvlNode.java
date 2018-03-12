package AVL_Tree;

public class AvlNode<Type> {
    Type element;
    AvlNode<Type> left;
    AvlNode<Type> right;
    int height;

    AvlNode(Type element){
        this(element, null,null);
    }

    public AvlNode(Type element, AvlNode<Type> left, AvlNode<Type> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}
