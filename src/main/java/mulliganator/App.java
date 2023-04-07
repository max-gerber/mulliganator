package mulliganator;

import java.io.*;
import java.util.*;

public class App {
    public static void main( String[] args ) throws FileNotFoundException  {
        int numberOfMulligans = 1000000;
        File deckList = new File("deck.txt");

        System.out.println("Getting Card Info From Scryfall.com...");
        Deck deck = new Deck(deckList);
        if (deck.library.size() == 0) {
            System.err.println("!!!Something went wrong importing the deck!!!");
            return;
        }
        // Deck deck = new Deck();

        System.out.println("Performing " + numberOfMulligans + " Mulligans...");
        Map<Integer, Integer> results = performMulligans(deck, numberOfMulligans);

        System.out.println(
            "Kept a 7 card hand " + new Double(results.get(7)) / 10000 + "\\% of the time\n" +
            "Kept a 6 card hand " + new Double(results.get(6)) / 10000 + "\\% of the time\n" +
            "Kept a 5 card hand " + new Double(results.get(5)) / 10000 + "\\% of the time\n" +
            "Had to go down to 4 cards " + new Double(results.get(4)) / 10000 + "\\% of the time");
    }

    private static Map<Integer, Integer> performMulligans(Deck deck, int numberOfMulligans) {

        // Map of how often we kept hands of various sizes
        // key = cards in hand
        // value = number of times we mulliganed to this ammount
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        for (int i = 0; i < numberOfMulligans; i++) {
            int handSize = handSizeAfterMulliganing(deck, 7, deck.library.size() > 60);
            results.merge(handSize, 1, Integer::sum);
        }

        return results;
    }

    private static int handSizeAfterMulliganing(Deck deck, int handSize, boolean freeMulligan) {

        if (handSize < 5) {
            //We will keep any 4 card hand
            return 4;
        }

        Collections.shuffle(deck.library);

        List<Card> hand = deck.library.subList(0, 7);
        Map<String, Integer> manaInHand = manaSourcesInHand(hand);

        if (keepableHand(manaInHand, deck)) {
            return handSize;
        }

        return handSizeAfterMulliganing(deck, freeMulligan ? 7 : handSize - 1, false);
    }

    private static Map<String, Integer> manaSourcesInHand(List<Card> hand) {
        Map<String, Integer> manaSources = new HashMap<String, Integer>() {{
            put("Total mana sources", 0);
            put("Lands", 0);
            put("White sources", 0);
            put("Blue sources", 0);
            put("Black sources", 0);
            put("Red sources", 0);
            put("Green sources", 0);
            put("Colourless sources", 0);
        }};
        hand.forEach(card -> {
            if (card instanceof Land || card instanceof Ramp) {
                manaSources.merge("Total mana sources", 1, Integer::sum);
                manaSources.merge("Lands", card instanceof Land ? 1 : 0, Integer::sum);
                manaSources.merge("White sources", card.white ? 1 : 0, Integer::sum);
                manaSources.merge("Blue sources", card.blue ? 1 : 0, Integer::sum);
                manaSources.merge("Black sources", card.black ? 1 : 0, Integer::sum);
                manaSources.merge("Red sources", card.red ? 1 : 0, Integer::sum);
                manaSources.merge("Green sources", card.green ? 1 : 0, Integer::sum);
                manaSources.merge("Colourless sources", card.colourless ? 1 : 0, Integer::sum);
            }
        });
        return manaSources;
    }

    private static boolean keepableHand(Map<String, Integer> manaInHand, Deck deck) {
        return !(
            manaInHand.get("Lands") < deck.mulliganConditions.minimumLands ||
            manaInHand.get("Total mana sources") < deck.mulliganConditions.minimumManaSources ||
            manaInHand.get("Total mana sources") > deck.mulliganConditions.maximumManaSources ||
            (deck.mulliganConditions.colourIdentity.get("White") && manaInHand.get("White sources") == 0) ||
            (deck.mulliganConditions.colourIdentity.get("Blue") && manaInHand.get("Blue sources") == 0) ||
            (deck.mulliganConditions.colourIdentity.get("Black") && manaInHand.get("Black sources") == 0) ||
            (deck.mulliganConditions.colourIdentity.get("Red") && manaInHand.get("Red sources") == 0) ||
            (deck.mulliganConditions.colourIdentity.get("Green") && manaInHand.get("Green sources") == 0));
    }
}
