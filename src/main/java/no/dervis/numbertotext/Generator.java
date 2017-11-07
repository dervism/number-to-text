package no.dervis.numbertotext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 *
 * Created by dervis on 04/04/2017.
 */
public class Generator {

    public final Map<Integer, String> map = new HashMap<>();
    BiFunction<Integer, Integer, String> enett = (n, div) -> map.get(n / div).equals("en") ? "ett" : map.get(n / div);
    BiFunction<String, String, String> bi = (left, right) -> left.equals("null") ? "og" : left + " " + right;
    BiFunction<String, String, String> bi2 = (left, right) -> left.equals("null") ? "" : left + " " + right;

    public Generator() {
        map.put(0, "null");
        map.put(1, "en"); map.put(11, "ellve");
        map.put(2, "to"); map.put(12, "tolv");
        map.put(3, "tre"); map.put(13, "tretten");
        map.put(4, "fire"); map.put(14, "fjorten");
        map.put(5, "fem"); map.put(15, "femten");
        map.put(6, "seks"); map.put(16, "seksten");
        map.put(7, "syv"); map.put(17, "sytten");
        map.put(8, "åtte"); map.put(18, "atten");
        map.put(9, "ni"); map.put(19, "nitten");

        map.put(10, "ti"); map.put(20, "tjue"); map.put(30, "tretti"); map.put(40, "førti");
        map.put(50, "femti"); map.put(60, "seksti") ;map.put(70, "sytti"); map.put(80, "åtti");
        map.put(90, "nitti");

        map.put(1_000_000_001, "milliarder");
        map.put(1_000_000_000, "milliard");
        map.put(1_000_001, "millioner");
        map.put(1_000_000, "million");
        map.put(100_000, "hundre tusen");
        map.put(10_000, "tusen");
        map.put(1_000, "tusen");
        map.put(100, "hundre");
    }

    // 12310
    public String convert(List<String> list, int pos, String tekst) {

        if (pos == 0) return tekst;

        int digit = Integer.parseInt(list.get(pos-1));
        int ten = (int) Math.pow(10, list.size() - pos);

        String alpha = ten > 0 ? map.get(ten) : "";
        String number = map.get(digit) + " " + alpha;

        System.out.printf("Digit: %s, ten: %s %s \n", digit, ten, number);

        String beta = convert(list, pos - 1, number);

        return beta + " " + tekst;
    }

    public String millions(int n) {
        // calc divider and remainder
        int divider = n / 1_000_000;

        // calc the common logarithm (10th base)
        // 10^exp => exp 1 = 10, 2 = 100, 3 = 1000, 4 = 100 000 etc.
        int base = (int) Math.pow(10, (int) Math.log10(divider));

        int remainder = n - (1_000_000 * divider);
        int remainderBase = (int) Math.pow(10, Math.log10(remainder));

        String ret =
                baseString(divider, base) + " "
                + ( divider > 1 ? map.get(1_000_001) : map.get(1_000_000) ) + " "
                + baseString(remainder, remainderBase);


        System.out.println("n: " + n);
        System.out.println("divider: " + divider);
        System.out.println("remainder: " + remainder);
        System.out.println("exp: " + Math.log10(divider));
        System.out.println("base: " + base);
        System.out.println("remainderBase: " + base);
        System.out.println("ret: " + ret);

        return ret;
    }

    private String baseString(int divider, int base) {
        if (base == 10) return tens(divider);
        if (base == 100) return hundreds(divider);
        if (base == 1_000) return thoundsands(divider);
        if (base == 100_000) return hundreds_thoundsands(divider);
        return "";
    }

    public String hundreds_thoundsands(int n) {
        int divider = n / 1000;
        String hundreds = hundreds(n - (divider * 1_000));
        return hundreds(divider) + " " + map.get(1_000) + (!hundreds.equals("") ? " " : "") + hundreds;
    }

    public String thoundsands(int n) {
        //if (n == 0) return "";
        if (n % 1000 == 0) return enett.apply(n, 1000) + " " + map.get(1000);
        return bi2.apply(enett.apply(n, 1000), map.get(1000)) + " " + hundreds(n % 1000);
    }

    public String hundreds(int n) {
        if (n == 0) return "";
        if (n % 100 == 0) return enett.apply(n, 100) + " " + map.get(100);
        return bi.apply(enett.apply(n, 100), map.get(100) + " og") + " " + tens(n % 100);
    }

    public String tens(int n) {
        //if (n == 0) return "";
        if (n <= 20 || map.containsKey(n)) return map.get(n);
        return map.get(10 * (n / 10)) + map.get(n % 10);
    }

}
