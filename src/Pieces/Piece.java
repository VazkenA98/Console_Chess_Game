package Pieces;

public class Piece {
    private boolean pieceIsWhite;
    private String icon;
    protected static final int BOARD_RANKS = 8;
    protected static final int BOARD_FILES = 8;


    public Piece(boolean white){
        this.pieceIsWhite = white;
    }

    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        return null;
    }

    public String toString(){
        return "X";
    }

    public boolean isWhite(){
        return pieceIsWhite;
    }

    public String getIcon(){return icon;}

    protected static int[][] appendToResult(int[][] squares, int[] square){

        int[][] result = new int[squares.length + 1][];

        for(int i = 0; i < squares.length; i++){
            result[i] = new int[]{squares[i][0], squares[i][1]};
        }

        result[result.length - 1] = new int[]{square[0],square[1]};


        return result;
    }

    public static void printMatrix(int[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

}
