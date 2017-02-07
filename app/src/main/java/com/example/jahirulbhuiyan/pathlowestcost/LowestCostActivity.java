package com.example.jahirulbhuiyan.pathlowestcost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jahirulbhuiyan.pathlowestcost.models.CostMatrix;
import com.example.jahirulbhuiyan.pathlowestcost.models.Result;

import java.util.List;

/**
 * Main Activity class.
 * Initialize all user component and handle events and display the result for the events.
 * Created by Jahirul Bhuiyan on 02/06/2017
 */
public class LowestCostActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView completedTextView;
    private TextView costTextView;
    private TextView pathTextView;
    private TextView errorTextView;
    private UserInputToMatrixParser inputParser;
    private LowestCostService leastResistanceService;

    // Dummy input data
    String input="3 4 1 2 8 6\n6 1 8 2 7 4\n5 9 3 9 9 5\n8 4 1 3 2 6\n3 7 2 8 6 4";
    //String input="3 4 1 2 8 6\n6 1 8 2 7 4\n5 9 3 9 9 5\n8 4 1 3 2 6\n3 7 2 1 2 3";
    //String input="19 10 19 10 19\n21 23 20 19 12\n20 12 20 11 10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowest_cost);
        inputEditText = ((EditText) findViewById(R.id.inputField));
        inputEditText.setText(input);
        completedTextView = ((TextView) findViewById(R.id.lblCompleted));
        costTextView = ((TextView) findViewById(R.id.lblTotalResistance));
        pathTextView = ((TextView) findViewById(R.id.lblPath));
        errorTextView = ((TextView) findViewById(R.id.lblError));
        inputParser = new UserInputToMatrixParser();
        leastResistanceService = new LowestCostService(new LowestPathFinder(50));
        findViewById(R.id.btnCalculate).setOnClickListener(calculateListener);
    }


    View.OnClickListener calculateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                errorTextView.setVisibility(View.GONE);
                CostMatrix graph = inputParser.parseInput(inputEditText.getText().toString());
                displayResults(leastResistanceService.calculate(graph));
            } catch (InvalidInputException e) {
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText(e.getMessage());
            }
        }

        private void displayResults(Result result) {
            completedTextView.setText(result.isCompleted() ? "Yes" : "No");
            costTextView.setText(Integer.toString(result.getTotalCost()));
            pathTextView.setText(pathToString(result.getPathList()));
        }

        private String pathToString(List<Integer> path) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i != path.size() - 1) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    };


}
