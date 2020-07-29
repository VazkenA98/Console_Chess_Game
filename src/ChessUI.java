import Pieces.Bishop;
import Pieces.Piece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChessUI extends JFrame implements ActionListener {
    private JButton[][] squares = new JButton[8][8];
    private JButton alreadySelected = null;
    private JPanel panel = new JPanel();
    private Chess chess =  new Chess();

    //  private GameController gameController;
    private ArrayList<JButton> highlighted;

    public ChessUI() {
        setLayout(new FlowLayout());
        panel.setBorder(new LineBorder(Color.BLACK));

        // this.gameController = controller;


        highlighted = new ArrayList<JButton>();
        add(panel);
        makeSpaces();
        makeBoard();


        panel.setPreferredSize(new Dimension(570, 600));
        panel.setBackground(new Color(180, 220, 120));
        setSize(700, 700);
        setVisible(true);
    }

    private void makeBoard() {
        // starts in upper left corner

        int currentRow = 8;

        for (int x = 0;x < squares.length; x++) {
            JLabel lab2 = new JLabel(" "+(8-x), SwingConstants.CENTER);
            panel.add(lab2);
            for (int y = 0; y < squares[x].length; y++) {

                JButton curr = squares[x][y];
                panel.add(curr);
            }
            currentRow--;
        }
        JLabel lab = new JLabel("A                   B                   C                   D            " +
                "       E                   F                   G                   H ", SwingConstants.CENTER);
        panel.add(lab);
    }

    private void makeSpaces() {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < squares.length; ii++) {
            for (int jj = 0; jj < squares[ii].length; jj++) {


                JButton b = new JButton();
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setFont(new Font("Arial", Font.PLAIN, 40));
                b.setActionCommand(String.format("%d,%d", ii, jj));
                b.setPreferredSize(new Dimension(64, 64));

                b.setMargin(buttonMargin);
                b.setOpaque(true);
                b.setBorder(new LineBorder(Color.GRAY));
                b.addActionListener(this);

                if ((jj % 2 == 1 && ii % 2 == 0) || (jj % 2 == 0 && ii % 2 == 1)) {

                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                }

                panel.add(b);
                squares[ii][jj] = b;
            }
        }



        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {

                if(chess.getPiece(i,j) != null) {
                    ImageIcon icon = new ImageIcon(chess.getPieceIcon(i, j));
                    squares[i][j].setIcon(icon);
                }

            }

        }

    }

    public void rePaint(){
        for (int ii = 0; ii < squares.length; ii++) {
            for (int jj = 0; jj < squares[ii].length; jj++) {
                if ((jj % 2 == 1 && ii % 2 == 0) || (jj % 2 == 0 && ii % 2 == 1)) {

                    squares[ii][jj].setBackground(Color.WHITE);
                } else {
                    squares[ii][jj].setBackground(Color.GRAY);
                }
            }
        }
    }






















    @Override
    public void actionPerformed(ActionEvent e) {
        // extract coordinates
        Scanner scanf = new Scanner(e.getActionCommand());
        scanf.useDelimiter(",");
         int x = scanf.nextInt();
         int y = scanf.nextInt();

        JButton clicked = squares[x][y];

        if (alreadySelected == null) {
            alreadySelected = clicked;
            clicked.setBorder(new LineBorder(Color.RED, 5));

            highlightPossibleMoves(x, y);

        } else {
            clearSelection();

            scanf = new Scanner(alreadySelected.getActionCommand());
            scanf.useDelimiter(",");
            int toX = scanf.nextInt();
            int toY = scanf.nextInt();

            movePiece(x,y,toX,toY);

            alreadySelected = null;
        }
    }

    private void movePiece(int x, int y, int fromX, int fromY) {
        System.out.println(x+" "+y+" "+fromX+" "+fromY);
        System.out.println(chess.getPiece(x,y));
        if(chess.getPiece(fromX,fromY) != null) {
            ImageIcon icon = new ImageIcon(chess.getPieceIcon(fromX, fromY));
            squares[x][y].setIcon(icon);
            squares[fromX][fromY].setIcon(null);
            chess.moviePiece(fromX,fromY,x,y);
            rePaint();
        }
    }


    private void clearSelection() {
        alreadySelected.setBorder(new LineBorder(Color.GRAY));

        for (JButton high : highlighted) {
            high.setBorder(new LineBorder(Color.GRAY));
        }

        highlighted = new ArrayList<JButton>();
        this.repaint();
    }

   /* public void removePieceAt(BoardLocation loc) {
        this.squares[loc.getX()][loc.getY()].setIcon(null);
        this.squares[loc.getX()][loc.getY()].repaint();
    }

    public void putPiece(Piece piece, BoardLocation loc) {
        this.squares[loc.getX()][loc.getY()].setIcon(piece.getPieceIcon());
        this.squares[loc.getX()][loc.getY()].repaint();
    }

    public void highlightSpace(BoardLocation loc) {
        squares[loc.getX()][loc.getY()].setBorder(new LineBorder(Color.RED, 5));
        highlighted.add(squares[loc.getX()][loc.getY()]);
        this.repaint();
    }*/

    private void highlightPossibleMoves(int x, int y) {
        System.out.println(x+" "+y);
        int [][]pos = chess.getPiece(x,y).reachableCoordinates(chess.getBoard(),x,y);
        System.out.println(Arrays.deepToString(pos));
        for (int i =0 ;i<pos.length;i++){
                squares[pos[i][0]][pos[i][1]].setBackground(Color.RED);
            }

    }

}
