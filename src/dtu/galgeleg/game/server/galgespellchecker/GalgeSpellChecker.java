/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server.galgespellchecker;

import com.cdyne.ws.DocumentSummary;
import galgespellchecker.Words;

/**
 *
 * @author champen
 */
public class GalgeSpellChecker {

    /**
     * @param args the command line arguments
     */
    
    public static String[] getCorrectWords(String word) {
        DocumentSummary doc = checkTextBodyV2(word);
        for(com.cdyne.ws.Words w : doc.getMisspelledWord()) {
            return (String[])w.getSuggestions().toArray();
        }
        return null;
    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap12();
        return port.checkTextBodyV2(bodyText);
    }


}
