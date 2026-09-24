package ArbolBinario;

public class Node {
    private int id;
    private Node father, right, left;

    public int getId() {
        return id;
    }
    public Node getFather() {
        return father;
    }
    public Node getRight() {
        return right;
    }
    public Node getLeft() {
        return left;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFather(Node father) {
        this.father = father;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
}
