/**
 * Author - Zaw Htet Aung(Y2K222) @ 7th November 2022
 * Description - Minimal Calculator Using JavaFx
 */

package com.zawhtetaung.calculatorfx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorFxController implements Initializable {

    @FXML
    TextField numberField;

    /**
     *  Menu bar handlers start
     */
    public void handleCloseProgram() {
        System.exit(0);
    }

    public void handleAbout() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Simple Calculator by Zaw Htet Aung.Github link ", ButtonType.OK);
        infoAlert.show();
    }
    /**
     *  Menu bar handlers end
     */

    /**
     * Handler for number button to add with the old string
     * @param numberString
     */
    private void handleNumberButtons(String numberString) {
        String oldNumberString = numberField.getText();
        numberField.setText(oldNumberString + numberString);
    }

    /**
     * Handler fro operation buttons skip for double operations
     * @param operationString
     */
    private void handleOperationButtons(String operationString) {
        String oldNumberString = numberField.getText();
        if (!isLastCharIsOperation(oldNumberString)) {
            numberField.setText(oldNumberString + operationString);
        }
    }

    /**
     * Check is last number is operation
     * @param payloadString
     * @return boolean
     */
    private boolean isLastCharIsOperation(String payloadString) {
        char lastChar = payloadString.charAt(payloadString.length() -1);
        return lastChar == '+' || lastChar == '-' || lastChar == '×'|| lastChar == '÷' || lastChar == '.';
    }

    /**
     * Button handlers start
     */
    public void handleOneButton() {
        handleNumberButtons("1");
    }

    public void handleTwoButton() {
        handleNumberButtons("2");
    }

    public void handleThreeButton() {
        handleNumberButtons("3");
    }

    public void handleFourButton() {
        handleNumberButtons("4");
    }

    public void handleFiveButton() {
        handleNumberButtons("5");
    }

    public void handleSixButton() {
        handleNumberButtons("6");
    }

    public void handleSevenButton() {
        handleNumberButtons("7");
    }

    public void handleEightButton() {
        handleNumberButtons("8");
    }

    public void handleNineButton() {
        handleNumberButtons("9");
    }

    public void handleZeroButton() {
        handleNumberButtons("0");
    }

    public void handleDoubleZeroButton() {
        handleNumberButtons("00");
    }

    public void handlePointButton() {
        handleOperationButtons(".");
    }

    public void handleAddButton() {
        handleOperationButtons("+");
    }

    public void handleMinusButton() {
        handleOperationButtons("-");
    }

    public void handleMultiButton() {
        handleOperationButtons("×");
    }

    public void handleDivisionButton() {
        handleOperationButtons("÷");
    }

    public void handleRemainderButton() {
        handleOperationButtons("%");
    }

    public void handlePowButton() {
        handleOperationButtons("p");
    }

    public void handleSqrtButton() { calculateSqrt(); }

    public void handleSquareButton() { calculateSquare(); }

    public void handleClearButton() {
        numberField.clear();
    }

    public void handleEqualButton() {
        // calculate the number
        String calculatedString = calculate(numberField.getText());

        // show the result
        numberField.setText(calculatedString);
    }

    public void handleDeleteButton() {
        String currentNumberString = numberField.getText();
        int numberLength = currentNumberString.length();
        if (numberLength > 0) {
            numberField.setText(currentNumberString.substring(0, currentNumberString.length() - 1));
        }
    }

    /**
     * Button handlers end
     */

    private void calculateSqrt() {
        Double number = Double.parseDouble(numberField.getText());
        numberField.setText(String.valueOf(Math.sqrt(number)));
    }

    private void calculateSquare() {
        Double number = Double.parseDouble(numberField.getText());
        numberField.setText(String.valueOf(number * number));
    }

    /**
     * Calculate with a numberString
     * @param numberString
     * @return String
     */
    private String calculate(String numberString) {
        // store all numbers to numberStr
        String numberArr[] = numberString.replaceAll("[+×÷()%-]+"," ").split(" ");
        // store operators to operatorArr
        String operatorArr[] = numberString.replaceAll("[0-9().]+","").split("");

        // total number initialized with first number;
        Double total = Double.parseDouble(numberArr[0]);
        for (int i = 0; i < operatorArr.length; i++) {
            switch (operatorArr[i]) {
                case "+":
                    System.out.println(numberArr[i]);
                    total += Double.parseDouble(numberArr[i + 1]);
                    break;
                case "-":
                    total -= Double.parseDouble(numberArr[i + 1]);
                    break;
                case "×":
                    total *= Double.parseDouble(numberArr[i + 1]);
                    break;
                case "÷":
                    total /= Double.parseDouble(numberArr[i + 1]);
                    break;
                case "%":
                    total %= Double.parseDouble(numberArr[i + 1]);
                    break;
            }
            if(i+2 >= operatorArr.length) continue; // if meets the last operands already
            numberArr[i+1] = String.valueOf(total);
        }

        return String.valueOf(total);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int location = numberField.getText().length();
                Platform.runLater(() -> {
                    numberField.positionCaret(location);
                });
            }
        });
    }
}