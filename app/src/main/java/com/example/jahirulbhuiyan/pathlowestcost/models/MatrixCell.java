package com.example.jahirulbhuiyan.pathlowestcost.models;

/**
 * Matrix cell based on X & Y coordinate
 * Created by Jahirul Bhuiyan on 02/06/2017
 */
public class MatrixCell {

    private final int coordinateX;
    private final int coordinateY;

    public MatrixCell(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }


    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().isAssignableFrom(obj.getClass())) return false;

        MatrixCell toCompare = (MatrixCell) obj;
        return this.coordinateX == toCompare.getCoordinateX() && this.coordinateY == toCompare.getCoordinateY();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", coordinateX, coordinateY);
    }
}
