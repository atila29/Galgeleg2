/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game;

import brugerautorisation.data.Bruger;
import java.util.ArrayList;

/**
 *
 * @author herErEtNavn
 */
public interface IGalgeleg extends java.rmi.Remote{
    
    static final int STATUS_VUNDET = 2;
    static final int STATUS_TABT = 4;
    static final int STATUS_IGANG = 8;
    static final int STATUS_LETTER_WRONG = 16;
    static final int STATUS_LETTER_CORRECT = 32;
    static final int STATUS_LETTER_USED = 64;
    
    String getVisibleWord() throws java.rmi.RemoteException;
    int getStatus() throws java.rmi.RemoteException;
    int guessLetter(char letter) throws java.rmi.RemoteException;
    ArrayList<String> getUsedLetters() throws java.rmi.RemoteException;
    void resetGame() throws java.rmi.RemoteException;
    
    void newCheckedWord(String s) throws java.rmi.RemoteException;
    
    Bruger login(String username, String password) throws java.rmi.RemoteException;
    
}
