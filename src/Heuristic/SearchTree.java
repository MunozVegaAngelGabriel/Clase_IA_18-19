package Heuristic;

import Classes.Node;
import Utils.Heuristics;
import Utils.NodeUtils;

import java.util.*;

import static Utils.NodeUtils.generateChildren;

public class SearchTree {
    private static final int FOUND = -1;

    private Node root;
    private String initialState;
    private String goalState;
    private Node goalNode;
    private List<String> path = new ArrayList<>();
    private int nodesGenerated = 0;
    private int iterations = 0;
    private int finalThreshold = -1;
    private long startTime, endTime;

    private Set<String> currentBranch = new HashSet<>();

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
        this.root.setDepth(0);
    }

    private int h(String state) {
        return Heuristics.manhattanDistance(state, goalState);
    }

    public void idaStarSearch() {
        startTime = System.nanoTime();
        int threshold = h(initialState);

        while (true) {
            iterations++;
            currentBranch.clear();
            currentBranch.add(root.getState());

            int result = search(root, threshold);

            if (result == FOUND) {
                finalThreshold = threshold;
                endTime = System.nanoTime() - startTime;
                buildPath();
                return;
            }
            if (result == Integer.MAX_VALUE) {
                endTime = System.nanoTime() - startTime;
                return;
            }
            threshold = result;
        }
    }

    private int search(Node node, int threshold) {
        int g = node.getDepth();
        int f = g + h(node.getState());

        if (f > threshold) {
            return f;
        }
        if (node.getState().equals(goalState)) {
            goalNode = node;
            return FOUND;
        }

        int min = Integer.MAX_VALUE;

        for (Node child : generateChildren(node)) {
            if (currentBranch.contains(child.getState())) {
                continue;
            }
            child.setDepth(g + 1);
            nodesGenerated++;

            currentBranch.add(child.getState());
            int result = search(child, threshold);
            currentBranch.remove(child.getState());

            if (result == FOUND) {
                return FOUND;
            }
            if (result < min) {
                min = result;
            }
        }
        return min;
    }

    private void buildPath() {
        LinkedList<String> reversed = new LinkedList<>();
        Node current = goalNode;
        while (current != null) {
            reversed.addFirst(current.getState());
            current = current.getParent();
        }
        path.addAll(reversed);
    }

    public void printPath() {
        if (path.isEmpty()) {
            System.out.println("No se encontró solución");
        } else {
            for (int i = 0; i < path.size(); i++) {
                System.out.println(NodeUtils.formatState(path.get(i)));
            }
        }
        System.out.println("Iteraciones (límites probados): " + iterations);
        System.out.println("Nodes generated: " + nodesGenerated);
        System.out.println("Tiempo de ejecucion (ns): " + endTime);
    }
}