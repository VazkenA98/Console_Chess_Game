package Pieces;

public class Rook extends Piece {

    private boolean hasMoved;
    private String icon;
    public Rook(boolean white){
        this(white,false);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\RookW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\RookB.png");
        }
    }

    public Rook(boolean white, boolean hasMoved){
        super(white);
        this.hasMoved = hasMoved;
    }

    public void setMoved(){
        this.hasMoved = true;
    }

    public String toString(){
        if(this.isWhite()){
            return "\u2656";
        }else{
            return "\u265C";
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


        for(int i = x + 1; i < Piece.BOARD_RANKS; i++){
            if(board[i][y] == null) {
                result = Piece.appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && this.isWhite()) ||
                        (!board[i][y].isWhite() && !this.isWhite())))
                {
                    result = Piece.appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--){
            if(board[i][y] == null) {
                result = Piece.appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && this.isWhite()) ||
                        (!board[i][y].isWhite() && !this.isWhite())))
                {
                    result = Piece.appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = y + 1; i < Piece.BOARD_FILES; i++){
            if(board[x][i] == null) {
                result = Piece.appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && this.isWhite()) ||
                        (!board[x][i].isWhite() && !this.isWhite())))
                {
                    result = Piece.appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--){
            if(board[x][i] == null) {
                result = Piece.appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && this.isWhite()) ||
                        (!board[x][i].isWhite() && !this.isWhite())))
                {
                    result = Piece.appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }

        return result;
    }


}
