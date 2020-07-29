package Exceptions;

import java.util.ArrayList;
import java.util.Collections;

public class InvalidPuzzleException extends Exception {

    InvalidPuzzleException(String str) {
        super(str);
    }

    static void validatePuzzle(String data) throws InvalidPuzzleException {
        if (data.length() < 128)
            throw new InvalidPuzzleException("Malformed initial arrangement.");
        if (validateKings(data))
            throw new InvalidPuzzleException("Incorrect number of kings in a single puzzle.");
    }

    private static boolean validateKings(String data) {
        int pos = 0;
        boolean valid = true;
        ArrayList<String> list = new ArrayList<String>();
        for (int m = 2; m <= data.length(); m += 2) {
            String loc = data.substring(pos, m);
            if (loc.equals("16") || loc.equals("26") || loc.equals("18") || loc.equals("28")) {
                list.add(loc);
            }
        }
        int whiteKing = Collections.frequency(list, "16");
        int whiteKingMoved = Collections.frequency(list, "18");
        int blackKing = Collections.frequency(list, "26");
        int blackKingMoved = Collections.frequency(list, "28");

        if ((whiteKing == 1 && blackKing == 1 && whiteKingMoved == 0 && blackKingMoved == 0)
                || (whiteKing == 0 && blackKing == 0 && whiteKingMoved == 1 && blackKingMoved == 1)
        ) {
            valid = false;
        }

        return valid;
    }
}
