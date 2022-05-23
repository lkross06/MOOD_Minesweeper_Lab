package org.headroyce.lross2024.minesweeper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;

/**
 * The game board for minesweeper (made with BorderPane)
 */
public class BoardGraphics extends BorderPane{
    BoardLogic logic;

    private Font common = Font.font(14);
    private Font big = Font.font("Verdana", FontWeight.BOLD, 22);

    Label title = new Label("YOU LOSE");
    public Button[][] board;
    private Button settings;
    StackPane main = new StackPane();

    int numRows;
    int numCols;

    /**
     * Constructor that makes a new board array and makes layout
     */
    public BoardGraphics(int rows, int cols, String difficulty) {
        numRows = rows;
        numCols = cols;

        logic = new BoardLogic(numRows, numCols, difficulty);
        board = new Button[numRows][numCols];

        GridPane boardGUI = layoutBoard();
        HBox status = layoutStatus();
        status.setPadding(new Insets(0, 0, 10, 0));
        title.setFont(big);
        title.setVisible(false);
        title.setAlignment(Pos.CENTER);

        main.getChildren().addAll(boardGUI, title);

        this.setTop(status);
        this.setCenter(main);
        this.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < board.length; i++){
            for (int k = 0; k < board[i].length; k++){
                board[i][k].setOnAction(new BoardButton());
            }
        }
    }


    private HBox layoutStatus() {
        HBox rtn = new HBox();

        settings = new Button("Settings");
        settings.setFont(common);
        settings.setPadding(new Insets(10, 10, 10, 10));
        settings.setOnAction(new settings());
        rtn.getChildren().add(settings);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        rtn.getChildren().add(spacer);

        Button reset = new Button("Reset");
        reset.setOnAction(e -> reset());
        reset.setFont(common);
        reset.setPadding(new Insets(10, 10, 10, 10));
        rtn.getChildren().add(reset);

        setMargin(rtn, new Insets(10, 5, 10, 5));
        return rtn;
    }


    private GridPane layoutBoard() {

        GridPane boardGUI = new GridPane();

        for (int i = 0; i < numRows; i++) {
            RowConstraints row = new RowConstraints(50, 100, Double.MAX_VALUE);
            row.setVgrow(Priority.ALWAYS);
            boardGUI.getRowConstraints().add(row);
        }
        for (int i = 0; i < numCols; i++){
            ColumnConstraints col = new ColumnConstraints(50, 100, Double.MAX_VALUE);
            col.setHgrow(Priority.ALWAYS);
            boardGUI.getColumnConstraints().add(col);
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                board[row][col] = new Button("");
                board[row][col].setFont(common);
                board[row][col].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setFillHeight(board[row][col], true);
                GridPane.setFillWidth(board[row][col], true);

                boardGUI.add(board[row][col], col, row);
            }
        }
        setMargin(boardGUI, new Insets(0, 10, 10, 10));
        return boardGUI;
    }

    private void reset(){
        logic.reset();
        title.setVisible(false);
        title.setText("YOU LOSE");
        for ( int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setText("");
            }
        }
    }

    private void gameEnd(){
        if (logic.winCondition()){
            title.setText("YOU WIN");
        }
        title.setVisible(true);
        title.toFront();
        for ( int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (logic.getSpace(i, j) < 0){
                    logic.pickSpace(i,j);
                    board[i][j].setText(Integer.toString(logic.getSpace(i, j)));
                    if (board[i][j].getText().equals("9")) {
                        board[i][j].setText("KABOOM!");
                    }
                }
            }
        }
    }

    private void updateGraphics() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (logic.getSpace(i, j) >= 0){
                    board[i][j].setText(Integer.toString(logic.getSpace(i, j)));
                    if (board[i][j].getText().equals("9")) {
                        board[i][j].setText("KABOOM!");
                    }
                }
                if (logic.isGameover()){
                    gameEnd();
                }
            }
        }
    }

    private class settings implements EventHandler<ActionEvent> {
        /**
         * Runs if button is pressed, switches scene to SettingsGraphics
         * @param e button source
         */
        public void handle(ActionEvent e) {
            Scene scene = new Scene(new SettingsGraphics(), 500, 500);
            Main.switchScenes(scene);
        }
    }


    private class BoardButton implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button source = (Button) e.getSource();
            if (!logic.isGameover()) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == source) {
                            logic.pickSpace(i, j);
                        }
                    }
                }
                updateGraphics();
            }
        }
    }
}
