package composite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for parser
 */
public class TestParser {

    @Test
    void allOperations() {
        Parser mainParser = new Expression("2+7*4-1/1");
        double outcome = mainParser.eval();
        assertEquals(29.0, outcome);
    }

    @Test
    void additionSubtraction() {
        Parser mainParser = new Expression("2+7+1+4");
        double outcome = mainParser.eval();
        assertEquals(14, outcome);
    }
}
