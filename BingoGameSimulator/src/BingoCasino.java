import java.util.*;

public class BingoCasino {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        final int NUMBER_OF_PLAYERS = 300;
        final int NUMBER_OF_GAMES = 20000;
        Referee ref = new Referee();
        Player[] players = new Player[NUMBER_OF_PLAYERS];

        HashMap<Integer, WinningRecord> recordMap = new HashMap<Integer, WinningRecord>();

        System.out.println("Bingo Game (5x5)\n\tNumber of Players: " + NUMBER_OF_PLAYERS
                + "\n\tNumber of Games: " + NUMBER_OF_GAMES + "\n");

        // creating multiple players
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++ ) {
            players[i] = new Player();
            players[i].playerId = i+1;
        }

        int currentWinningNum;
        boolean winnerExists;

        for(int i = 0; i < NUMBER_OF_GAMES; i++) {
            // START NEW GAME
            // reset player's game info
            for(int k = 0; k < NUMBER_OF_PLAYERS; k++) {
                players[k].startNewGame();
            }
            // reset referee's past game info
            ref.startNewGame();

            winnerExists = false;

            while(!winnerExists) { // winnerExists != true

                currentWinningNum = ref.drawNextWinningNumber();

                for(int j = 0; j < NUMBER_OF_PLAYERS; j++) {
                    if( players[j].mark(currentWinningNum) == true ) {
                        // Data to be saved : (Key Obj) # of draws
                        // 					  (Value Obj) # of H.Line, # of V.Line, # of DT.Line, # of DB.Line, # of winners

                        // make hashmap key
                        Integer key = new Integer(ref.numberOfDraws());
                        WinningRecord value = recordMap.get(key);

                        // save bingo info in the object
                        BingoLineType bingoType = players[j].bingoLineInfo().type;

                        // Value does not exist for the given key
                        if(value == null) {
                            // create WinningRecord object
                            value = new WinningRecord();
                        }
                        // update bingo info in the object
                        WinningRecord.updateWinningRecord(value, bingoType);
                        value.numberOfWinners++;

                        // put WinningRecord object into the map
                        recordMap.put(key, value);

                        winnerExists = true;
                    }
                }
            }
        }
        // Print HashMap record
        Set<Integer> keySet = recordMap.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        // print Header
        System.out.printf("[%7s %7s %7s %7s %7s %7s]\n", "Draws", "VL", "HL", "DTL", "DBL", "Winners");
        int totalWinners = 0;
        int totalVL = 0;
        int totalHL = 0;
        int totalDTL = 0;
        int totalDBL = 0;
        while(iterator.hasNext()) {
            Integer key = iterator.next();
            WinningRecord record = recordMap.get(key);

            // total # of record
            totalWinners += record.numberOfWinners;
            totalVL += record.verticalLines;
            totalHL += record.horizontalLines;
            totalDTL += record.diagonalTopLines;
            totalDBL += record.diagonalBottomLines;

            // print # of each types of Bingo Game Result
            System.out.printf("[%7d %7d %7d %7d %7d %7d]\n", key, record.verticalLines,
                    record.horizontalLines, record.diagonalTopLines, record.diagonalBottomLines, record.numberOfWinners);
        }

        // print total # of record
        System.out.printf("[%7s %7d %7d %7d %7d %7d]\n", "Total", totalVL, totalHL, totalDTL, totalDBL, totalWinners);

        System.out.println("\n\nExecution Time: " + ((System.nanoTime() - startTime) * 0.000000001) + "sec");
    }
}
