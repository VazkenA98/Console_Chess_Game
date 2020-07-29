package Pieces;

import Pieces.Piece;

public class Knight extends Piece {
    private String icon;
    public Knight(boolean white){
        super(white);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\KnightW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\KnightB.png");
        }
    }

    public String toString(){
        if(this.isWhite()){
            return "\u2658";
        }else{
            return "\u265E";
        }
    }

    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int[][] reachableCoordinates(Piece[][] board, int x, int y){

        int[][] result = new int[0][0];

        int[][] pattern = {
                {x + 2, y + 1},
                {x + 2, y - 1},
                {x - 2, y + 1},
                {x - 2, y - 1},

                {x + 1, y + 2},
                {x + 1, y - 2},
                {x - 1, y + 2},
                {x - 1, y - 2}
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
}
