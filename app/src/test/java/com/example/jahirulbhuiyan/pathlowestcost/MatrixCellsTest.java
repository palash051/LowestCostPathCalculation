package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.MatrixCell;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class MatrixCellsTest {

    @Test
    public void equals_returnsTrue_when_coordinatesAreEqual() {
        assertThat(new MatrixCell(1,1), Matchers.is(new MatrixCell(1,1)));
    }

    @Test
    public void equals_returnsFalse_when_coordinatesAreNotEqual() {
        assertThat(new MatrixCell(1,1), Matchers.not(Matchers.is(new MatrixCell(1,2))));
        assertThat(new MatrixCell(1,1), Matchers.not(Matchers.is(new MatrixCell(1,2))));
        assertThat(new MatrixCell(1,1), Matchers.not(Matchers.is(new MatrixCell(2,1))));
        assertThat(new MatrixCell(1,1), Matchers.not(Matchers.is(new Object())));
    }
}