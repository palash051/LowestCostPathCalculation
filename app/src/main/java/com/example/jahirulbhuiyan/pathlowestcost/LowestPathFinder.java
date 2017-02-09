package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.MatrixCell;
import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;
import com.example.jahirulbhuiyan.pathlowestcost.models.LowestPathEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used for find the all available path that we have to taking care of.
 * It will calculate to best path from the source to destination.
 * There is a maxcost variable which will provided from caller and path will dismiss when total cost is greater maxcost.
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class LowestPathFinder {

    private final int maxCost;

    public LowestPathFinder() {
        this(Integer.MAX_VALUE);
    }

    public LowestPathFinder(int maxCost) {
        this.maxCost = maxCost;
    }

    /**
     * Find the best available path
     * @param costMatrix input matrix
     * @return best path entry
     */
    public List<LowestPathEntry> findPath(CostMatrix costMatrix) {
        List<LowestPathEntry> bestPath = null;

        MatrixCell matrixCell;
        for (int i = 0; i < costMatrix.getHeight(); i++) {
            matrixCell=new MatrixCell(1, i + 1);
            if(costMatrix.getCost(matrixCell)>maxCost) {
                continue;
            }
            List<LowestPathEntry> currentPath = findPath(costMatrix, matrixCell, new ArrayList<LowestPathEntry>());
            if (bestPath == null || sumPath(currentPath) < sumPath(bestPath)) {
                bestPath = currentPath;
            }
        }

        return bestPath;
    }

    /**
     * Find the best available path
     * @param costMatrix inputmatrix
     * @param matrixCells current cell
     * @param path list of paths
     * @return pathentry
     */
    private List<LowestPathEntry> findPath(CostMatrix costMatrix, MatrixCell matrixCells, List<LowestPathEntry> path) {
        if (matrixCells == null) {
            return path;
        }

        List<LowestPathEntry> currentPath = new ArrayList<>(path);
        int nextCost = costMatrix.getCost(matrixCells);

        if (sumPath(currentPath) + nextCost > maxCost || matrixCells.getCoordinateX() > costMatrix.getWidth()) {
            return currentPath;
        }
        currentPath.add(new LowestPathEntry(matrixCells, nextCost));

        List<LowestPathEntry> upRight = findPath(costMatrix, costMatrix.getDiagonalUp(matrixCells), currentPath);
        List<LowestPathEntry> straightRight = findPath(costMatrix, costMatrix.getRight(matrixCells), currentPath);
        List<LowestPathEntry> downRight = findPath(costMatrix, costMatrix.getDiagonalDown(matrixCells), currentPath);

        return findBestPath(upRight, straightRight, downRight);
    }

    // find the best path entry from UP/DOWN or RIGHT
    private List<LowestPathEntry> findBestPath(List<LowestPathEntry> up, List<LowestPathEntry> right, List<LowestPathEntry> down) {
        List<LowestPathEntry> bestOfUpAndRight = bestOfTwo(up, right);
        return bestOfTwo(bestOfUpAndRight, down);
    }

    private List<LowestPathEntry> bestOfTwo(List<LowestPathEntry> p1, List<LowestPathEntry> p2) {
        if (p1.size() == p2.size()) {
            if (sumPath(p1) < sumPath(p2)) {
                return p1;
            }
            return p2;
        }

        if (p1.size() > p2.size()) {
            return p1;
        }
        return p2;
    }

    private int sumPath(List<LowestPathEntry> path) {
        int sum = 0;
        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i).getValue();
        }
        return sum;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
