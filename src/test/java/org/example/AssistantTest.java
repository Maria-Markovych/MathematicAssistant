package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AssistantTest {
    @Test(dataProvider = "dataProviderInvalidInput", dataProviderClass = StaticDataProvider.class)
    void testInvalidInput(String equation) {
        assertFalse(Assistant.isValidInput(equation));
    }

    @Test(dataProvider = "dataProviderValidInput", dataProviderClass = StaticDataProvider.class)
    void testValidInput(String equation) {
        assertTrue(Assistant.isValidInput(equation));
    }

    @Test(dataProvider = "dataProviderTwoMathSignsCloseNegative", dataProviderClass = StaticDataProvider.class)
    void testTwoMathSignsCloseNegative(String equation) {
        assertFalse(Assistant.twoMathSignsClose(equation));
    }

    @Test(dataProvider = "dataProviderTwoMathSignsClosePositive", dataProviderClass = StaticDataProvider.class)
    void testTwoMathSignsClosePositive(String equation) {
        assertTrue(Assistant.twoMathSignsClose(equation));
    }

    @Test(dataProvider = "dataProviderWithInvalidBrackets", dataProviderClass = StaticDataProvider.class)
    void testInvalidBrackets(String equation) {
        assertFalse(Assistant.invalidBrackets(equation));
    }

    @Test(dataProvider = "dataProviderWithValidBrackets", dataProviderClass = StaticDataProvider.class)
    void testValidBrackets(String equation) {
        assertTrue(Assistant.invalidBrackets(equation));
    }

    @Test(dataProvider = "dataProviderWithInvalidEquation", dataProviderClass = StaticDataProvider.class)
    void testInvalidEquation(String equation) {
        assertFalse(Assistant.verifyValidEquation(equation));
    }

    @Test(dataProvider = "dataProviderWithValidEquation", dataProviderClass = StaticDataProvider.class)
    void testValidEquation(String equation) {
        assertTrue(Assistant.verifyValidEquation(equation));
    }

    @Test(dataProvider = "dataProviderWithValidRoot", dataProviderClass = StaticDataProvider.class)
    void testCheckValidRoot(String equation, double root) {
        double actualResult = Assistant.checkIfRoot(equation, root);
        double expectedResult = 0;
        assertEquals(actualResult, expectedResult, 1e-9);
    }

    @Test(dataProvider = "dataProviderWithInvalidRoot", dataProviderClass = StaticDataProvider.class)
    void testCheckInvalidRoot(String equation, double root) {
        double actualResult = Assistant.checkIfRoot(equation, root);
        double expectedResult = 0;
        assertNotEquals(actualResult, expectedResult, 1e-9);
    }
}