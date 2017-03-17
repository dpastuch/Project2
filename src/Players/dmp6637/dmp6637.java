package Players.dmp6637;

import Interface.PlayerModulePart1;
import Interface.PlayerMove;

/**
 * A class that works as a player module.
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
        board = new Node[dim*2][dim*2];
        this.playerId = playerId;
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
        Node n = new Node(playerId, row, col);
        board[row][col] = n;
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
        return false;
    }

    private void setNeighbor(Node n, int row, int col) {
        if(row > 0 && row < board.length && col > 0 && col < board.length) {
            if(board[row][col] != null) {
                n.addNeighbor(board[row][col]);
            }
        }
    }
}
