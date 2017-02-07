package com.example.jahirulbhuiyan.pathlowestcost.models;

import java.util.List;

/**
 * Store the final result.
 * Completed means if we cal calculate the final path then set TRUE otherwise FALSE
 * TotalCost store the total cost
 * PathList store the node path list
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class Result {
    private final boolean completed;
    private final int totalCost;
    private final List<Integer> pathList;

    public Result(boolean completed, int totalResistance, List<Integer> path) {
        this.completed = completed;
        this.totalCost = totalResistance;
        this.pathList = path;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<Integer> getPathList() {
        return pathList;
    }
}
