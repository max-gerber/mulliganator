package mulliganator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ScryfallAPI {

    public static String manaProduced(String cardName) {

        String colours = "";
        JSONObject card = getCard(cardName);

        if (card.has("produced_mana")) {

            if (card.has("card_faces") && card.get("layout") != "modal_dfc") {
                return colours;
            }

            JSONArray colourArray = (JSONArray) card.get("produced_mana");

            for (Object manaSymbol : colourArray) {
                colours += manaSymbol.toString();
            }
        }

        return colours;
    }

    private static JSONObject getCard(String cardName) {

        String host = "https://api.scryfall.com/";
        String endpoint = "cards/named";

        try {

            String query = String.format("fuzzy=%s",URLEncoder.encode(cardName, "UTF-8"));

            try {

                return Unirest.get(host + endpoint + "?" + query).asJson().getBody().getObject();
                
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
