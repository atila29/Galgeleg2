/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server;

import brugerautorisation.data.Bruger;
import dtu.galgeleg.game.IGalgeleg;
import brugerautorisation.transport.rmi.*;
import dtu.galgeleg.game.server.galgespellchecker.GalgeSpellChecker;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kongerne
 */
public class GalgelegImpl extends UnicastRemoteObject implements IGalgeleg{
    Brugeradmin ba;

    private Galgelogik logik;
    
    
    public GalgelegImpl() throws java.rmi.RemoteException {
        logik = new Galgelogik();
       
        
        
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (NotBoundException ex) {
            Logger.getLogger(GalgelegImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GalgelegImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

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
        if(logik.getBrugteBogstaver().contains(String.valueOf(letter)))
            return IGalgeleg.STATUS_LETTER_USED;
        logik.gætBogstav(String.valueOf(letter));
        if(logik.erSidsteBogstavKorrekt())
            return IGalgeleg.STATUS_LETTER_CORRECT;
        else
            return IGalgeleg.STATUS_LETTER_WRONG;
    }

    @Override
    public ArrayList<String> getUsedLetters() throws RemoteException {
        return logik.getBrugteBogstaver();
    }

    @Override
    public void resetGame() throws RemoteException {
        logik.nulstil();
    }

    @Override
    public Bruger login(String username, String password) throws RemoteException {
        return ba.hentBruger(username, password);
    }
    
    private void setRandomWordToLogik(String[] words) throws NoSuchFieldException, IllegalAccessException {
        int index = (int)(Math.random()*words.length);
        String nytord = words[index];
        logik.setOrdet(nytord);
        
        Field s = logik.getClass().getDeclaredField("ordet");
        s.setAccessible(true);
        s.set(logik, nytord);
    }

    @Override
    public void newCheckedWord(String s) throws RemoteException {
        try {
            setRandomWordToLogik(GalgeSpellChecker.getCorrectWords(s));
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(GalgelegImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GalgelegImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
