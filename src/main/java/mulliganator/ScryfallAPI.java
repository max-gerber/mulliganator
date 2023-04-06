package mulliganator;

import java.io.*;
import java.net.*;
import org.json.*;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ScryfallAPI {

    // We want to determine if the card is a land, ramp spell or neither
    public static Card getCard(String cardName) {
        JSONObject cardInfo = getCardInfo(cardName);

        if (producesMana(cardInfo)) {
            String colours = getColours(cardInfo);
            
            if (cardInfo.has("card_faces")) {
                if (isModalDoubleFacedLand(cardInfo)) {
                    return new Land(colours);
                }
                cardInfo = getFrontFace(cardInfo);
            }
            if (!isCheapEnough(cardInfo)) {
                return new Spell();
            }
            if (isLand(cardInfo)) {
                return new Land(colours);
            }
            return new Ramp(colours);
        }

        if (!isCheapEnough(cardInfo)) {
            return new Spell();
        }
        if (tutorsLandIntoPlay(cardInfo)) {
            if (isLand(cardInfo)) {
                return new Land("wubrg");
            }
            return new Ramp("wubrg");
        }
        if(isCostReducer(cardInfo)) {
            return new Ramp("c");
        }
        if (isUntapper(cardInfo)) {
            return new Ramp("wubrg");
        }
        return new Spell();
    }

    private static boolean isUntapper(JSONObject cardInfo) {
        return ((String) cardInfo.get("oracle_text")).toLowerCase().contains("{t}: untap") && 
        (((String) cardInfo.get("oracle_text")).toLowerCase().contains("target snow") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("target artfact") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("target gate") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("target forest") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("target land") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("target permanent")) &&
        // We don't consider anything with a cost other than just tapping to be ramp
        !((String) cardInfo.get("oracle_text")).toLowerCase().contains(", {t}");
    }

    private static boolean tutorsLandIntoPlay(JSONObject cardInfo) {
        return ((String) cardInfo.get("oracle_text")).toLowerCase().contains("search your library") && 
        (((String) cardInfo.get("oracle_text")).toLowerCase().contains("land") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("plains") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("island") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("swamp") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("mountain") ||
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("forest")) &&
        ((String) cardInfo.get("oracle_text")).toLowerCase().contains("onto the battlefield");
    }

    private static boolean isCheapEnough(JSONObject cardInfo) {
        return (Double) cardInfo.get("cmc") <= 3;
    }

    private static boolean isLand(JSONObject cardInfo) {
        return ((String) cardInfo.get("type_line")).contains("Land");
    }

    private static boolean isModalDoubleFacedLand(JSONObject cardInfo) {
        return (cardInfo.get("layout") == "modal_dfc" && isLand(cardInfo));
    }

    private static boolean isCostReducer(JSONObject cardInfo) {
        return (((String) cardInfo.get("oracle_text")).toLowerCase().contains("spells you cast cost") && 
            (((String) cardInfo.get("oracle_text")).toLowerCase().contains("less to cast")));
    }

    private static JSONObject getFrontFace(JSONObject cardInfo) {
        return ((JSONArray) cardInfo.get("card_faces")).getJSONObject(0);
    }

    private static String getColours(JSONObject cardInfo) {
        String colours = "";
        for (Object colour : (JSONArray) cardInfo.get("produced_mana")) {
            colours += colour.toString().toLowerCase();
        }
        return colours;
    }

    private static boolean producesMana(JSONObject cardInfo) {
        return cardInfo.has("produced_mana");
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
