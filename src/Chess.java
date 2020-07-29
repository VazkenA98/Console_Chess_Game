import Exceptions.InvalidPuzzleException;
import Pieces.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chess {
    private Piece[][] board;
    private static final int BOARD_RANKS = 8;
    private static final int BOARD_FILES = 8;


    public Chess() {
       setUpBoard(gameModeOptionList());
    }

    private String gameModeOptionList() {
        StringBuffer sb=new StringBuffer();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();
        try
        {
            File file=new File("puzzles.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                stringList.add(line);
            }
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Object[] options = {1,2,3,"default"};
        int n = JOptionPane.showOptionDialog(new JFrame(),
                "1-"+stringList.get(1)+"\n"+"2-"+stringList.get(3)+"\n"+"3-"+stringList.get(5)+"default",
                "Choose game mode",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]);
        System.out.println(n);
        String chessBoard = "";
        switch (n){
            case 0:
                chessBoard = stringList.get(1);
                break;
            case 1:
                chessBoard = stringList.get(3);
                break;
            case 2:
                chessBoard = stringList.get(5);
                break;
            default:
                chessBoard = "24232225262223242121212121212121000000000000000000000000000000000000000000000000000000000000000011111111111111111413121516121314";
        }
        checkModeValidation(chessBoard);
        return chessBoard;
    }

    private void checkModeValidation(String chessBoard) {
        try{
            InvalidPuzzleException.validatePuzzle(chessBoard);
        }catch (Exception e){
            String message = e.getMessage();
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


    public String getPieceIcon(int i, int j) {
        return board[i][j].getIcon();
    }

    public Piece getPiece(int i, int j) {
        return board[i][j];
    }
    public Piece[][] getBoard(){
        return this.board;
    }


    private void setUpBoard(String arg) {
        board = new Piece[BOARD_RANKS][BOARD_FILES];
        int j = 0;
        int i = 0;
        int pos = 0;
        for (int m = 2; m <= arg.length(); m += 2) {
            String loc = arg.substring(pos, m);
            pos = m;
            switch (loc) {
                case "00":
                    break;
                case "11":
                    board[i][j] = new Pawn(false);
                    break;
                case "12":
                    board[i][j] = new Bishop(false);
                    break;
                case "13":
                    board[i][j] = new Knight(false);
                    break;
                case "14":
                    board[i][j] = new Rook(false);
                    break;
                case "15":
                    board[i][j] = new Queen(false);
                    break;
                case "16":
                    board[i][j] = new King(false);
                    break;
                case "17":
                    board[i][j] = new Rook(false);
                    break;
                case "18":
                    board[i][j] = new King(false);
                    break;
                case "21":
                    board[i][j] = new Pawn(true);
                    break;
                case "22":
                    board[i][j] = new Bishop(true);
                    break;
                case "23":
                    board[i][j] = new Knight(true);
                    break;
                case "24":
                    board[i][j] = new Rook(true);
                    break;
                case "25":
                    board[i][j] = new Queen(true);
                    break;
                case "26":
                    board[i][j] = new King(true);
                    break;
                case "27":
                    board[i][j] = new Rook(true);
                    break;
                case "28":
                    board[i][j] = new King(true);
                    break;
                default:

            }
            j++;
            if (j == 8) {
                j = 0;
                i++;
            }

        }


    }
    public void moviePiece(int fromX ,int fromY,int toX, int toY){

            board[toX][toY] = board[fromX][fromY];
            board[fromX][fromY] = null;

    }

    public void play() {

        Scanner sc = new Scanner(System.in);
        int turn = 0;
        boolean gameNotFinished = true;
        String[] userInput;


        print();


        while (gameNotFinished) {
            if (turn % 2 == 0) {
                System.out.print("White's move:");

            } else {
                System.out.print("Black's move:");

            }

            userInput = sc.nextLine().toLowerCase().split(" ");
            System.out.println();

            if (userInput.length == 1) {
                int[] sourceCoords = squareToCoords(userInput[0]);
                printHighlighted(sourceCoords[0], sourceCoords[1]);

            } else {
                if (userInput.length == 2) {

                    int[] sourceCoords = squareToCoords(userInput[0]);
                    int[] destCoords = squareToCoords(userInput[1]);
                    printHighlighted(sourceCoords[0], sourceCoords[1]);


                    if (isSquareReachable(sourceCoords[0], sourceCoords[1],
                            destCoords[0], destCoords[1])) {
                        board[destCoords[0]][destCoords[1]] = board[sourceCoords[0]][sourceCoords[1]];
                        board[sourceCoords[0]][sourceCoords[1]] = null;
                    }
                    //check your king before moving
                    if (checkIfKingIsInCheck(this.board, turn)) {
                        System.out.println("can't go there your king will be in check");
                        board[sourceCoords[0]][sourceCoords[1]] = board[destCoords[0]][destCoords[1]];
                        board[destCoords[0]][destCoords[1]] = null;
                        print();
                    } else {

                        print();
                        // check enemy king after moving
                        if (checkIfKingIsInCheck(this.board, turn + 1)) {
                            System.out.println("Your Pieces.King is in check");
                        }

                        turn++;
                    }
                }
            }


        }

    }


    public boolean checkIfKingIsInCheck(Piece[][] board, int turn) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < BOARD_RANKS; i++) {
            for (int j = 0; j < BOARD_FILES; j++) {
                if (turn % 2 == 0 && (board[i][j] instanceof King) && !board[i][j].isWhite()) {
                    x = i;
                    y = j;
                }
                if (turn % 2 != 0 && board[i][j] instanceof King && board[i][j].isWhite()) {
                    x = i;
                    y = j;
                }

            }
        }
        return enemyLocations(this.board, x, y, turn);
    }

    private boolean enemyLocations(Piece[][] board, int x, int y, int turn) {
        boolean color;
        boolean check = false;
        if (turn % 2 == 0) {
            color = false;
        } else {
            color = true;
        }
        outerloop:
        for (int i = 0; i < BOARD_RANKS; i++) {
            for (int j = 0; j < BOARD_FILES; j++) {
                if (board[i][j] != null && board[i][j].isWhite() != color) {
                    check = matrixHasThisLocation(board[i][j].reachableCoordinates(board, i, j), x, y);
                    if (check) {
                        break outerloop;
                    }
                }
            }
        }
        return check;
    }

    private boolean matrixHasThisLocation(int[][] array, int x, int y) {
        boolean check = false;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][0] == x && array[i][1] == y) {
                    check = true;
                }
            }
        }
        return check;
    }


    public void print() {
        for (int i = 0; i < BOARD_RANKS; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < BOARD_FILES; j++) {
                if (board[i][j] != null) {
                    System.out.print("[" + board[i][j] + "]");
                } else {
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
        System.out.println("   A   B   C   D   E   F   G   H ");
        System.out.println();
    }

    public int[] squareToCoords(String s) {
        return new int[]{56 - s.charAt(1), s.charAt(0) - 97};
    }

    public void printHighlighted(int sourceX, int sourceY) {
        if (board[sourceX][sourceY] == null) return;

        for (int i = 0; i < BOARD_RANKS; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < BOARD_FILES; j++) {
                if (isSquareReachable(sourceX, sourceY, i, j)) {
                    if (board[i][j] != null) {
                        System.out.print("\u001b[31m[" + board[i][j] + "]\u001b[0m");

                    } else {
                        System.out.print("\u001b[31m[ ]\u001b[0m");
                    }
                } else {
                    if (board[i][j] != null) {
                        System.out.print("[" + board[i][j] + "]");

                    } else {
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
    }

    public boolean isSquareReachable(int sourceX, int sourceY,
                                      int destinationX, int destinationY) {

        if (board[sourceX][sourceY] == null) return false;
        int[][] reachableFrom =
                board[sourceX][sourceY].reachableCoordinates
                        (this.board, sourceX, sourceY);
       /* if(board[sourceX][sourceY]instanceof Pieces.King) {

        }else {*/
        for (int i = 0; i < reachableFrom.length; i++) {
            if (reachableFrom[i][0] == destinationX
                    && reachableFrom[i][1] == destinationY) {
                return true;
            }
        }


        return false;
    }

    public int[][] getPieceLocations(int x, int y) {
        return board[x][y].reachableCoordinates(this.board, x, y);
    }
}