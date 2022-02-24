public class Player {

    public int playerId;

    private BingoBoard bingoboard;

    private boolean isBingo;

    // isBingo Getter
    public boolean isBingo() {
        return isBingo;
    }

    public Player() {
        playerId = -1; // default player id
        this.startNewGame();
    }

    public void startNewGame() {
        bingoboard = new BingoBoard();
        isBingo = false;
    }

    public boolean mark(int winningNum) {
        isBingo = bingoboard.mark(winningNum);
        return isBingo;
    }

    public BingoLine bingoLineInfo() {
        // return BingoLine object with line info
        return bingoboard.getBingoLine();
    }

    public void printBingoBoard() {
        bingoboard.printBoard();
    }
}
