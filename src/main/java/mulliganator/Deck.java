package mulliganator;

import java.util.ArrayList;

public class Deck {
    int size = 99;
    int lands = 34;

    int whiteLands = 7;
    int blueLands = 7;
    int blackLands = 0;
    int redLands = 0;
    int greenLands = 0;
    int colourlessLands = 6;

    int azoriousLands = 14;
    int orzhovLands = 0;
    int borosLands = 0;
    int selesnyaLands = 0;
    int dimirLands = 0;
    int izzetLands = 0;
    int simicLands = 0;
    int rakdosLands = 0;
    int golgariLands = 0;
    int gruulLands = 0;

    int esperLands = 0;
    int jeskaiLands = 0;
    int bantLands = 0;
    int marduLands = 0;
    int abzanLands = 0;
    int nayaLands = 0;
    int grixisLands = 0;
    int sultaiLands = 0;
    int temurLands = 0;
    int jundLands = 0;

    int rainbowLands = 0;
    
    int whiteRamp = 1;
    int blueRamp = 1;
    int blackRamp = 0;
    int redRamp = 0;
    int greenRamp = 0;
    int colourlessRamp = 6;

    int azoriousRamp = 3;
    int orzhovRamp = 0;
    int borosRamp = 0;
    int selesnyaRamp = 0;
    int dimirRamp = 0;
    int izzetRamp = 0;
    int simicRamp = 0;
    int rakdosRamp = 0;
    int golgariRamp = 0;
    int gruulRamp = 0;

    int esperRamp = 0;
    int jeskaiRamp = 0;
    int bantRamp = 0;
    int marduRamp = 0;
    int abzanRamp = 0;
    int nayaRamp = 0;
    int grixisRamp = 0;
    int sultaiRamp = 0;
    int temurRamp = 0;
    int jundRamp = 0;

    int rainbowRamp = 0;

    ArrayList<Card> library = new ArrayList<Card>();

    public Deck() {
        for (int i = 0; i < whiteLands; i++) {
        library.add(new Land("w"));
        }
        for (int i = 0; i < blueLands; i++) {
            library.add(new Land("u"));
        }
        for (int i = 0; i < blackLands; i++) {
            library.add(new Land("b"));
        }
        for (int i = 0; i < redLands; i++) {
            library.add(new Land("r"));
        }
        for (int i = 0; i < greenLands; i++) {
            library.add(new Land("g"));
        }
        for (int i = 0; i < colourlessLands; i++) {
            library.add(new Land("c"));
        }
        for (int i = 0; i < azoriousLands; i++) {
            library.add(new Land("wu"));
        }
        for (int i = 0; i < orzhovLands; i++) {
            library.add(new Land("wb"));
        }
        for (int i = 0; i < borosLands; i++) {
            library.add(new Land("wr"));
        }
        for (int i = 0; i < selesnyaLands; i++) {
            library.add(new Land("wg"));
        }
        for (int i = 0; i < dimirLands; i++) {
            library.add(new Land("ub"));
        }
        for (int i = 0; i < izzetLands; i++) {
            library.add(new Land("ur"));
        }
        for (int i = 0; i < simicLands; i++) {
            library.add(new Land("ug"));
        }
        for (int i = 0; i < rakdosLands; i++) {
            library.add(new Land("br"));
        }
        for (int i = 0; i < golgariLands; i++) {
            library.add(new Land("bg"));
        }
        for (int i = 0; i < gruulLands; i++) {
            library.add(new Land("rg"));
        }
        for (int i = 0; i < esperLands; i++) {
            library.add(new Land("wub"));
        }
        for (int i = 0; i < jeskaiLands; i++) {
            library.add(new Land("wur"));
        }
        for (int i = 0; i < bantLands; i++) {
            library.add(new Land("wug"));
        }
        for (int i = 0; i < marduLands; i++) {
            library.add(new Land("wbr"));
        }
        for (int i = 0; i < abzanLands; i++) {
            library.add(new Land("wbg"));
        }
        for (int i = 0; i < nayaLands; i++) {
            library.add(new Land("wrg"));
        }
        for (int i = 0; i < grixisLands; i++) {
            library.add(new Land("ubr"));
        }
        for (int i = 0; i < sultaiLands; i++) {
            library.add(new Land("ubg"));
        }
        for (int i = 0; i < temurLands; i++) {
            library.add(new Land("urg"));
        }
        for (int i = 0; i < jundLands; i++) {
            library.add(new Land("brg"));
        }
        for (int i = 0; i < rainbowLands; i++) {
            library.add(new Land("wubrg"));
        }
        for (int i = 0; i < whiteRamp; i++) {
            library.add(new Ramp("w"));
        }
        for (int i = 0; i < blueRamp; i++) {
            library.add(new Ramp("u"));
        }
        for (int i = 0; i < blackRamp; i++) {
            library.add(new Ramp("b"));
        }
        for (int i = 0; i < redRamp; i++) {
            library.add(new Ramp("r"));
        }
        for (int i = 0; i < greenRamp; i++) {
            library.add(new Ramp("g"));
        }
        for (int i = 0; i < colourlessRamp; i++) {
            library.add(new Ramp("c"));
        }
        for (int i = 0; i < azoriousRamp; i++) {
            library.add(new Ramp("wu"));
        }
        for (int i = 0; i < orzhovRamp; i++) {
            library.add(new Ramp("wb"));
        }
        for (int i = 0; i < borosRamp; i++) {
            library.add(new Ramp("wr"));
        }
        for (int i = 0; i < selesnyaRamp; i++) {
            library.add(new Ramp("wg"));
        }
        for (int i = 0; i < dimirRamp; i++) {
            library.add(new Ramp("ub"));
        }
        for (int i = 0; i < izzetRamp; i++) {
            library.add(new Ramp("ur"));
        }
        for (int i = 0; i < simicRamp; i++) {
            library.add(new Ramp("ug"));
        }
        for (int i = 0; i < rakdosRamp; i++) {
            library.add(new Ramp("br"));
        }
        for (int i = 0; i < golgariRamp; i++) {
            library.add(new Ramp("bg"));
        }
        for (int i = 0; i < gruulRamp; i++) {
            library.add(new Ramp("rg"));
        }
        for (int i = 0; i < esperRamp; i++) {
            library.add(new Ramp("wub"));
        }
        for (int i = 0; i < jeskaiRamp; i++) {
            library.add(new Ramp("wur"));
        }
        for (int i = 0; i < bantRamp; i++) {
            library.add(new Ramp("wug"));
        }
        for (int i = 0; i < marduRamp; i++) {
            library.add(new Ramp("wbr"));
        }
        for (int i = 0; i < abzanRamp; i++) {
            library.add(new Ramp("wbg"));
        }
        for (int i = 0; i < nayaRamp; i++) {
            library.add(new Ramp("wrg"));
        }
        for (int i = 0; i < grixisRamp; i++) {
            library.add(new Ramp("ubr"));
        }
        for (int i = 0; i < sultaiRamp; i++) {
            library.add(new Ramp("ubg"));
        }
        for (int i = 0; i < temurRamp; i++) {
            library.add(new Ramp("urg"));
        }
        for (int i = 0; i < jundRamp; i++) {
            library.add(new Ramp("brg"));
        }
        for (int i = 0; i < rainbowRamp; i++) {
            library.add(new Ramp("wubrg"));
        }
        while (library.size() != 100) {
            library.add(new Spell());
        }
    }
}
