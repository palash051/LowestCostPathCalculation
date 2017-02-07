package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;
import com.example.jahirulbhuiyan.pathlowestcost.models.LowestPathEntry;
import com.example.jahirulbhuiyan.pathlowestcost.models.MatrixCell;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;


import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LowestPathFinderTest {

    LowestPathFinder subject = new LowestPathFinder(50);

    @Test
    public void constructor_setsMaxResistanceToMaxInteger_when_noValueIsSupplied() {
        assertEquals(Integer.MAX_VALUE, new LowestPathFinder().getMaxCost());
    }

    @Test
    public void constructor_setsMaxResistanceToPassedValue_when_valueIsPassed() {
        assertEquals(50, new LowestPathFinder(50).getMaxCost());
    }

    @Test
    public void findPath_given1x1_returnsCorrectPath() {
        CostMatrix costMatrix = new CostMatrix(singletonList(singletonList(1)));

        List<LowestPathEntry> expected = singletonList(
                new LowestPathEntry(new MatrixCell(1, 1), 1)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void findPath_given1x5_returnsCorrectPath() {
        CostMatrix costMatrix = new CostMatrix(singletonList(asList(1, 2, 3, 4, 5)));

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 1),
                new LowestPathEntry(new MatrixCell(2, 1), 2),
                new LowestPathEntry(new MatrixCell(3, 1), 3),
                new LowestPathEntry(new MatrixCell(4, 1), 4),
                new LowestPathEntry(new MatrixCell(5, 1), 5)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void findPath_given2x5_returnsCorrectPath() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(1, 2, 2, 2, 1),
                        asList(2, 1, 1, 1, 2))
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 1),
                new LowestPathEntry(new MatrixCell(2, 2), 1),
                new LowestPathEntry(new MatrixCell(3, 2), 1),
                new LowestPathEntry(new MatrixCell(4, 2), 1),
                new LowestPathEntry(new MatrixCell(5, 1), 1)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void findPath_given5x5_returnsCorrectPath() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(1, 2, 3, 4, 5),
                        asList(3, 4, 2, 1, 5),
                        asList(5, 6, 3, 2, 3),
                        asList(6, 1, 2, 1, 1),
                        asList(9, 2, 1, 4, 5)
                )
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 1),
                new LowestPathEntry(new MatrixCell(2, 1), 2),
                new LowestPathEntry(new MatrixCell(3, 5), 1),
                new LowestPathEntry(new MatrixCell(4, 4), 1),
                new LowestPathEntry(new MatrixCell(5, 4), 1)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void findPath_given10x10_returnsCorrectPath() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(5, 1, 5, 1, 5, 5, 5, 5, 5, 5),
                        asList(1, 5, 5, 5, 1, 5, 5, 5, 5, 1),
                        asList(5, 5, 5, 5, 5, 1, 5, 5, 1, 5),
                        asList(5, 5, 5, 5, 5, 5, 1, 1, 5, 5),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
                        asList(5, 5, 1, 5, 5, 5, 5, 5, 5, 5)
                )
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 2), 1),
                new LowestPathEntry(new MatrixCell(2, 1), 1),
                new LowestPathEntry(new MatrixCell(3, 10), 1),
                new LowestPathEntry(new MatrixCell(4, 1), 1),
                new LowestPathEntry(new MatrixCell(5, 2), 1),
                new LowestPathEntry(new MatrixCell(6, 3), 1),
                new LowestPathEntry(new MatrixCell(7, 4), 1),
                new LowestPathEntry(new MatrixCell(8, 4), 1),
                new LowestPathEntry(new MatrixCell(9, 3), 1),
                new LowestPathEntry(new MatrixCell(10, 2), 1)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void findPath_given1x5_thatExceedsMaxResistance_returnsCorrectPathUnderMaxResistance() {
        LowestPathFinder pathFinder = new LowestPathFinder(10);

        CostMatrix costMatrix = new CostMatrix(singletonList(asList(5, 4, 3, 2, 1)));

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 5),
                new LowestPathEntry(new MatrixCell(2, 1), 4)
        );

        assertListsAreEqual(pathFinder.findPath(costMatrix), expected);
    }

    @Test
    public void kata_example_one() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 8, 6, 4)
                )
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 3),
                new LowestPathEntry(new MatrixCell(2, 2), 1),
                new LowestPathEntry(new MatrixCell(3, 3), 3),
                new LowestPathEntry(new MatrixCell(4, 4), 3),
                new LowestPathEntry(new MatrixCell(5, 4), 2),
                new LowestPathEntry(new MatrixCell(6, 5), 4)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void kata_example_two() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(3, 4, 1, 2, 8, 6),
                        asList(6, 1, 8, 2, 7, 4),
                        asList(5, 9, 3, 9, 9, 5),
                        asList(8, 4, 1, 3, 2, 6),
                        asList(3, 7, 2, 1, 2, 3)
                )
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 3),
                new LowestPathEntry(new MatrixCell(2, 2), 1),
                new LowestPathEntry(new MatrixCell(3, 1), 1),
                new LowestPathEntry(new MatrixCell(4, 5), 1),
                new LowestPathEntry(new MatrixCell(5, 5), 2),
                new LowestPathEntry(new MatrixCell(6, 5), 3)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    @Test
    public void kata_example_three() {
        CostMatrix costMatrix = new CostMatrix(
                asList(
                        asList(19,10,19,10,19),
                        asList(21,23,20,19,12),
                        asList(20,12,20,11,10)
                )
        );

        List<LowestPathEntry> expected = asList(
                new LowestPathEntry(new MatrixCell(1, 1), 19),
                new LowestPathEntry(new MatrixCell(2, 1), 10),
                new LowestPathEntry(new MatrixCell(3, 1), 19)
        );

        assertListsAreEqual(subject.findPath(costMatrix), expected);
    }

    private void assertListsAreEqual(List<LowestPathEntry> i1, List<LowestPathEntry> i2) {
        assertThat(i1.size(), Matchers.is(i2.size()));

        for (int i = 0; i < i1.size(); i++) {
            assertThat(i1.get(i), Matchers.is(i2.get(i)));
        }
    }
}