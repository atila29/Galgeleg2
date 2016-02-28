/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server.galgespellchecker;

import com.cdyne.ws.DocumentSummary;
import java.util.List;

/**
 *
 * @author champen
 */
public class GalgeSpellChecker {

    /**
     * @param word
     * @param args the command line arguments
     * @return 
     */
    
    public static String[] getCorrectWords(String word) throws IllegalArgumentException {
        DocumentSummary doc = checkTextBodyV2(word);
        List<String> temp = null;
        for(com.cdyne.ws.Words w : doc.getMisspelledWord()) {
            temp = w.getSuggestions();
        }
        
        if(temp == null)
            throw new IllegalArgumentException("Fejl : ikke muligt at rette ordet!");
        String[] ar = new String[temp.size()];
        ar = temp.toArray(ar);
        return ar;
    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap12();
        return port.checkTextBodyV2(bodyText);
    }


}
