package mulliganator;

import java.util.*;

public class MulliganConditions {

    int minimumLands =          2;
    int minimumManaSources =    3;
    int maximumManaSources =    5;
    int maximumRampManaValue =  3;
    boolean untapCreatureRamp = false;

    Map<String, Boolean> colourIdentity = new HashMap<String, Boolean>() {{
        put("White", false);
        put("Blue", true);
        put("Black", true);
        put("Red", false);
        put("Green", false);
    }};
}
