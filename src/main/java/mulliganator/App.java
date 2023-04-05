package mulliganator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args ) {

        int numberOfMulligans = 1000000;
        Deck deck = new Deck();

        Map<Integer, Integer> results = performMulligans(deck, numberOfMulligans);

        System.out.println(
            "Kept a 7 card hand " + new Double(results.get(7)) / 10000 + "\\% of the time\n" +
            "Kept a 6 card hand " + new Double(results.get(6)) / 10000 + "\\% of the time\n" +
            "Kept a 5 card hand " + new Double(results.get(5)) / 10000 + "\\% of the time\n" +
            "Had to go down to 4 cards " + new Double(results.get(4)) / 10000 + "\\% of the time");
    }

    private static Map<Integer, Integer> performMulligans(Deck deck, int numberOfMulligans) {

        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        for (int i = 0; i < numberOfMulligans; i++) {
            int handSize = handSizeAfterMulliganing(deck.library, 7, deck.library.size() == 99);
            results.merge(handSize, 1, Integer::sum);
        }

        return results;
    }

    private static int handSizeAfterMulliganing(ArrayList<Card> library, int handSize, boolean freeMulligan) {

        if (handSize < 5) {
            //We will keep any 4 card hand
            return 4;
        }

        Collections.shuffle(library);
        List<Card> hand = library.subList(0, handSize);

        if (keepableHand(hand, freeMulligan)) {
            return handSize;
        }

        return handSizeAfterMulliganing(library, handSize - 1, false);
    }

    private static boolean keepableHand(List<Card> hand, boolean freeMulligan) {
        int manaSources = 0;
        int lands = 0;
        // int whiteSources = 0;
        int blueSources = 0;
        int blackSources = 0;
        // int redSources = 0;
        // int greenSources = 0;
        for (Card card : hand) {
            if (card instanceof Land || card instanceof Ramp) {
                manaSources++;
                lands += card instanceof Land ? 1 : 0; 
                // whiteSources += card.white ? 1 : 0;
                blueSources += card.blue ? 1 : 0;
                blackSources += card.black ? 1 : 0;
                // redSources += card.red ? 1 : 0;
                // greenSources += card.green ? 1 : 0;
            }
        }
        if (lands < 2 || manaSources < 3 || manaSources > (freeMulligan ? 6 : 5) || blueSources == 0 || blackSources == 0) {
            return false;
        }
        return true;
    }
}
