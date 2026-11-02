package Iterative;

public class App {
    public static void main(String[] args) {

        String initialState = "7621 3458";
        String goalState = "12345678 ";
        int maxDepth = 30;

        System.out.println("START");
        SearchTree searchTree = new SearchTree(initialState, goalState, maxDepth);
        searchTree.iterativeSearch();
        searchTree.printPath();
        System.out.println("END");
    }
}