package Iterative;

import Classes.Node;
import Utils.NodeUtils;

import java.util.*;

import static Utils.NodeUtils.generateChildren;
public class SearchTree {
    private Node root;
    private String initialState;
    private String goalState;
    private Node goalNode;
    private Stack<String> path;
    private int nodesGenerated = 0;
    private int maxDepth;
    private int solutionLimit = -1;
    private int iterations = 0;
    private long startTime, endTime;

    private Set<String> currentBranch;

    public SearchTree(String initialState, String goalState, int maxDepth) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.maxDepth = maxDepth;
        this.root = new Node(initialState, null);
        this.root.setDepth(0);
        this.path = new Stack<>();
        this.currentBranch = new HashSet<>();
    }

    public void iterativeSearch() {
        startTime = System.nanoTime();

        for (int limit = 0; limit <= maxDepth; limit++) {
            iterations++;
            currentBranch.clear();
            currentBranch.add(root.getState());

            goalNode = depthLimitedSearch(root, limit);

            if (goalNode != null) {
                solutionLimit = limit;
                endTime = System.nanoTime() - startTime;
                buildPath();
                return;
            }
        }

        endTime = System.nanoTime() - startTime;
    }

    private Node depthLimitedSearch(Node node, int limit) {
        if (node.getState().equals(goalState)) {
            return node;
        }
        if (node.getDepth() >= limit) {
            return null;
        }

        for (Node child : generateChildren(node)) {
            if (currentBranch.contains(child.getState())) {
                continue;
            }
            child.setDepth(node.getDepth() + 1);
            nodesGenerated++;

            currentBranch.add(child.getState());
            Node result = depthLimitedSearch(child, limit);
            currentBranch.remove(child.getState());

            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private void buildPath() {
        Node current = goalNode;
        while (current != null) {
            path.push(current.getState());
            current = current.getParent();
        }
    }

    public void printPath() {
        if (goalNode == null) {
            System.out.println("No se encontró solución con profundidad máxima " + maxDepth);
        } else {
            while (!path.isEmpty()) {
                System.out.println(NodeUtils.formatState(path.pop()));
            }
            System.out.println("Profundidad de la solución: " + goalNode.getDepth());
            System.out.println("Límite con el que se encontró: " + solutionLimit);
        }
        System.out.println("Iteraciones (límites probados): " + iterations);
        System.out.println("Nodes generated: " + nodesGenerated);
        System.out.println("Tiempo de ejecucion (ns): " + endTime);
    }
}