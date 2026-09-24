package ArbolBinario;

public class BinaryTreeApp {
    public static void main(String[] args) {
        BinaryTree arbol = new BinaryTree();
        arbol.insertNode(1);
        arbol.insertNode(2);
        arbol.insertNode(3);
        arbol.insertNode(32);
        arbol.insertNode(50);
        arbol.insertNode(12);
        arbol.insertNode(4);

        System.out.println("Impresion en preorden:");
        arbol.preOrder();
        System.out.println("Impresion en entreorden:");
        arbol.inOrder();
        System.out.println("Impresion en postorden:");
        arbol.postOrder();
    }
}
