import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Object[] options = {"GUI",
                "Console"};
        int n = JOptionPane.showOptionDialog(new JFrame(),
                "Would you like GUI Chess and Console?",
                "A Silly Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]);
        switch (n){
            case 0:
                ChessUI chessUI = new ChessUI();
                break;
            case 1:
                Chess myGame = new Chess();
                myGame.play();
                break;
            default:

        }









    }
}
