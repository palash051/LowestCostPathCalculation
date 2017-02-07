package com.example.jahirulbhuiyan.pathlowestcost.models;

import java.util.List;

/**
 * Generated cost matrix.
 * Use to get right, Diagonal Up & Down cell value and also the cost
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class CostMatrix {
    private final List<List<Integer>> lowestCostMatrix;

    public CostMatrix(List<List<Integer>> lowestCostMatrix) {
        this.lowestCostMatrix = lowestCostMatrix;
    }

    /**
     * Get right cell
     * @param matrixCells
     * @return
     */
    public MatrixCell getRight(MatrixCell matrixCells) {
        if (matrixCells.getCoordinateX() + 1 > lowestCostMatrix.get(matrixCells.getCoordinateY() - 1).size())
            return null;

        return new MatrixCell(matrixCells.getCoordinateX() + 1, matrixCells.getCoordinateY());
    }

    /**
     * Get Diagonal up cell
     * @param matrixCells
     * @return if right cell null return null otherwise diagonal cell
     */
    public MatrixCell getDiagonalUp(MatrixCell matrixCells) {
        MatrixCell right = getRight(matrixCells);
        if (right == null)
            return null;
        int y = matrixCells.getCoordinateY() - 1;
        return new MatrixCell(right.getCoordinateX(), y == 0 ? lowestCostMatrix.size() : y);
    }


    /**
     * Get Diagonal up cell
     * @param matrixCells
     * @return if right cell null return null otherwise diagonal cell
     */
    public MatrixCell getDiagonalDown(MatrixCell matrixCells) {
        MatrixCell right = getRight(matrixCells);
        if (right == null)
            return null;
        int y = matrixCells.getCoordinateY() + 1;
        return new MatrixCell(right.getCoordinateX(), y > lowestCostMatrix.size() ? 1 : y);
    }


    public int getCost(MatrixCell matrixCells) {
        return lowestCostMatrix.get(matrixCells.getCoordinateY() - 1).get(matrixCells.getCoordinateX() - 1);
    }

    public int getWidth() {
        return lowestCostMatrix.get(0).size();
    }

    public int getHeight() {
        return lowestCostMatrix.size();
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }
        CostMatrix o1 = (CostMatrix) o;

        for (int i = 0; i < o1.lowestCostMatrix.size(); i++) {
            List<Integer> thisRow = this.lowestCostMatrix.get(i);
            List<Integer> compareRow = o1.lowestCostMatrix.get(i);

            for (int x = 0; x < compareRow.size(); x++) {
                if (thisRow.get(x) != compareRow.get(x)) {
                    return false;
                }
            }
        }
        return true;
    }
}
