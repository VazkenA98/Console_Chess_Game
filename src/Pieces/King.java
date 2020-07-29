package Pieces;

import Pieces.Piece;

public class King extends Piece {
    private boolean hasMoved;
    private String icon;
    public King(boolean white) {
        this(white, false);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\KingW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\KingB.png");
        }
    }

    public King(boolean white, boolean hasMoved) {
        super(white);
        this.hasMoved = hasMoved;
    }

    public String toString(){
        if(this.isWhite()){
            return "\u2654";
        }else{
            return "\u265A";
        }
    }
    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int[][] reachableCoordinates(Piece[][] board, int x, int y) {
        int[][] result = new int[0][0];
        //Pieces.King can move only one step. So all the adjacent 8 cells have been considered.

        int[][] pattern = {
                {x, y - 1},
                {x, y +1},
                {x + 1, y - 1},
                {x + 1, y },
                {x + 1, y + 1},
                {x - 1, y - 1},
                {x - 1, y},
                {x - 1, y + 1}
        };
        for(int i = 0; i < pattern.length; i++){
        if(pattern[i][0] >= 0 && pattern[i][0] < BOARD_RANKS && pattern[i][1] >= 0
                && pattern[i][1] < BOARD_FILES) {


            if(board[pattern[i][0]][pattern[i][1]] == null){
                result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
            }else {
                if ((board[pattern[i][0]][pattern[i][1]].isWhite() && !this.isWhite()) ||
                        (!board[pattern[i][0]][pattern[i][1]].isWhite() && this.isWhite())
                ) {
                    result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
                }

            }

        }
    }
        return result;
    }


    public boolean canDoCastling(int fromX, int toX, int fromY,int toY){

        if(toY == 6){
            if(toX == 7){
                return doRightCastling(fromX);
            }
        }
        if(toY == 2){
            if(toX == 7){

                return doLeftCastling(fromX);
            }
        }
        return false;
    }

    public boolean doRightCastling(int fromX){
        return true;
    }

    public boolean doLeftCastling(int fromX){
        return true;
    }
}