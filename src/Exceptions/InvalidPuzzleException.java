package Exceptions;

import java.util.ArrayList;
import java.util.Collections;

public class InvalidPuzzleException extends Exception {

    InvalidPuzzleException(String str) {
        super(str);
    }

    public static void validatePuzzle(String data) throws InvalidPuzzleException {
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
            pos +=2;
        }
        int whiteKing = 0;
        int whiteKingMoved = 0;
        int blackKing = 0;
        int blackKingMoved = 0;

        for (String str : list) {
            if (str.equals("16")) {
                whiteKing++;
            }
            if (str.equals("18")) {
                whiteKingMoved++;
            }
            if (str.equals("26")) {
                blackKing++;
            }
            if (str.equals("28")) {
                blackKingMoved++;
            }
        }

        if ((whiteKing + blackKing == 2 && whiteKingMoved + blackKingMoved == 0) || (whiteKingMoved + blackKingMoved == 2
                && blackKing + whiteKing == 0) || (whiteKing + blackKingMoved == 2 && blackKing + whiteKingMoved == 0) ||
                (blackKing + whiteKingMoved == 2 && whiteKing + blackKingMoved == 0) || whiteKing>1 || blackKing>1 || whiteKingMoved>1 || blackKingMoved>1) {
            valid = false;
        }

        return valid;
    }
}
