import java.util.*;

public class Referee
{
    private int winNum;
    private int[] pastWinningNum;
    private int numOfDraws;
    private final int CARD_NUMBER_RANGE_MAX = 75;

    public Referee() {
        this.startNewGame();
    }

    public void startNewGame() {
        winNum = 0;
//		pastWinningNum = new int[BingoBoard.SIZE];
        pastWinningNum = new int[CARD_NUMBER_RANGE_MAX];
//		for (int i=0 ; i < BingoBoard.SIZE ; i++) {
        for (int i=0 ; i < CARD_NUMBER_RANGE_MAX ; i++) {
            pastWinningNum[i] = -1;
        }
        numOfDraws = 0;
    }

    // draw a new winning number
    public int drawNextWinningNumber() {
        Random rand = new Random();
        int number = rand.nextInt(CARD_NUMBER_RANGE_MAX) + 1;

        // check duplication
        int index = 0;
//		while(index < BingoBoard.SIZE && pastWinningNum[index] > 0) {
        while(index < CARD_NUMBER_RANGE_MAX && pastWinningNum[index] > 0) {
            for (int j = 0 ; j <= index ; j++) {
                // checking if the current number matches any of the past winning numbers
                if (pastWinningNum[j] == number) {
                    number = rand.nextInt(CARD_NUMBER_RANGE_MAX) + 1;
                    index -= 1;
                    break;
                }
            }
            index++;
        }
//		if (index < BingoBoard.SIZE) {
        if (index < CARD_NUMBER_RANGE_MAX) {
            winNum = number;
            pastWinningNum[index] = number;
            numOfDraws++;
        }

        return winNum;
    }

    public int currentWinningNumber() {
        return winNum;
    }

    public int numberOfDraws() {
        return numOfDraws;
    }
}

