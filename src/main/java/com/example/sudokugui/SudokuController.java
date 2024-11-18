package com.example.sudokugui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private GridPane grid;

    private TextField[][] cells = new TextField[9][9];

    @FXML
    public void initialize() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField cell = new TextField();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setText(""); // Můžete nastavit předvyplněné číslo
                grid.add(cell, col, row);
                cells[row][col] = cell;
            }
        }
    }

    @FXML
    private void onSolve() {
        // Tady můžete implementovat logiku řešení Sudoku
        System.out.println("Řešení Sudoku není implementováno.");
        //pro ukazku vypis
        System.out.println("uplne vlevo je cislo " + cells[0][0].getText());
    }

    @FXML //jen pro ukazku, vsude nastavi hodnotu 5
    private void onClear() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("5");
            }
        }
    }
}