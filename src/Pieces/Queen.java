package Pieces;

public class Queen extends Bishop {
    private String icon;
    public Queen(boolean white){
        super(white);
        if(this.isWhite()){
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\QueenW.png");
        }else{
            setIcon("C:\\Users\\User\\Desktop\\AUA OOP\\ChessH5\\src\\ChessPieces\\QueenB.png");
        }
    }

    public String toString(){
        if(this.isWhite()){
            return "\u2655";
        }else{
            return "\u265B";
        }
    }
    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        int[][] result = super.reachableCoordinates(board,x,y);

        for(int i = x + 1; i < BOARD_RANKS; i++){
            if(board[i][y] == null) {
                result = appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && this.isWhite()) ||
                        (!board[i][y].isWhite() && !this.isWhite())))
                {
                    result = appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--){
            if(board[i][y] == null) {
                result = appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && this.isWhite()) ||
                        (!board[i][y].isWhite() && !this.isWhite())))
                {
                    result = appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = y + 1; i < BOARD_FILES; i++){
            if(board[x][i] == null) {
                result = appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && this.isWhite()) ||
                        (!board[x][i].isWhite() && !this.isWhite())))
                {
                    result = appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--){
            if(board[x][i] == null) {
                result = appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && this.isWhite()) ||
                        (!board[x][i].isWhite() && !this.isWhite())))
                {
                    result = appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }


        return result;
    }
}
