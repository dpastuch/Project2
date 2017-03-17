package Players.dmp6637;

import java.util.HashSet;
import java.util.Set;

/**
 * Players.dmp6637.Node for use in a pathbuilder game.
 *
 * @author dmp6637 (David Pastuch)
 */
public class Node {

    private int playerId;
    private int row;
    private int col;
    private Set<Node> neighbors;

    public Node(int playerId, int row, int col) {
        this.playerId = playerId;
        this.row = row;
        this.col = col;
        neighbors = new HashSet<>();
    }

    public int getId() {
        return playerId;
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }
}
