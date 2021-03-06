/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.client;

import brugerautorisation.data.Bruger;
import dtu.galgeleg.game.IGalgeleg;
import java.io.Console;
import java.rmi.Naming;
import java.util.Scanner;
import java.util.ArrayList;

public class GameClient {

    public void run() throws Exception {

        IGalgeleg galgeleg = (IGalgeleg) Naming.lookup("rmi://localhost/galgelegnexus");
        galgeleg.resetGame();

        Scanner sc = new Scanner(System.in);
        boolean spilletErIgang = true;

        boolean loggedin = false;
        int round = 0;

        do {
            try {
                System.out.println("Indtast dit brugernavn:");
                String brugernavn = sc.nextLine();
                System.out.println("Indtast dit kodeord:");
                String kodeord = sc.nextLine();
                Bruger bruger = galgeleg.login(brugernavn, kodeord);
                loggedin = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!loggedin);

        while (spilletErIgang) {
            if (round == 0) {
                System.out.println("Indtast et engelsk ord:");
                String word = sc.nextLine();
                galgeleg.newCheckedWord(word);
            }
            
            
            System.out.println(galgeleg.getVisibleWord());

            String temp = "";
            ArrayList<String> usedLetters = galgeleg.getUsedLetters();
            for (int i = 0; i < usedLetters.size(); i++) {
                if (i == 0) {
                    temp = usedLetters.get(0);
                } else {
                    temp += " ," + usedLetters.get(i);
                }
            }
            System.out.println("Brugte bogstaver: " + temp);
            System.out.println("Gæt ved at indtaste et bogstav og afslut med enter:");
            String guess = sc.nextLine();
            while (guess.length() != 1) {
                System.out.println("Fejl: Det indtastede skal være et bogstav. Prøv igen:");
                guess = sc.nextLine();
            }

            int guessValue = galgeleg.guessLetter(guess.charAt(0));

            switch (guessValue) {
                case IGalgeleg.STATUS_LETTER_WRONG:
                    System.out.println("Bogstavet " + guess + " var forkert.");
                    break;
                case IGalgeleg.STATUS_LETTER_USED:
                    System.out.println("Bogstavet " + guess + " er allerede brugt.");
                    break;
                case IGalgeleg.STATUS_LETTER_CORRECT:
                    System.out.println("Bogstavet " + guess + " var korrekt.");
                    break;
                default:
                    break;
            }
            
            round++;

            if (!(galgeleg.getStatus() == IGalgeleg.STATUS_IGANG)) {
                if (galgeleg.getStatus() == IGalgeleg.STATUS_VUNDET) {
                    System.out.println("Tillykke, du vandt!");
                } else if (galgeleg.getStatus() == IGalgeleg.STATUS_TABT) {
                    System.out.println("Desværre, du tabte!");
                }

                System.out.println("Vil du spille igen y/n?");
                String answer = sc.nextLine();

                while (answer.length() != 1) {
                    System.out.println("Vil du spille igen y/n?");
                    answer = sc.nextLine();
                }

                if (!answer.equals("y")) {
                    spilletErIgang = false;
                } else {
                    round = 0;
                    galgeleg.resetGame();
                }
            }
        }
    }

    public static void main(String[] arg) throws Exception {
        GameClient gk = new GameClient();
        gk.run();

    }

}
