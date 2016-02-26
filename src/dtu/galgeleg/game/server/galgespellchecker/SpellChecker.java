/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server.galgespellchecker;

/**
 *
 * @author nicolaihansen
 */
public class SpellChecker {
    
    public static void main(String[] arg) {
        DocumentSummary ds = CheckTextBodyV2("Hillo der world");
		for (Words w : ds.getMisspelledWord()) {
			System.out.println("Forkert ord: "+w.getWord());
			System.out.println("Forslag: "+w.getSuggestions());
		}
	}

    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        dtu.galgeleg.game.server.galgespellchecker.Check service = new dtu.galgeleg.game.server.galgespellchecker.Check();
        dtu.galgeleg.game.server.galgespellchecker.CheckSoap port = service.getCheckSoap12();
        return port.checkTextBodyV2(bodyText);
    }
    
}
