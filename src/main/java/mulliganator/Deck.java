package mulliganator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deck {
    int size = 99;

    Map<String, Integer> lands = new HashMap<String, Integer>() {{
        put("c", 5);

        put("w", 0);
        put("u", 10);
        put("b", 9);
        put("r", 0);
        put("g", 0);

        put("wu", 0);
        put("wb", 0);
        put("wr", 0);
        put("wg", 0);
        put("ub", 11);
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

    ArrayList<Card> library = new ArrayList<Card>();

    public Deck(File decklist) {
    }

    public Deck() {
        for (Object colourIdentity : lands.keySet()) {
            for (int i = 0; i < lands.get(colourIdentity); i++) {
                library.add(new Land(colourIdentity.toString()));
            }
        }
        for (Object colourIdentity : ramp.keySet()) {
            for (int i = 0; i < ramp.get(colourIdentity); i++) {
                library.add(new Ramp(colourIdentity.toString()));
            }
        }
        while (library.size() != 100) {
            library.add(new Spell());
        }
    }
}
