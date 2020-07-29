import java.util.Scanner;

public class ChessConsole {

    public void play() {
        Chess chess = new Chess();
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        boolean gameNotFinished = true;
        String[] userInput;


        chess.print();


        while (gameNotFinished) {
            if (turn % 2 == 0) {
                System.out.print("White's move:");

            } else {
                System.out.print("Black's move:");

            }

            userInput = sc.nextLine().toLowerCase().split(" ");
            System.out.println();

            if (userInput.length == 1) {
                int[] sourceCoords = chess.squareToCoords(userInput[0]);
                chess.printHighlighted(sourceCoords[0], sourceCoords[1]);

            } else {
                if (userInput.length == 2) {

                    int[] sourceCoords = chess.squareToCoords(userInput[0]);
                    int[] destCoords = chess.squareToCoords(userInput[1]);
                    chess.printHighlighted(sourceCoords[0], sourceCoords[1]);


                    if (chess.isSquareReachable(sourceCoords[0], sourceCoords[1],
                            destCoords[0], destCoords[1])) {
                        chess.getBoard()[destCoords[0]][destCoords[1]] = chess.getBoard()[sourceCoords[0]][sourceCoords[1]];
                        chess.getBoard()[sourceCoords[0]][sourceCoords[1]] = null;
                    }
                    //check your king before moving
                    if (chess.checkIfKingIsInCheck(chess.getBoard(), turn)) {
                        System.out.println("can't go there your king will be in check");
                        chess.getBoard()[sourceCoords[0]][sourceCoords[1]] = chess.getBoard()[destCoords[0]][destCoords[1]];
                        chess.getBoard()[destCoords[0]][destCoords[1]] = null;
                        chess.print();
                    } else {

                        chess.print();
                        // check enemy king after moving
                        if (chess.checkIfKingIsInCheck(chess.getBoard(), turn + 1)) {
                            System.out.println("Your Pieces.King is in check");
                        }

                        turn++;
                    }
                }
            }


        }

    }
}
