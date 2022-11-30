package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainTest {
    private static List<Arguments> givenInputs() {
        return Arrays.asList(
                Arguments.of(1, 2, 3),
                Arguments.of(12, 23, 35),
                Arguments.of(11, 23, 34),
                Arguments.of(5, 8, 13)
        );
    }

    @ParameterizedTest
    @MethodSource("givenInputs")
    @Order(2)
    void nr1PlusNr2IsEqualNr3(int nr1, int nr2, int expected) {
        int result = Main.calculate(nr1, nr2, Main.Operator.addition);
        //int expected = nr1 + nr2;
        assertEquals(expected, result);
    }

    private static List<Arguments> givenInputsWithDifferentOperator() {
        return Arrays.asList(
                Arguments.of(1, 2, 3, Main.Operator.addition),
                Arguments.of(2, 6, 12, Main.Operator.multiplication),
                Arguments.of(10, 5, 2, Main.Operator.division),
                Arguments.of(5, 8, -3, Main.Operator.subtraction)
        );
    }

    @ParameterizedTest
    @MethodSource("givenInputsWithDifferentOperator")
    @Order(1)
    void calculateDifferentNumbersWithDifferentOperators(int nr1, int nr2, int expected, Main.Operator operator) {
        int result = Main.calculate(nr1, nr2, operator);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @EnumSource(Main.Operator.class)
    @Order(3)
    void calculateWithOperatorAndENUMSource(Main.Operator operator) {
        System.out.println(operator);
        int expected = 5;
        switch (operator) {
            case addition -> expected += 5;
            case subtraction -> expected -= 5;
            case division -> expected /= 5;
            case multiplication -> expected *= 5;
        }
        int result = Main.calculate(5, 5, operator);
        assertEquals(expected, result);
    }

    @Order(5)
    @ParameterizedTest
    @EnumSource(value = Main.Operator.class, names = {"addition", "subtraction"})
    void calculateWithOperatorAndENUMSourceOnlyAdditionAndSubtraction(Main.Operator operator) {
        System.out.println(operator);
        int expected = 5;
        switch (operator) {
            case addition -> expected += 5;
            case subtraction -> expected -= 5;
        }
        int result = Main.calculate(5, 5, operator);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @EnumSource(value = Main.Operator.class, names = {"addition", "subtraction"}, mode = EnumSource.Mode.EXCLUDE)
    void calculateWithOperatorAndENUMSourceOWithoutAdditionAndSubtraction(Main.Operator operator) {
        System.out.println(operator);
        int expected = 5;
        switch (operator) {
            case division -> expected /= 5;
            case multiplication -> expected *= 5;
        }
        int result = Main.calculate(5, 5, operator);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hi", "I'm", "Awesome"})
  /*  @NullSource
    @EmptySource*/
    @NullAndEmptySource
    void tt(String message) {
        String actual = Main.printMessage(message);
        assertEquals("The message is: " + message, actual);
    }

    @TestFactory
    Collection<DynamicTest> dynamicTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test A", () -> assertEquals(5, 5)),
                DynamicTest.dynamicTest("Test B", () -> assertEquals(5, 5)),
                DynamicTest.dynamicTest("Test C", () -> assertEquals(5, 5))
        );
    }

}