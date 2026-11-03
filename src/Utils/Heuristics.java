package Utils;

public class Heuristics {

    public static int manhattanDistance(String state, String goalState) {
        int distance = 0;

        for (int i = 0; i < state.length(); i++) {
            char tile = state.charAt(i);
            if (tile == ' ') {
                continue; // el hueco no cuenta
            }
            int goalPos = goalState.indexOf(tile);
            distance += Math.abs(i / 3 - goalPos / 3) + Math.abs(i % 3 - goalPos % 3);
        }

        return distance;
    }
    public static int misplacedTiles(String state, String goalState) {
        int count = 0;

        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) != ' ' && state.charAt(i) != goalState.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}