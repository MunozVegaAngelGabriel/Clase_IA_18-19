package Classes;

public class Node {
    private String state;
    private Node parent;
    private int depth;

    public Node(String state, Node parent){
        this.state = state;
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public String getState() {
        return state;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setState(String state) {
        this.state = state;
    }
}
