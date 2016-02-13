/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server;

import dtu.galgeleg.game.IGalgeleg;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author kongerne
 */
public class GalgelegImpl extends UnicastRemoteObject implements IGalgeleg{

    private Galgelogik logik;
    
    public GalgelegImpl() throws java.rmi.RemoteException {
        logik = new Galgelogik();
    }
    
    @Override
    public String getVisibleWord() throws RemoteException {
        return logik.getSynligtOrd();
    }

    @Override
    public int getStatus() throws RemoteException {
        if(!logik.erSpilletSlut())
            return IGalgeleg.STATUS_IGANG;
        else
            if(logik.erSpilletVundet())
                return IGalgeleg.STATUS_VUNDET;
            else
                return IGalgeleg.STATUS_TABT;      
    }

    @Override
    public int guessLetter(char letter) throws RemoteException {
        logik.gætBogstav(String.valueOf(letter));
    }

    @Override
    public ArrayList<String> getUsedLetters() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetGame() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
