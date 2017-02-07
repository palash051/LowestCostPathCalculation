package com.example.jahirulbhuiyan.pathlowestcost.models;

/**
 * Used to store matrix coordinate and value
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class LowestPathEntry {
    private final MatrixCell coordinates;
    private final Integer value;

    public MatrixCell getCoordinates() {
        return coordinates;
    }

    public Integer getValue() {
        return value;
    }

    public LowestPathEntry(MatrixCell coordinates, Integer value) {
        this.coordinates = coordinates;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }
        LowestPathEntry o1 = (LowestPathEntry) o;
        return o1.getCoordinates().equals(this.getCoordinates()) &&
                o1.getValue().equals(this.getValue());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", coordinates, value);
    }
}