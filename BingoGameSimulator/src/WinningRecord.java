public class WinningRecord {
    public int verticalLines = 0;
    public int horizontalLines = 0;
    public int diagonalTopLines = 0;
    public int diagonalBottomLines = 0;
    public int numberOfWinners = 0;

    public static WinningRecord updateWinningRecord(WinningRecord record, BingoLineType bingoType) {
        if(bingoType == BingoLineType.Vertical) {
            record.verticalLines++;
        } else if(bingoType == BingoLineType.Horizontal) {
            record.horizontalLines++;
        } else if(bingoType == BingoLineType.DiagonalTop) {
            record.diagonalTopLines++;
        } else {
            record.diagonalBottomLines++;
        }
        return record;
    }
}
