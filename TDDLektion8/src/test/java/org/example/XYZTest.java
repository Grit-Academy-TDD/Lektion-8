package org.example;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class XYZTest {

    @TestFactory
    Collection<DynamicTest> dynamicTests() {
        ArrayList<DynamicTest> dynamicTests = new ArrayList<>();
        ArrayList<XYZ> xyzArrayList = new ArrayList<>();
        xyzArrayList.add(new XYZ(1, 2, 3));
        xyzArrayList.add(new XYZ(2, 3, 5));
        xyzArrayList.add(new XYZ(3, 4, 7));
        xyzArrayList.add(new XYZ(4, 5, 9));
        for (XYZ point : xyzArrayList) {
            DynamicTest dynamicTest = DynamicTest.dynamicTest(
                    "Test when: " + point.getX() + " plus " + point.getY() + " is equal: " + point.getZ(),
                    () -> assertEquals(point.getZ(), Main.calculate(point.getX(), point.getY(), Main.Operator.addition))
            );
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }
}