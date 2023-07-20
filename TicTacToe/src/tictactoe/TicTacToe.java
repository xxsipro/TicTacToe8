/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author adlerjo
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static char[][] spielfeld = fillArray();
    public boolean XTrue = true;
    public static int number = 1;
    public static boolean gameover = false;
    public static char playeronesymb;
    public static char playertwosymb;
    public static String firstplayer;
    public static String secondplayer;
    public static boolean whostarts;
    public static char symbol;
    public static boolean isFull = false;
    public static boolean isDraw = false;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here

        //TODO: Diagonal Prüfen fertigstellen
        //fix Vertical
        //Ignorieren leerer Felder
        //Prüfen ob Feld voll
        //User Inputs
        startplayer();

        while (gameover == false & isDraw == false) {
            if (number % 2 == 0) {
                computer(spielfeld);
            } else {
                chooseField();

            }

            if (playeronesymb == playertwosymb) {
                System.out.println("Ist gleich");
            }

            number++;
            System.out.println("");
            System.out.println("--------------------------");
            System.out.println("");
            printArray(spielfeld);
            if (checkifgame()) {
                if (isDraw) {
                    System.out.println("Das Spiel ist untentschieden");
                    System.out.println("Das spiel ist zu Ende. Möchtest du nochmal spielen?");
                } else if (whostarts == false) {
                    System.out.println("Spieler " + firstplayer + " hat gewonnen!");
                    System.out.println("Das spiel ist zu Ende. Möchtest du nochmal spielen?");
                } else {
                    System.out.println("Spieler " + secondplayer + " hat gewonnen!");
                    System.out.println("Das spiel ist zu Ende. Möchtest du nochmal spielen?");
                }
                String yesno = sc.next().toLowerCase();
                exit(yesno);

            } else {

            }
        }

    }

    public static void computer(char[][] spielfeld) {
        if (spielfeld[1][1] == ' ') {
            placetoe(spielfeld, 1, 1);

        } else {
            checkCounterOption();
        }
    }

    public static void placetoe(char[][] spielfeld, int x, int y) {
        char symbol = playeronesymb;
        if (number % 2 == 0) {
            symbol = playertwosymb;
            whostarts = true;

        } else {
            whostarts = false;
        }
        if (spielfeld[x][y] == ' ') {
            spielfeld[x][y] = symbol;
        } else {
            System.out.println("Sorry, das ist schon belegt.");
            System.out.println("");
            System.out.println("--------------------------");
            System.out.println("");
            printArray(spielfeld);
            if(whostarts == false){
                chooseField();
            }
            else{
                computer(spielfeld);
            }
            
        }

    }

    public static void chooseField() {
        System.out.println("Guten tag, welche Tabellenzahl möchtest du eintragen?");
        int x = sc.nextInt();
        int y = sc.nextInt();

        placetoe(spielfeld, x - 1, y - 1);
    }

    public static void startplayer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wie heißt der erste Spieler");
        firstplayer = sc.next().toLowerCase();
        System.out.println("Wie heißt der zweite Spieler");
        secondplayer = sc.next().toLowerCase();
        System.out.println("Welches Symbol möchte der erste Spieler sein?");
        playeronesymb = sc.next().charAt(0);
        System.out.println("Welches Symbol möchte der zweite Spieler sein?");
        playertwosymb = sc.next().charAt(0);
    }

    public static void exit(String yesno) {
        if (yesno.equals("nein")) {
            System.exit(0);
        } else if (yesno.equals("ja")) {
            restartgame();
        } else {
            System.out.println("Das ist eine falsche Eingabe! Hiermit wird das Spiel beendet.");
            System.exit(0);
        }
    }

    public static void restartgame() {
        resetgame(spielfeld);
        gameover = false;
        isDraw = false;

    }

    public static void resetgame(char[][] spielfeld) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int x = 0; x < spielfeld.length; x++) {
                spielfeld[i][x] = ' ';
            }
        }
    }

    public static boolean checkifgame() {
        if (checkHorizontal(spielfeld) || checkVertical(spielfeld) || checkDiagonal(spielfeld) || checkDiagonalr(spielfeld)) {
            return true;
        } else if (checkiffull(spielfeld)) {
            isDraw = true;
            return isDraw;
        }
        return false;

    }

    public static boolean checkiffull(char[][] spielfeld) {
        boolean isFull = true;
        for (int i = 0; i < spielfeld.length; i++) {
            for (int x = 0; x < spielfeld.length; x++) {
                if (spielfeld[i][x] == ' ') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public static char[][] fillArray() {
        char[][] spielfeld = new char[3][3];
        for (int i = 0; i < spielfeld.length; i++) {
            for (int x = 0; x < spielfeld[0].length; x++) {
                spielfeld[i][x] = ' ';
            }
        }
        return spielfeld;
    }

    public static void printArray(char[][] multidimensionalIntArr) {
        for (int i = 0; i < multidimensionalIntArr.length; i++) {

            for (int x = 0; x < multidimensionalIntArr[0].length; x++) {
                System.out.print("[" + multidimensionalIntArr[i][x] + "]");
            }
            System.out.println();
        }
    }

    /*  public static boolean checkHorizontal(char[][] spielfeld) {
            for (int i = 0; i < spielfeld.length; i++) {
                for (int x = 0; x < spielfeld.length; x++) {
                    if (spielfeld[i][x] == (spielfeld[i][2]) && spielfeld[i][1] == (spielfeld[i][2])) {
                        return true;
                    }
                }
            }
            return false;
        }*/
    public static boolean checkHorizontal(char[][] spielfeld) {
        boolean isCorrect = true;
        for (int i = 0; i < spielfeld.length; i++) {
            isCorrect = true;
            for (int x = 0; x < spielfeld.length - 1; x++) {
                if (spielfeld[i][x] != spielfeld[i][x + 1] || spielfeld[i][x] == (' ')) {
                    isCorrect = false;
                }
            }
            if (isCorrect) {
                return isCorrect;
            }
        }
        return isCorrect;
    }

    public static boolean checkVertical(char[][] spielfeld) {
        boolean isCorrect = true;
        for (int x = 0; x < spielfeld.length; x++) {
            isCorrect = true;
            for (int i = 0; i < spielfeld.length - 1; i++) {
                isCorrect = true;

                if (spielfeld[i][x] != spielfeld[i + 1][x] || spielfeld[i][x] == ' ') {
                    isCorrect = false;
                }

            }
            if (isCorrect) {
                return isCorrect;

            }
        }
        return isCorrect;
    }

    /*  public static boolean checkVictory(char[][] spielfeld) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int x = 0; x < spielfeld.length; x++) {
                if (spielfeld[i][x]) {
                    
                }
            }
        }
        return true;

    }*/

 /* public static boolean checkDiagonal(char[][] spielfeld) {
            boolean isCorrect = true;
            for (int x = 0; x < spielfeld.length-1; x++) {
                isCorrect = true;
                for (int i = 0; i < spielfeld.length-1; i++) {
                    isCorrect = true;
                if (spielfeld[i][x] != spielfeld[i+1][x+1]) {
                    isCorrect = false;
                }
            }
            }
            return isCorrect;
        }*/
    public static boolean checkDiagonal(char[][] spielfeld) {
        for (int i = 0; i < spielfeld.length - 1; i++) {
            if (spielfeld[i][i] != spielfeld[i + 1][i + 1] || spielfeld[i][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonalr(char[][] spielfeld) {
        int counter = spielfeld.length - 1;
        for (int i = 0; i < spielfeld.length - 1; i++) {
            if (spielfeld[i][counter] != spielfeld[i + 1][counter - 1] || spielfeld[i][counter] == ' ') {
                return false;
            }
            counter--;

        }
        return true;
    }

    public static void checkCounterOption() {
    Random rd = new Random();
    int i = checkCountVertical();
    int o = checkCountHorizontal();
    if(o != -1){
        int emptyField = getEmptyField(o, -1);
        if (emptyField != -1) {
            placetoe(spielfeld, o, emptyField);
            return;
        }
    }
    if(i != -1){
        int emptyField = getEmptyField(i, -1);
        if (emptyField != -1) {
            placetoe(spielfeld, emptyField, i);
            return;
        }
    }

    placetoe(spielfeld, rd.nextInt(3), rd.nextInt(3));
}

public static int getEmptyField(int i, int o) {
    if (i < 0 && o < 0) {
        return -1;
    }
    if (i >= 0) {
        for (int x = 0; x < spielfeld.length; x++) {
            if (spielfeld[x][i] == ' ') {
                return x;
            }
        }
    }
    if (o >= 0) {
        for (int j = 0; j < spielfeld[0].length; j++) {
            if (spielfeld[o][j] == ' ') {
                return j;
            }
        }
    }
    return -1;
}

public static int checkCountVertical() {
    int counter = 0;
    for (int i = 0; i < spielfeld.length; i++) {
        counter = 0;
        for (int x = 0; x < spielfeld[0].length; x++) {
            if (spielfeld[x][i] == playeronesymb) {
                counter++;
            } else if(spielfeld[x][i] == playertwosymb) {
                counter--;
            }
        }
        if (counter == 2) {
            return i;
        }
    }
    return -1;
}

public static int checkCountHorizontal() {
    int counter = 0;
    for (int y = 0; y < spielfeld.length; y++) {
        counter = 0;
        for (int x = 0; x < spielfeld[0].length; x++) {
            if (spielfeld[y][x] == playeronesymb) {
                counter++;
            } else if (spielfeld[y][x] == playertwosymb) {
                counter--;
            }
        }
        if (counter == 2) {
            return y;
        }
    }
    return -1;
}
}
