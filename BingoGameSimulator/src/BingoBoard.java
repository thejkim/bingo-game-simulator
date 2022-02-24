import java.util.*;

public class BingoBoard
{
    public static final int SIZE = 25;
    public static final int COL_LENGTH = 5;
    public static final int ROW_LENGTH = 5;

    // 5x5 bingo board
    // numbers[row][col]
    private int[][] numbers = new int[5][5];
    private boolean[][] marked = new boolean[5][5];
    private BingoLine bingoLine;

    public BingoLine getBingoLine() {
        return bingoLine;
    }

    // Constructor
    public BingoBoard() {
        // initialize variables
        bingoLine = null;

        // set all numbers unmarked.
        for(int i=0 ; i < marked.length ; i++) {
            for(int j=0 ; j < marked[i].length ; j++) {
                marked[i][j] = false;
            }
        }
        this.fillBoard();
    }

    /***
     * Fill the empty bingo board with generated random numbers. All numbers are unique.
     * @param none
     * @return none
     */
    private void fillBoard() {
        // generate rand num, store in numbers array
        Random rand = new Random();

        int[] col1 = new int[5];
        for (int col=0 ; col < 5 ; col++) {
            for(int row=0; row < 5; row++) {
                numbers[row][col] = rand.nextInt(15) + 1 + (col*15);
                // enters the number check loop only when j is equal or greater than 1
                for(int i=0; i < row; i++) {
                    if(numbers[i][col] == numbers[row][col]) {
                        row--; //return to previous inner(row) loop. (== repeating the current inner(row) loop and generate another random number)
                        break;
                    }
                }
            }
        }
    }

    public void printBoard() {
        for (int row=0 ; row < 5; row++) {
            for (int col=0 ; col < 5 ; col++)
                System.out.print(numbers[row][col] + "  ");
            System.out.println();
        }
    }

    /* Check if bingo (containing the number located at the given row/col position on the bingo board)
     * If bingo, create a bingoLine object and save the bingo line info (type, starting position)
     * Returns true, if bingo, otherwise false.
     */
    private boolean checkBingoWithNumberAt(int row, int col) {
        int baseNumber = numbers[row][col];
        bingoLine = null;

        // check vertical, horizontal, diagonal(if applicable) lines from the base number

        // horizontal (same row #)
        for (int i=0 ; i < marked[row].length ; i++) {
            // unmarked = no bingo
            if (marked[row][i] == false) {
                break;
            } else {
                if (i == marked[row].length - 1) { //i=col #, marked[row].length = length of col
                    bingoLine = new BingoLine();
                    bingoLine.type = BingoLineType.Horizontal;
                    bingoLine.startingPosition = row;
                    // Bingo!
                    return true;
                }
            }
        }

        // vertical (same col #)
        for (int i=0 ; i < marked.length ; i++) {
            // unmarked = no bingo
            if (marked[i][col] == false) {
                break;
            } else {
                if (i == marked.length - 1) {
                    bingoLine = new BingoLine();
                    bingoLine.type = BingoLineType.Vertical;
                    bingoLine.startingPosition = col;
                    // Bingo!
                    return true;
                }
            }
        }

        // diagonal top (row == col)
        if (row == col) {
            for (int i=0 ; i < marked.length ; i++) {
                // unmarked = no bingo
                if (marked[i][i] == false) {
                    break;
                } else {
                    if (i == marked.length - 1) {
                        bingoLine = new BingoLine();
                        bingoLine.type = BingoLineType.DiagonalTop;
                        bingoLine.startingPosition = 0;
                        // Bingo!
                        return true;
                    }
                }
            }
        }

        // diagonal bottom (row + col == 5)
        if (row + col == marked.length) {
            for (int i=0 ; i < marked.length ; i++) {
                // unmarked = no bingo
                if (marked[i][(marked.length - 1) - i] == false) {
                    break;
                } else {
                    if (i == marked.length - 1) {
                        bingoLine = new BingoLine();
                        bingoLine.type = BingoLineType.DiagonalBottom;
                        bingoLine.startingPosition = marked.length - 1;
                        // Bingo!
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean mark(int number) {
        boolean isBingo = false;

        // look up the given number in numbers array
        int row = 0;
        int col = 0;
        for(int i=0 ; i < numbers.length ; i++) {
            for(int j=0 ; j < numbers[i].length ; j++) {
                if (numbers[i][j] == number) {
                    // if number exists
                    marked[i][j] = true; // marked (matching)
                    if (this.checkBingoWithNumberAt(i, j)) {
                        // Bingo!
                        isBingo = true;
                        break; // break inner (col) loop
                    }

                }
            }
            // break outer (row) loop
            if (isBingo == true) { break; }
        }
        return isBingo;
    }
}
