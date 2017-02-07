package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;
import com.example.jahirulbhuiyan.pathlowestcost.models.MatrixCell;

import org.hamcrest.Matchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CostMatrixTest {

    @Test
    public void getRight_returnsStraightRightValue() {
        MatrixCell result = new CostMatrix(singletonList(asList(1, 2))).getRight(new MatrixCell(1, 1));

        assertThat(result, Matchers.is(new MatrixCell(2, 1)));
    }

    @Test
    public void getRight_returnsNullWhenAtEndOfRow() {
        MatrixCell result = new CostMatrix(singletonList(asList(1, 1))).getRight(new MatrixCell(2, 1));

        assertNull(result);
    }

    @Test
    public void getDiagonalUp_returnsUpRightMatrixCells() {
        MatrixCell result =
                new CostMatrix(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1)
                )).getDiagonalUp(new MatrixCell(2, 2));

        assertThat(result, Matchers.is(new MatrixCell(3, 1)));
    }

    @Test
    public void getDiagonalUp_returnsRightForXAndLastRowForYMatrixCells_whenCurrentMatrixCellsAreTopRow() {
        MatrixCell result =
                new CostMatrix(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1),
                        asList(1, 1, 1)
                )).getDiagonalUp(new MatrixCell(2, 1));

        assertThat(result, Matchers.is(new MatrixCell(3, 3)));
    }

    @Test
    public void getDiagonalDown_returnsDownRightMatrixCells() {
        MatrixCell result =
                new CostMatrix(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1)
                )).getDiagonalDown(new MatrixCell(2, 1));

        assertThat(result, Matchers.is(new MatrixCell(3, 2)));
    }

    @Test
    public void getDiagonalDown_returnsRightForXAndFirstRowForYMatrixCells_whenCurrentMatrixCellsAreBottomRow() {
        MatrixCell result =
                new CostMatrix(asList(
                        asList(1, 1, 2),
                        asList(1, 1, 1),
                        asList(1, 1, 1)
                )).getDiagonalDown(new MatrixCell(2, 3));

        assertThat(result, Matchers.is(new MatrixCell(3, 1)));
    }

    @Test
    public void getCost_returns_theCorrectValue() {
        CostMatrix CostMatrix = new CostMatrix(asList(
                asList(1, 2, 3),
                asList(4, 5, 6),
                asList(7, 8, 9)
        ));

        assertThat(CostMatrix.getCost(new MatrixCell(1,1)), Matchers.is(1));
        assertThat(CostMatrix.getCost(new MatrixCell(2,1)), Matchers.is(2));
        assertThat(CostMatrix.getCost(new MatrixCell(3,1)), Matchers.is(3));
        assertThat(CostMatrix.getCost(new MatrixCell(1,2)), Matchers.is(4));
        assertThat(CostMatrix.getCost(new MatrixCell(2,2)), Matchers.is(5));
        assertThat(CostMatrix.getCost(new MatrixCell(3,2)), Matchers.is(6));
        assertThat(CostMatrix.getCost(new MatrixCell(1,3)), Matchers.is(7));
        assertThat(CostMatrix.getCost(new MatrixCell(2,3)), Matchers.is(8));
        assertThat(CostMatrix.getCost(new MatrixCell(3,3)), Matchers.is(9));
    }

    @Test
    public void getHeight_returns_theCorrectValue() {
        int height = new CostMatrix(asList(
                asList(1, 2),
                asList(4, 5),
                asList(7, 8)
        )).getHeight();

        assertThat(height, Matchers.is(3));
    }

    @Test
    public void getWidth_returns_theCorrectValue() {
        int width = new CostMatrix(asList(
                asList(1, 2),
                asList(4, 5),
                asList(7, 8)
        )).getWidth();

        assertThat(width, Matchers.is(2));
    }
}