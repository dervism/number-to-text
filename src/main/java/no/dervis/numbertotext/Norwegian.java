package no.dervis.numbertotext;

import java.util.HashMap;
import java.util.Map;

public class Norwegian {

    public static final Map<Integer, String> map = new HashMap<>();
    public static final String EN = "en";
    public static final String ETT = "ett";
    public static final String OG = "og";
    public static final String SPACE = " ";
    public static final String PADDING = SPACE + OG;
    public static final String NONE = "";

    public static Map<Integer, String> getLanguageMap() {
        map.put(0, "null");
        map.put(1, "en");
        map.put(11, "ellve");
        map.put(2, "to");
        map.put(12, "tolv");
        map.put(3, "tre");
        map.put(13, "tretten");
        map.put(4, "fire");
        map.put(14, "fjorten");
        map.put(5, "fem");
        map.put(15, "femten");
        map.put(6, "seks");
        map.put(16, "seksten");
        map.put(7, "syv");
        map.put(17, "sytten");
        map.put(8, "åtte");
        map.put(18, "atten");
        map.put(9, "ni");
        map.put(19, "nitten");

        map.put(10, "ti");
        map.put(20, "tjue");
        map.put(30, "tretti");
        map.put(40, "førti");
        map.put(50, "femti");
        map.put(60, "seksti") ;
        map.put(70, "sytti");
        map.put(80, "åtti");
        map.put(90, "nitti");

        map.put(1_000_000_001, "milliarder");
        map.put(1_000_000_000, "milliard");
        map.put(1_000_001, "millioner");
        map.put(1_000_000, "million");
        map.put(100_000, "hundre tusen");
        map.put(10_000, "tusen");
        map.put(1_000, "tusen");
        map.put(100, "hundre");
        return map;
    }

    public static String enett(int n, int div) {
        String en = map.get(n / div);
        return en.equals(EN) ? Norwegian.ETT : en;
    }
}
