package org.example;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {
    @DataProvider(name = "dataProviderInvalidInput")
    public static Object[][] createInvalidInput() {
        return new Object[][]{
                {"2*x$+10=0"},
                {"2*x@+8=9"},
                {"6*y-8y-7=0"},
                {"9&5*x=9"},
                {"5.6*x*x-(3*x+10)k=67.5"},
                {"5*x+56.9c=67b"}};
    }

    @DataProvider(name = "dataProviderValidInput")
    public static Object[][] createValidInput() {
        return new Object[][]{
                {"2*x+10=0"},
                {"2*x+8=9"},
                {"6*x*x+8*x-7=0"},
                {"9.5*x=9"},
                {"5.6*x*x-(3*x+10)/x=67.5"},
                {"5*x+56.9/x=67x"}};
    }

    @DataProvider(name = "dataProviderTwoMathSignsCloseNegative")
    public static Object[][] createEquationWithTwoMathSignsClose() {
        return new Object[][]{
                {"2*x+-10=0"},
                {"2*x+/8=9"},
                {"6*x*x+8*x-/7=0"},
                {"9.5/*x=9"},
                {"5.6*x*x-(3*x+*10)/x=67.5"},
                {"3/x-+2*x*x=8.79"},
                {"5.43*/2.98*x=78*x*x"},
                {"54*x-9()-76=0"},
                {"x++78-97=0"},
                {"45--(2/x*x-98.6)=76"},
                {"x//87=0"},
                {"3**x=7"},
                {"5*x-*56.9/x=67x"}};
    }

    @DataProvider(name = "dataProviderTwoMathSignsClosePositive")
    public static Object[][] createEquationWithoutTwoMathSignsClose() {
        return new Object[][]{
                {"2*x+10=0"},
                {"2*x+8=9"},
                {"6*x*x+8*x-7=0"},
                {"9.5*x=9"},
                {"5.6*x*x-(3*x*10)/x=67.5"},
                {"3/x-2*x*x=8.79"},
                {"5*x-56.9/x=67x"}};
    }

    @DataProvider(name = "dataProviderWithInvalidBrackets")
    public static Object[][] createEquationWithInvalidBrackets() {
        return new Object[][]{
                {"2*(x+10=0"},
                {"2*x+8)=9"},
                {"6*((x*x+8)*x-7=0"},
                {"(9.5*x))=9"},
                {"5.6*x*x-(3*x*10))/x=67.5"},
                {"5*((x-56.9/x=67x"}};
    }

    @DataProvider(name = "dataProviderWithValidBrackets")
    public static Object[][] createEquationWithValidBrackets() {
        return new Object[][]{
                {"2*(x+10)=0"},
                {"2*(x+8)=9"},
                {"6*((x*x)+8)*x-7=0"},
                {"3*x*x+(9.5*(2-x))=9"},
                {"5.6*x*x-3*x*10/x=67.5"},
                {"5*(x-56.9)/x=67x"}};
    }

    @DataProvider(name = "dataProviderWithInvalidEquation")
    public static Object[][] createInvalidEquation() {
        return new Object[][]{
                {"2*(x+10=0"},
                {"2*(x+*8)=9"},
                {"6*((x*x)+8)*a-7=0"},
                {"3*x#x+(9.5*(2-x))=9"},
                {"5,6*x*x-3*x*10/x=67.5"},
                {"5*(x-/56.9)/x=67x"}};
    }

    @DataProvider(name = "dataProviderWithValidEquation")
    public static Object[][] createValidEquation() {
        return new Object[][]{
                {"2*(x+10)=0"},
                {"2*(x+8)=9"},
                {"6*((x*x)+8)*x-7=0"},
                {"3*x+x+(9.5*(2-x))=9"},
                {"5.6*x*x-3*x*10/x=67.5"},
                {"5*(x-56.9)/x=67x"}};
    }

    @DataProvider(name = "dataProviderWithValidRoot")
    public static Object[][] createValidRoot() {
        return new Object[][]{
                {"2*(2*x+5)+5=10", -1.25},
                {"2*x-10=0", 5},
                {"2*x+5=17", 6},
                {"-1.3*5/x=1.2", -5.4166666667},
                {"2*x*x=10", 2.2360679775},
                {"17=2*x+5", 6}};
    }

    @DataProvider(name = "dataProviderWithInvalidRoot")
    public static Object[][] createInvalidRoot() {
        return new Object[][]{
                {"2*(2*x+5)+5=10", -5},
                {"2*x-10=0", 5.6},
                {"2*x+5=17", -3.5},
                {"-1.3*5/x=1.2", -5.4},
                {"2*x*x=10", 2.2},
                {"17=2*x+5", 11}};
    }
}
