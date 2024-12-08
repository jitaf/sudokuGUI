package com.example.sudokugui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private GridPane grid;

    private final int SIZE = 9;
    private TextField[][] cells = new TextField[SIZE][SIZE];
    private boolean[][] locked = new boolean[SIZE][SIZE];

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
                // Nastavení střídavého stylu podle pozice
                if ((row / 3 + col / 3) % 2 == 0) {
                    cell.getStyleClass().add("cell-dark");
                } else {
                    cell.getStyleClass().add("cell-light");
                }
                // Přidání CSS třídy pro okraje podle pozice
                if (row == 0) cell.getStyleClass().add("top-border");
                if (col == 0) cell.getStyleClass().add("left-border");
                if (row == 8) cell.getStyleClass().add("bottom-border");
                if (col == 8) cell.getStyleClass().add("right-border");

                if (row % 3 == 0 && row != 0) cell.getStyleClass().add("thick-top-border");
                if (col % 3 == 0 && col != 0) cell.getStyleClass().add("thick-left-border");

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

    @FXML
    private void checkInput() {
        if (hasErrors(false)) {
            showAlert(Alert.AlertType.ERROR, "Chyba", "V zadání jsou chyby!");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Bez chyby", "Zadání je v pořádku!");
        }
    }

    @FXML
    private void lockInput() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!cells[row][col].getText().isEmpty()) {
                    locked[row][col] = true;
                    cells[row][col].setEditable(false);
                }
            }
        }
    }
    @FXML
    private void checkSolution() {
        if (hasErrors(true)) {
            showAlert(Alert.AlertType.ERROR, "Chyba", "Řešení je špatně!");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Správně", "Gratulace! Sudoku je správně vyřešené.");
        }
    }

    @FXML
    private void resetAttempt() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!locked[row][col]) {
                    cells[row][col].clear();
                }
            }
        }
    }

    @FXML
    private void clearAll() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].clear();
                cells[row][col].setEditable(true);
                locked[row][col] = false;
            }
        }
    }

    private boolean hasErrors(boolean checkAll) {
        // Check rows, columns, and subgrids for duplicates
        for (int i = 0; i < SIZE; i++) {
            if (hasDuplicates(getRow(i), checkAll) || hasDuplicates(getColumn(i), checkAll)) {
                return true;
            }
        }

        for (int row = 0; row < SIZE; row += 3) {
            for (int col = 0; col < SIZE; col += 3) {
                if (hasDuplicates(getSubgrid(row, col), checkAll)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String[] getRow(int row) {
        String[] values = new String[SIZE];
        for (int col = 0; col < SIZE; col++) {
            values[col] = cells[row][col].getText();
        }
        return values;
    }

    private String[] getColumn(int col) {
        String[] values = new String[SIZE];
        for (int row = 0; row < SIZE; row++) {
            values[row] = cells[row][col].getText();
        }
        return values;
    }

    private String[] getSubgrid(int startRow, int startCol) {
        String[] values = new String[SIZE];
        int index = 0;
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                values[index++] = cells[row][col].getText();
            }
        }
        return values;
    }

    private boolean hasDuplicates(String[] values, boolean checkAll) {
        boolean[] seen = new boolean[SIZE + 1];
        for (String value : values) {
            if (value.isEmpty()) {
                if (checkAll) return true;
                continue;
            }
            int num;
            try {
                num = Integer.parseInt(value);
                if (num < 1 || num > SIZE) return true;
            } catch (NumberFormatException e) {
                return true;
            }
            if (seen[num]) return true;
            seen[num] = true;
        }
        return false;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}