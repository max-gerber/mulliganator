package mulliganator;

import java.io.*;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ScryfallAPI {

    public static Card getCard(String cardName, MulliganConditions mulliganConditions) {

        JSONObject cardInfo = getCardInfo(cardName);

        // We want to determine if the card is a land, ramp spell or neither
        if (cardInfo.has("produced_mana")) {

            String colours = "";
            for (Object colour : (JSONArray) cardInfo.get("produced_mana")) {
                colours += colour.toString().toLowerCase();
            }

            if (cardInfo.has("card_faces")) {

                // Check if card is a MDFC land
                if (cardInfo.get("layout") == "modal_dfc" && ((String) cardInfo.get("type_line")).contains("Land")) {
                    return new Land(colours);
                } else {
                    return new Spell();
                }
            } else {

                if (((String) cardInfo.get("type_line")).contains("Land")) {
                    return new Land(colours);
                // If the spell produces mana check if it is within the threshold for acceptable ramp spell mana value
                } else if ((Double) cardInfo.get("cmc") <= mulliganConditions.maximumRampManaValue) {
                    return new Ramp(colours);
                }
            }
        }
        // Check if the card tutors for lands
        else if (((String) cardInfo.get("oracle_text")).toLowerCase().contains("search your library") && 
            (((String) cardInfo.get("oracle_text")).toLowerCase().contains("land") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("plains") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("island") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("swamp") ||
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("mountain")) &&
            ((String) cardInfo.get("oracle_text")).toLowerCase().contains("onto the battlefield")) {

                if (((String) cardInfo.get("type_line")).contains("Land")) {
                    return new Land("wubrg");
                // Check if spell is within the threshold for acceptable ramp spell mana value
                } else if ((Double) cardInfo.get("cmc") <= mulliganConditions.maximumRampManaValue) {
                    return new Ramp("wubrg");
                }
            }
            // Check for cost reducers
            else if(((String) cardInfo.get("oracle_text")).toLowerCase().contains("spells you cast cost") && 
            (((String) cardInfo.get("oracle_text")).toLowerCase().contains("less to cast")) &&
            (Double) cardInfo.get("cmc") <= mulliganConditions.maximumRampManaValue) {
                return new Ramp("c");
            }

        // If none of these conditions are met we do not count it as land or ramp
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
