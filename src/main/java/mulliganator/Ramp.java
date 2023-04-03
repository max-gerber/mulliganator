package mulliganator;

public class Ramp extends Card {
    // int manaValue;

    public Ramp(String colours) {
        this.white = colours.contains("w");
        this.blue = colours.contains("u");
        this.black = colours.contains("b");
        this.red = colours.contains("r");
        this.green = colours.contains("g");
        this.colourless = colours.contains("c");
    }
}
