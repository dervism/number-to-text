package no.dervis.numbertotext;

import java.util.Map;
import java.util.function.BiFunction;

import static no.dervis.numbertotext.Norwegian.*;

public class Generator {

    public final Map<Integer, String> map = Norwegian.getLanguageMap();

    private BiFunction<String, String, String> trim = (left, right) -> right.isEmpty() ? NONE : left + right;

    private TriFunction<String, String, String, String> and =
            (left, right, combiner) -> left.equals(map.get(0)) ? combiner : left + SPACE + right;

    public String convert(int number) {

        int base = (int) Math.pow(10, (int) Math.log10(number));

        if (base == 1) return tens(number);
        if (base == 10) return tens(number);
        if (base == 100) return hundreds(number);
        if (base == 1_000) return thoundsands(number);
        if (base == 10_000) return tenthousands(number);
        if (base == 100_000) return hundreds_thoundsands(number);
        if (base == 1_000_000) return millions(number);
        if (base == 10_000_000) return millions(number);
        if (base == 100_000_000) return millions(number);
        if (base == 1_000_000_000) return millions(number);

        return NONE;
    }

    public String millions(int n) {
        int divider = n / 1_000_000;

        // calc the common logarithm (10th base)
        // 10^exp => exp 1 = 10, 2 = 100, 3 = 1000, 4 = 100 000 etc.
        int base = (int) Math.pow(10, (int) Math.log10(divider));

        int remainder = n - (1_000_000 * divider);
        int remainderBase = (int) Math.pow(10, (int) Math.log10(remainder));

        String baseString = baseString(divider, base, NONE);
        String ret = baseString + SPACE
                + ( divider > 1 ? map.get(1_000_001) : map.get(1_000_000) )
                + baseString(remainder, remainderBase, baseString);

        return ret.trim();
    }

    private String baseString(int divider, int base, String baseString) {
        String tens = tens(divider);
        if (!baseString.equals(NONE)) tens = SPACE + OG + SPACE + tens;

        if (base == 1) return tens;
        if (base == 10) return tens;
        if (base == 100) return SPACE + hundreds(divider);
        if (base == 1_000) return SPACE + thoundsands(divider);
        if (base == 10_000) return SPACE + tenthousands(divider);
        if (base == 100_000) return SPACE + hundreds_thoundsands(divider);
        return NONE;
    }

    public String hundreds_thoundsands(int n) {
        int divider = n / 1000;
        String hundreds = hundreds(n - (divider * 1_000));
        return hundreds(divider) + SPACE + map.get(1_000) + trim.apply(SPACE, hundreds);
    }

    public String tenthousands(int n) {
        if (n % 10_000 == 0) return tens(n / 1_000) + SPACE + map.get(1_000);
        return and.apply(tens(n / 1_000), map.get(1_000), NONE) + trim.apply( SPACE, hundreds(n % 1_000));
    }

    public String thoundsands(int n) {
        if (n % 1000 == 0) return enett(n, 1_000) + SPACE + map.get(1_000);
        return and.apply(enett(n, 1_000), map.get(1_000), NONE) + trim.apply(SPACE, hundreds(n % 1_000));
    }

    public String hundreds(int n) {
        if (n == 0) return "";
        if (n % 100 == 0) return enett(n, 100) + SPACE + map.get(100);
        return and.apply(enett(n, 100), map.get(100) + PADDING, OG) + SPACE + tens(n % 100);
    }

    public String tens(int n) {
        if (n <= 20 || map.containsKey(n)) return map.get(n);
        return map.get(10 * (n / 10)) + map.get(n % 10);
    }

}
