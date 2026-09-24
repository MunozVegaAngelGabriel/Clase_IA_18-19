package ArbolBinario;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    Node root;

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

    public Node search(int id) {
        return searchRecursive(root, id);
    }

    private Node searchRecursive(Node current, int id) {
        if (current == null || current.getId() == id) {
            return current;
        }
        if (id < current.getId()) {
            return searchRecursive(current.getLeft(), id);
        }
        return searchRecursive(current.getRight(), id);
    }

    public void preOrderTraversal() {
        preOrderTraversalRecursive(root);
    }

    private void preOrderTraversalRecursive(Node node) {
        if (node != null) {
            System.out.print(node.getId() + " ");
            preOrderTraversalRecursive(node.getLeft());
            preOrderTraversalRecursive(node.getRight());
        }
    }

    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    private void inOrderTraversalRecursive(Node node) {
        if (node != null) {
            preOrderTraversalRecursive(node.getLeft());
            System.out.print(node.getId() + " ");
            preOrderTraversalRecursive(node.getRight());
        }
    }

    public void postOrderTraversal() {
        postOrderTraversalRecursive(root);
    }

    private void postOrderTraversalRecursive(Node node) {
        if (node != null) {
            preOrderTraversalRecursive(node.getLeft());
            preOrderTraversalRecursive(node.getRight());
            System.out.print(node.getId() + " ");
        }
    }

    public void deleteNode(int id) {
        root = deleteNodeRecursive(root, id);
    }

    private Node deleteNodeRecursive(Node current, int id) {
        if (current == null) {
            return current;
        }
        if (id < current.getId()) {
            current.setLeft(deleteNodeRecursive(current.getLeft(), id));
        } else if (id > current.getId()) {
            current.setRight(deleteNodeRecursive(current.getRight(), id));
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }
            Node temp = minValueNode(current.getRight());
            current.setId(temp.getId());
            current.setRight(deleteNodeRecursive(current.getRight(), temp.getId()));
        }
        return current;
    }
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
}
