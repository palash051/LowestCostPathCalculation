package com.example.jahirulbhuiyan.pathlowestcost;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Matrix converter from user input to NxM integer matrix.
 * User will input the matrix using SPACE as Column separator and Back(\n) for row separator
 * This helper class will split the user input row wise first and then column wise
 * This class also validate the user input and notify user if there any wrong input
 * by throwing InvalidInputException
 * Created by Jahirul Bhuiyan on 02/06/2017
 */

public class UserInputToMatrixParser {
    public CostMatrix parseInput(String input) {

        List<String> rowsAsString = Arrays.asList(input.split("\n"));
        List<List<Integer>> grid = new ArrayList<>();

        int validRowSize = 0;
        for (int x = 0; x < rowsAsString.size(); x++) {
            List<String> rowAsStringList = Arrays.asList(rowsAsString.get(x).split(" "));

            if (validRowSize == 0)
                validRowSize = rowAsStringList.size();

            if (rowAsStringList.size() != validRowSize) {
                throw new InvalidInputException("All rows must have the same amount of integers.");
            }

            List<Integer> rowAsIntegerList = new ArrayList<>();
            for (int i = 0; i < rowAsStringList.size(); i++) {
                try {
                    rowAsIntegerList.add(Integer.parseInt(rowAsStringList.get(i)));
                } catch (Exception e) {
                    throw new InvalidInputException("All values must be integers.");
                }
            }
            grid.add(rowAsIntegerList);
        }

        return new CostMatrix(grid);
    }
}
