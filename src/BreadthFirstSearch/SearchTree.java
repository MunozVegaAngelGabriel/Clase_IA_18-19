package BreadthFirstSearch;


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
    private Set<String> visited;
    private long startTime, endTime;


    public SearchTree(String initialState, String goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
        this.visited = new HashSet<>();
        this.path = new Stack<>();
    }
    
    public void breadthFirstSearch(){
        startTime = System.nanoTime();
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(currentNode);
        while (!queue.isEmpty()){
            currentNode = queue.poll();
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
            List<Node> children = generateChildren(currentNode);
            for (Node child : children){
                if (!visited.contains(child.getState())){
                    visited.add(child.getState());
                    queue.add(child);
                    nodesGenerated++;
                }
            }
        }
    }
    public void printPath(){
        while (!path.isEmpty()){
            System.out.println(NodeUtils.formatState(path.pop()));
        }
        System.out.println("Nodes generated: " + nodesGenerated);
        System.out.println("Tiempo de ejecucion (ns): " + endTime);
    }
}

