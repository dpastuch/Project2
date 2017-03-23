package Players.dmp6637;

import Interface.PlayerModulePart1;
import Interface.PlayerMove;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * A class that works as a player module.
 *
 * @author dmp6637 (David Pastuch)
 */
public class dmp6637 implements PlayerModulePart1 {

    private Node[][] board;
    private int playerId;

    /**
     * Initialize a player module.
     * @param dim size of the smaller dimension of playing area for one player.
     * @param playerId player id (1 or 2)
     */
    @Override
    public void initPlayer(int dim, int playerId) {
        board = new Node[dim*2 + 1][dim*2 + 1];
        this.playerId = playerId;
        for(int i = 0; i <= dim*2; i++) {
            if(i == 0) {
                for(int j = 0; j <= dim*2; j++) {
                    board[i][j] = new Node(1, i, j);
                }
            }
            for(int j = 0; j <= dim*2; j++) {
                if(j == 0) {
                    board[i][j] = new Node(2, i, j);
                }
                else if(((i % 2) == 0) && ((j % 2) == 1)) {
                    board[i][j] = new Node(1, i, j);
                }
                else if(((i % 2) == 1) && ((j % 2) == 0)) {
                    board[i][j] = new Node(2, i, j);
                }
            }
        }
    }

    /**
     * Called after every move of the game. Used to keep internal game state
     * current.
     * @param m most recent move
     */
    @Override
    public void lastMove(PlayerMove m) {
        int row = m.getCoordinate().getRow();
        int col = m.getCoordinate().getCol();
        Node n = new Node(m.getPlayerId(), row, col);
        board[col][row] = n;
        setNeighbor(n, row - 1, col);
        setNeighbor(n, row + 1, col);
        setNeighbor(n, row, col - 1);
        setNeighbor(n, row, col + 1);
    }

    /**
     * Generates next move for this player. Update game state OUTSIDE this
     * method (lastMove).
     * @return the next move
     */
    @Override
    public PlayerMove move() {
        return null;
    }

    /**
     * Indicates that the other player has been invalidated.
     */
    @Override
    public void otherPlayerInvalidated() {

    }

    /**
     * Tests if a player has won the game given a set of PREMOVEs.
     * @param id player id
     * @return true if player has a winning path, otherwise false
     */
    @Override
    public boolean hasWonGame(int id) {
        //TODO breadth first search
        if(id == 1) {
            winningPath(0,1,id, board.length - 1);
        }
        if(id == 2) {
            winningPath(1,0,id, board.length - 1);
        }
        return false;
    }

    /**
     * Helper method that adds a node to another node's list of neighbors.
     * @param n node to add neighbor to
     * @param row row of node to add
     * @param col column of node to add
     */
    private void setNeighbor(Node n, int row, int col) {
        if(row >= 0 && row < board.length && col >= 0 && col < board.length) {
            if(board[col][row] != null && board[col][row].getId() == playerId) {
                n.addNeighbor(board[col][row]);
                board[col][row].addNeighbor(n);
            }
        }
    }

    /**
     * Check if there is a winning path from this node.
     * @param x x coordinate of node to check
     * @param y y coordinate of node to check
     * @param playerId player Id
     * @param dim size of board
     * @return true if there is a winning path, otherwise false
     */
    private boolean winningPath(int x, int y, int playerId, int dim) {
        HashSet<Node> s = new HashSet<>();
        LinkedList<Node> l = new LinkedList<>();

        l.addLast(board[y][x]);
        s.add(board[y][x]);

        while(!(l.isEmpty())) {
            Node curr = l.pop();
            if(playerId == 1) {
                if(curr.getCoordinate().getCol() == dim) {
                    return true;
                }
            }
            else if(playerId == 2) {
                if(curr.getCoordinate().getRow() == dim) {
                    return true;
                }
            }
            for(Node n : curr.getNeighbors()) {
                if(!(s.contains(n))) {
                    l.addLast(n);
                    s.add(n);
                }

            }
        }
        return false;
    }
}
