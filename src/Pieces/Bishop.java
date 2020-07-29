package Pieces;

import Pieces.Piece;

public class Bishop extends Piece {


    private String icon;
    public Bishop(boolean white){
        super(white);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\BishopW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\BishopB.png");
        }
    }

    public String toString(){
        if(this.isWhite()){
                return "\u2657";
        }else{
            return "\u265D";
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

        for(int i = 1; x + i < BOARD_RANKS && y + i < BOARD_FILES; i++){
            if(board[x+i][y+i] == null){
                result = appendToResult(result,new int[]{x+i,y+i});
            }else{
                if(
                        (board[x+i][y+i].isWhite() && this.isWhite()) ||
                                ((!board[x+i][y+i].isWhite() && !this.isWhite()))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x+i,y+i});
                    break;
                }

            }
        }

        for(int i = 1; x - i >= 0 && y + i < BOARD_FILES; i++){
            if(board[x-i][y+i] == null){
                result = appendToResult(result,new int[]{x-i,y+i});
            }else{
                if(
                        (board[x-i][y+i].isWhite() && this.isWhite()) ||
                                ((!board[x-i][y+i].isWhite() && !this.isWhite()))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x-i,y+i});
                    break;
                }

            }
        }

        for(int i = 1; x + i < BOARD_RANKS && y - i >= 0; i++){
            if(board[x+i][y-i] == null){
                result = appendToResult(result,new int[]{x+i,y-i});
            }else{
                if(
                        (board[x+i][y-i].isWhite() && this.isWhite()) ||
                                ((!board[x+i][y-i].isWhite() && !this.isWhite()))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x+i,y-i});
                    break;
                }

            }
        }

        for(int i = 1; x - i >= 0 && y - i >= 0; i++){
            if(board[x-i][y-i] == null){
                result = appendToResult(result,new int[]{x-i,y-i});
            }else{
                if(
                        (board[x-i][y-i].isWhite() && this.isWhite()) ||
                                ((!board[x-i][y-i].isWhite() && !this.isWhite()))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x-i,y-i});
                    break;
                }

            }
        }

        return result;
    }


}
