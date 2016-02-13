/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game;

import java.util.ArrayList;

/**
 *
 * @author herErEtNavn
 */
public interface IGalgeleg extends java.rmi.Remote{
    String getVisibleWord() throws java.rmi.RemoteException;
    int getStatus() throws java.rmi.RemoteException;
    int guessLetter(char letter) throws java.rmi.RemoteException;
    ArrayList<String> getUsedLetters() throws java.rmi.RemoteException;
    void resetGame() throws java.rmi.RemoteException;
}