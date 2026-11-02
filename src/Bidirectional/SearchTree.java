package Bidirectional;

import Classes.Node;
import Utils.NodeUtils;

import java.util.*;

import static Utils.NodeUtils.generateChildren;

public class SearchTree {
    private String initialState;
    private String goalState;

    private Node forwardMeeting;
    private Node backwardMeeting;
    private List<String> path = new ArrayList<>();

    private int nodesGenerated = 0;
    private long startTime, endTime;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public void bidirectionalSearch() {
        startTime = System.nanoTime();

        Node forwardRoot = new Node(initialState, null);
        Node backwardRoot = new Node(goalState, null);
        forwardRoot.setDepth(0);
        backwardRoot.setDepth(0);

        if (initialState.equals(goalState)) {
            forwardMeeting = forwardRoot;
            backwardMeeting = backwardRoot;
            endTime = System.nanoTime() - startTime;
            buildPath();
            return;
        }

        Map<String, Node> forwardVisited = new HashMap<>();
        Map<String, Node> backwardVisited = new HashMap<>();
        Queue<Node> forwardQueue = new LinkedList<>();
        Queue<Node> backwardQueue = new LinkedList<>();

        forwardVisited.put(initialState, forwardRoot);
        backwardVisited.put(goalState, backwardRoot);
        forwardQueue.add(forwardRoot);
        backwardQueue.add(backwardRoot);

        while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
            boolean expandForward = forwardQueue.size() <= backwardQueue.size();

            boolean found = expandForward
                    ? expandLevel(forwardQueue, forwardVisited, backwardVisited, true)
                    : expandLevel(backwardQueue, backwardVisited, forwardVisited, false);

            if (found) {
                endTime = System.nanoTime() - startTime;
                buildPath();
                return;
            }
        }

        endTime = System.nanoTime() - startTime;
    }
    private boolean expandLevel(Queue<Node> queue, Map<String, Node> ownVisited, Map<String, Node> otherVisited, boolean forward) {
        int levelSize = queue.size();
        int bestLength = Integer.MAX_VALUE;
        Node bestOwn = null;
        Node bestOther = null;

        for (int i = 0; i < levelSize; i++) {
            Node current = queue.poll();

            for (Node child : generateChildren(current)) {
                if (ownVisited.containsKey(child.getState())) {
                    continue;
                }
                child.setDepth(current.getDepth() + 1);
                ownVisited.put(child.getState(), child);
                queue.add(child);
                nodesGenerated++;

                Node other = otherVisited.get(child.getState());
                if (other != null) {
                    int length = child.getDepth() + other.getDepth();
                    if (length < bestLength) {
                        bestLength = length;
                        bestOwn = child;
                        bestOther = other;
                    }
                }
            }
        }

        if (bestOwn == null) {
            return false;
        }

        forwardMeeting = forward ? bestOwn : bestOther;
        backwardMeeting = forward ? bestOther : bestOwn;
        return true;
    }

    private void buildPath() {
        LinkedList<String> firstHalf = new LinkedList<>();
        Node current = forwardMeeting;
        while (current != null) {
            firstHalf.addFirst(current.getState());
            current = current.getParent();
        }
        path.addAll(firstHalf);
        current = backwardMeeting.getParent();
        while (current != null) {
            path.add(current.getState());
            current = current.getParent();
        }
    }

    public void printPath() {
        if (path.isEmpty()) {
            System.out.println("No se encontró solución");
        } else {
            for (int i = 0; i < path.size(); i++) {
                System.out.println(NodeUtils.formatState(path.get(i)));
            }
        }
        System.out.println("Nodes generated: " + nodesGenerated);
        System.out.println("Tiempo de ejecucion (ns): " + endTime);
    }
}