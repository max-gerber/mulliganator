package mulliganator;

import java.io.*;
import java.util.*;

public class Deck {
    int size = 99;

    MulliganConditions mulliganConditions = new MulliganConditions();

    Map<String, Boolean> colourIdentity = new HashMap<String, Boolean>() {{
        put("White", false);
        put("Blue", true);
        put("Black", true);
        put("Red", false);
        put("Green", false);
    }};

    ArrayList<Card> library = new ArrayList<Card>();

    Map<String, Integer> lands = new HashMap<String, Integer>() {{
        put("c", 6);

        put("w", 0);
        put("u", 11);
        put("b", 9);
        put("r", 0);
        put("g", 0);

        put("wu", 0);
        put("wb", 0);
        put("wr", 0);
        put("wg", 0);
        put("ub", 9);
        put("ur", 0);
        put("ug", 0);
        put("br", 0);
        put("bg", 0);
        put("rg", 0);

        put("wub", 0);
        put("wur", 0);
        put("wug", 0);
        put("wbr", 0);
        put("wbg", 0);
        put("wrg", 0);
        put("ubr", 0);
        put("ubg", 0);
        put("urg", 0);
        put("brg", 0);

        put("wubrg", 0);
    }};
    
    Map<String, Integer> ramp = new HashMap<String, Integer>() {{
        put("c", 3);

        put("w", 0);
        put("u", 0);
        put("b", 0);
        put("r", 0);
        put("g", 0);

        put("wu", 0);
        put("wb", 0);
        put("wr", 0);
        put("wg", 0);
        put("ub", 6);
        put("ur", 0);
        put("ug", 0);
        put("br", 0);
        put("bg", 0);
        put("rg", 0);

        put("wub", 0);
        put("wur", 0);
        put("wug", 0);
        put("wbr", 0);
        put("wbg", 0);
        put("wrg", 0);
        put("ubr", 0);
        put("ubg", 0);
        put("urg", 0);
        put("brg", 0);

        put("wubrg", 0);
    }};

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
                    library.add(ScryfallAPI.getCard(cardName, mulliganConditions));
                }
            });
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
    }

    public Deck() {
        lands.forEach((colour, ammount) -> {
            for (int i = 0; i < ammount; i++) {
                library.add(new Land(colour));
            }
        });
        ramp.forEach((colour, ammount) -> {
            for (int i = 0; i < ammount; i++) {
                library.add(new Ramp(colour));
            }
        });
        while (library.size() != 99) {
            library.add(new Spell());
        }
    }
}
