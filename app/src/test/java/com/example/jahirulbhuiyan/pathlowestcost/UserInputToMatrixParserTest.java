package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;

import org.hamcrest.Matchers;
import org.junit.Test;



import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertThat;

public class UserInputToMatrixParserTest {

    private UserInputToMatrixParser subject = new UserInputToMatrixParser();

    @Test
    public void parseInput_oneRowOfIntegers_returns_correctCostMatrix() {
        CostMatrix expected = new CostMatrix(singletonList(asList(1,2,3,4,5)));

        CostMatrix actual = subject.parseInput("1 2 3 4 5");

        assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void parseInput_multipleRowsOfIntegers_returns_correctCostMatrix() {
        CostMatrix expected = new CostMatrix(asList(
                asList(1,2,3,4,5),
                asList(6,7,8,9,10)
        ));

        CostMatrix actual = subject.parseInput("1 2 3 4 5\n6 7 8 9 10");

        assertThat(actual, Matchers.is(expected));
    }

    @Test(expected = InvalidInputException.class)
    public void parseInput_throwsExceptionIfNotAllValuesAreIntegers() {
        subject.parseInput("1 2 foo 4");
    }

    @Test(expected = InvalidInputException.class)
    public void parseInput_throwsExceptionIfNotAllRowsAreSameSize() {
        subject.parseInput("1 2 3\n3 2 1 1");
    }
}