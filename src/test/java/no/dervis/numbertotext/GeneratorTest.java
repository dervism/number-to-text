package no.dervis.numbertotext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 *
 * Created by dervis on 01/05/2017.
 */
public class GeneratorTest {

    private Generator generator;

    @Before
    public void setup() {
        generator = new Generator();
    }

    @Test
    public void testMillions() throws Exception {
        System.out.println(generator.millions(12_000_001));
    }

    @Test
    public void testHundredThoundsands() throws Exception {
        assertEquals(generator.hundreds_thoundsands(100_000), "ett hundre tusen");
        assertEquals(generator.hundreds_thoundsands(100_001), "ett hundre tusen og en");
        assertEquals(generator.hundreds_thoundsands(200_000), "to hundre tusen");
        assertEquals(generator.hundreds_thoundsands(102_000), "ett hundre og to tusen");
        assertEquals(generator.hundreds_thoundsands(202_000), "to hundre og to tusen");
        assertEquals(generator.hundreds_thoundsands(202_001), "to hundre og to tusen og en");
        assertEquals(generator.hundreds_thoundsands(398_000), "tre hundre og nittiåtte tusen");
        assertEquals(generator.hundreds_thoundsands(398_010), "tre hundre og nittiåtte tusen og ti");
        assertEquals(generator.hundreds_thoundsands(398_100), "tre hundre og nittiåtte tusen ett hundre");
        assertEquals(generator.hundreds_thoundsands(589_370), "fem hundre og åttini tusen tre hundre og sytti");
        assertEquals(generator.hundreds_thoundsands(589_371), "fem hundre og åttini tusen tre hundre og syttien");
        assertEquals(generator.hundreds_thoundsands(600_371), "seks hundre tusen tre hundre og syttien");
        assertEquals(generator.hundreds_thoundsands(700_701), "syv hundre tusen syv hundre og en");
        assertEquals(generator.hundreds_thoundsands(999_990), "ni hundre og nittini tusen ni hundre og nitti");
    }

    @Test
    public void testThoundsands() throws Exception {
        assertEquals(generator.thoundsands(1000), "ett tusen");
        assertEquals(generator.thoundsands(2000), "to tusen");
        assertEquals(generator.thoundsands(1101), "ett tusen ett hundre og en");
        assertEquals(generator.thoundsands(2101), "to tusen ett hundre og en");
        assertEquals(generator.thoundsands(1001), "ett tusen og en");
        assertEquals(generator.thoundsands(2001), "to tusen og en");
        assertEquals(generator.thoundsands(2010), "to tusen og ti");
        assertEquals(generator.thoundsands(2100), "to tusen ett hundre");
        assertEquals(generator.thoundsands(3456), "tre tusen fire hundre og femtiseks");
        assertEquals(generator.thoundsands(9999), "ni tusen ni hundre og nittini");
    }

    @Test
    public void testHundreds() throws Exception {
        assertEquals(generator.hundreds(100), "ett hundre");
        assertEquals(generator.hundreds(122), "ett hundre og tjueto");
        assertEquals(generator.hundreds(112), "ett hundre og tolv");
        assertEquals(generator.hundreds(101), "ett hundre og en");
        assertEquals(generator.hundreds(200), "to hundre");
        assertEquals(generator.hundreds(222), "to hundre og tjueto");
        assertEquals(generator.hundreds(201), "to hundre og en");
        assertEquals(generator.hundreds(356), "tre hundre og femtiseks");
        assertEquals(generator.hundreds(370), "tre hundre og sytti");
    }

    @Test
    public void testTens() throws Exception {
        assertEquals(generator.tens(1), "en");
        assertEquals(generator.tens(2), "to");
        assertEquals(generator.tens(12), "tolv");
        assertEquals(generator.tens(22), "tjueto");
        assertEquals(generator.tens(99), "nittini");
        assertEquals(generator.tens(30), "tretti");
        assertEquals(generator.tens(70), "sytti");
        assertEquals(generator.tens(74), "syttifire");
    }

    @Test
    @Ignore
    public void test() {
        String s = "12312";
        String x = generator.convert(toList(s), s.length(), "");
        System.out.println("Converted: " + x);
    }

    private List<String> toList(String s) {
        return Arrays.stream(s.split("")).collect(Collectors.toList());
    }

}