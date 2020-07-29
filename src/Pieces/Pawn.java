package Pieces;

public class Pawn extends Piece {

    private int[] passingPawn;
    private String icon;
    public Pawn(boolean white){
        super(white);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\PawnW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\PawnB.png");
        }
    }

    public String toString(){
        if(this.isWhite()){
            return "\u2659";
        }else{
            return "\u265F";
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

        if(!isWhite()){
            if(board[x - 1][y] == null){
                result = Piece.appendToResult(result, new int[]{x-1,y});
                if(x == 6 && board[x - 2][y] == null){
                    result = Piece.appendToResult(result, new int[]{x-2,y});
                }
            }

            if(y > 0 && board[x - 1][y - 1] != null && board[x - 1][y - 1].isWhite()){
                result = Piece.appendToResult(result, new int[]{x - 1,y-1});
            }

            if(y < Piece.BOARD_FILES - 1 && board[x - 1][y + 1] != null && !board[x - 1][y + 1].isWhite()){
                result = Piece.appendToResult(result, new int[]{x - 1,y+1});
            }
        } else{
            if(board[x + 1][y] == null){
                result = Piece.appendToResult(result, new int[]{x+1,y});
                if(x == 1 && board[x + 2][y] == null){
                    result = Piece.appendToResult(result, new int[]{x+2,y});
                }
            }

            if(y > 0 && board[x + 1][y - 1] != null && !board[x + 1][y - 1].isWhite()){
                result = Piece.appendToResult(result, new int[]{x + 1,y-1});
            }

            if(y < Piece.BOARD_FILES - 1 && board[x + 1][y + 1] != null && board[x + 1][y + 1].isWhite()){
                result = Piece.appendToResult(result, new int[]{x + 1,y+1});
            }
        }

        return result;
    }

    public void setPassingPawn(int x, int y){
        this.passingPawn = new int[]{x, y};
    }

    public void setPassingPawn(){
        this.passingPawn = null;
    }
}
