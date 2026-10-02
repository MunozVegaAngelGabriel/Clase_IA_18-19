package DepthFirstSearch;

import Classes.Node;
import Utils.NodeUtils;

import java.util.*;

import static Utils.NodeUtils.formatState;

public class SearchTree {
    private Node root;
    private String initialState;
    private String goalState;
    private Node goalNode;
    private Stack<String> path;
    private int nodesGenerated = 0;
    private Set<String> visited;
    private long startTime, endTime;

    public SearchTree(String initialState, String goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
        this.visited = new HashSet<>();
        this.path = new Stack<>();
    }

    public void DepthFirstSearch(){
        startTime = System.nanoTime();
        Node currentNode = root;
        Stack<Node> stack = new Stack<>();
        stack.push(currentNode);
        while (!stack.isEmpty()){
            currentNode = stack.pop();
            visited.add(currentNode.getState());
            if (currentNode.getState().equals(goalState)){
                goalNode = currentNode;
                endTime = System.nanoTime() - startTime;
                while (currentNode.getParent() != null){
                    path.add(currentNode.getState());
                    currentNode = currentNode.getParent();
                }
                return;
            }
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children){
                if (!visited.contains(child.getState())){
                    visited.add(child.getState());
                    stack.push(child);
                    nodesGenerated++;
                }
            }
        }
    }

    public void printPath(){
        if (goalNode != null) {
            Node currentNode = goalNode;
            while (currentNode != null) {
                path.push(currentNode.getState());
                currentNode = currentNode.getParent();
            }
            while (!path.isEmpty()){
                System.out.println(formatState(path.pop()));
            }
        } else {
            System.out.println("No path found.");
        }
        System.out.println("Nodes generated: " + nodesGenerated);
        System.out.println("Tiempo de ejecucion (ns): " + endTime);
    }
}
