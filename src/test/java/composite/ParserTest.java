package composite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Tests for parser
 */
public class ParserTest 
{
    @ParameterizedTest
    @CsvFileSource(resources = "test.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(
        String inputExpression, String expected) {
        
        double outcome;
        double expectedOutcome;

        System.out.println("New Test - AST from DFS Leaves to Root:");

        Parser mainParser = new Expression(inputExpression);
        outcome = mainParser.eval();

        expectedOutcome = Double.parseDouble(expected);
        assertEquals(expectedOutcome, outcome);

        // Test
    }
}
