package mulliganator;

import java.io.*;
import java.util.*;

public class Deck {
    ArrayList<Card> library = new ArrayList<Card>();
    MulliganConditions mulliganConditions = new MulliganConditions();

    public Deck(File decklist) {
        addCardsToLibrary(decklist);
    }

    private void addCardsToLibrary(File deckList) {
        Map<String, Integer> cards = getCardsFromDeckList(deckList);
        
        cards.keySet().forEach(cardName -> {
            for (int i = 0; i < cards.get(cardName); i++) {
                library.add(ScryfallAPI.getCard(cardName));
            }
        });
    }

    private Map<String, Integer> getCardsFromDeckList(File deckList) {
        Map<String, Integer> cards = new HashMap<String, Integer>();

        try {
            Scanner deckScanner = new Scanner(deckList);
            while (deckScanner.hasNextLine()) {
                String[] cardData = deckScanner.nextLine().split(" ", 2);
                cards.put(cardData[1], Integer.parseInt(cardData[0]));
            }
            deckScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
