package Players.dmp6637;

import Interface.Coordinate;

import java.util.HashSet;
import java.util.Set;

/**
 * Node for use in a pathbuilder game.
 *
 * @author dmp6637 (David Pastuch)
 */
public class Node{

    private int playerId;
    private Set<Node> neighbors;
    private Coordinate coord;

    /**
     * Create a new node.
     * @param playerId player Id
     * @param row row
     * @param col column
     */
    public Node(int playerId, int row, int col) {
        this.playerId = playerId;
        neighbors = new HashSet<Node>();
        coord = new Coordinate(row, col);
    }

    /**
     * The the ID of the player this node belongs to.
     * @return player ID
     */
    public int getId() {
        return playerId;
    }

    /**
     * Add a neighbor to this node's set of neighbors.
     * @param n node to add
     */
    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    /**
     * Get this node's set of neighbors.
     * @return neighbors
     */
    public Set<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Get this node's coordinates
     * @return
     */
    public Coordinate getCoordinate() {
        return coord;
    }

    public void setId(int Id) {
        playerId = Id;
    }

    public String toString() {
        return Integer.toString(playerId);
    }
}
