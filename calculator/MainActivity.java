package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder input = new StringBuilder();
    private double operand1 = Double.NaN;
    private double operand2 = Double.NaN;
    private String operator = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set up click listeners for number buttons
        findViewById(R.id.button0).setOnClickListener(numberClickListener);
        findViewById(R.id.button1).setOnClickListener(numberClickListener);
        findViewById(R.id.button2).setOnClickListener(numberClickListener);
        findViewById(R.id.button3).setOnClickListener(numberClickListener);
        findViewById(R.id.button4).setOnClickListener(numberClickListener);
        findViewById(R.id.button5).setOnClickListener(numberClickListener);
        findViewById(R.id.button6).setOnClickListener(numberClickListener);
        findViewById(R.id.button7).setOnClickListener(numberClickListener);
        findViewById(R.id.button8).setOnClickListener(numberClickListener);
        findViewById(R.id.button9).setOnClickListener(numberClickListener);

        // Set up click listeners for operation buttons (+, -, *, /)
        findViewById(R.id.buttonPlus).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonMinus).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonMultiply).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonDivide).setOnClickListener(operationClickListener);

        // Set up click listener for equal button
        findViewById(R.id.equalButton).setOnClickListener(equalClickListener);

        // Set up click listener for reset button
        findViewById(R.id.resetButton).setOnClickListener(resetClickListener);
    }

    private View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            input.append(button.getText());
            updateResult();
        }
    };

    private View.OnClickListener operationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!input.toString().isEmpty()) {
                Button button = (Button) v;
                operator = button.getText().toString();
                operand1 = Double.parseDouble(input.toString());
                input.setLength(0); // Clear input for the next number
            }
        }
    };

    private View.OnClickListener equalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!Double.isNaN(operand1) && !input.toString().isEmpty()) {
                operand2 = Double.parseDouble(input.toString());
                double result = performOperation(operand1, operand2, operator);
                resultTextView.setText(String.valueOf(result));
                input.setLength(0);
                operand1 = result;
            }
        }
    };

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0)
                    return operand1 / operand2;
                else
                    return Double.NaN; // Division by zero
            default:
                return Double.NaN; // Invalid operation
        }
    }

    private View.OnClickListener resetClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            input.setLength(0);
            operand1 = Double.NaN;
            operand2 = Double.NaN;
            operator = "";
            resultTextView.setText("0");
        }
    };

    private void updateResult() {
        resultTextView.setText(input.toString());
    }
}
