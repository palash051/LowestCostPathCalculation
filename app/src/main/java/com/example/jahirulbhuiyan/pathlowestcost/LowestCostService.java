package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;
import com.example.jahirulbhuiyan.pathlowestcost.models.LowestPathEntry;
import com.example.jahirulbhuiyan.pathlowestcost.models.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Lowest cost finding service class.
 * This class will calculate the actual path & cost using the path finder class and return result.
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class LowestCostService {

    private final LowestPathFinder pathFinder;

    public LowestCostService(LowestPathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    /**
     * Calculate the lowest cost
     * @param costMatrix input matrix
     * @return output Result
     */
    public Result calculate(CostMatrix costMatrix) {
        List<LowestPathEntry> path = pathFinder.findPath(costMatrix);

        return new Result(path.size() == costMatrix.getWidth(), sumPath(path), pathToRowNumbers(path));
    }

    /**
     * Store the path value to row number
     * @param path path entry
     * @return list of integer
     */
    private List<Integer> pathToRowNumbers(List<LowestPathEntry> path) {
        List<Integer> pathAsRowNumbers = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            pathAsRowNumbers.add(path.get(i).getCoordinates().getCoordinateY());
        }
        return pathAsRowNumbers;
    }

    /**
     * Calculate path sum
     * @param path entry path
     * @return total path cost
     */
    private int sumPath(List<LowestPathEntry> path) {
        int sum = 0;
        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i).getValue();
        }
        return sum;
    }
}
