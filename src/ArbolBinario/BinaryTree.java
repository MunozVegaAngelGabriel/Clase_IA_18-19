package ArbolBinario;

public class BinaryTree {
    private Node root;
    public void insertNode(int id) {
        Node newNode = new Node();
        newNode.setId(id);

        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    private void insertNode(Node current, Node newNode) {
        if (newNode.getId() < current.getId()) {
            if (current.getLeft() == null) {
                current.setLeft(newNode);
                newNode.setFather(current);
            } else {
                insertNode(current.getLeft(), newNode);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newNode);
                newNode.setFather(current);
            } else {
                insertNode(current.getRight(), newNode);
            }
        }
    }
    

    public void preOrder() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.getId() + " ");
            preOrderRecursive(node.getLeft());
            preOrderRecursive(node.getRight());
        }
    }

    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            preOrderRecursive(node.getLeft());
            System.out.print(node.getId() + " ");
            preOrderRecursive(node.getRight());
        }
    }

    public void postOrder() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node node) {
        if (node != null) {
            preOrderRecursive(node.getLeft());
            preOrderRecursive(node.getRight());
            System.out.print(node.getId() + " ");
        }
    }

}
