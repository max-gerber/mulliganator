package mulliganator;

import java.io.*;
import java.util.*;

public class Deck {
    int size = 99;
    ArrayList<Card> library = new ArrayList<Card>();
    MulliganConditions mulliganConditions = new MulliganConditions();

    public Deck(File decklist) {
        try {
            Map<String, Integer> cards = new HashMap<String, Integer>();
            Scanner deckScanner = new Scanner(decklist);
            while (deckScanner.hasNextLine()) {
                String[] cardData = deckScanner.nextLine().split(" ", 2);
                cards.put(cardData[1], Integer.parseInt(cardData[0]));
            }
            deckScanner.close();
            cards.keySet().forEach(cardName -> {
                for (int i = 0; i < cards.get(cardName); i++) {
                    library.add(ScryfallAPI.getCard(cardName));
                }
            });
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
    }
}
