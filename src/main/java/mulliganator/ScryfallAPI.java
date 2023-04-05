package mulliganator;

import java.io.*;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ScryfallAPI {

    public static Card getCard(String cardName) {

        JSONObject cardInfo = getCardInfo(cardName);

        if (cardInfo.has("produced_mana")) {

            String colours = "";
            for (Object colour : (JSONArray) cardInfo.get("produced_mana")) {
                colours += colour.toString().toLowerCase();
            }

            if (cardInfo.has("card_faces")) {

                if (cardInfo.get("layout") == "modal_dfc" && ((String) cardInfo.get("type_line")).contains("Land")) {
                    return new Land(colours);
                } else {
                    return new Spell();
                }

            } else {

                if (((String) cardInfo.get("type_line")).contains("Land")) {
                    return new Land(colours);
                } else if ((Double) cardInfo.get("cmc") < 4) {
                    return new Ramp(colours);
                }

            }
        }
        else if (((String) cardInfo.get("oracle_text")).toLowerCase().contains("search your library") && 
            (((String) cardInfo.get("oracle_text")).toLowerCase().contains("land") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("plains") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("island") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("swamp") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("mountain")) &&
            (Double) cardInfo.get("cmc") < 4) {

                return new Ramp("wubrg");

            }

        return new Spell();
    }

    private static JSONObject getCardInfo(String cardName) {

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
