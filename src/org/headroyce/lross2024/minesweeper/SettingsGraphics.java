package org.headroyce.lross2024.minesweeper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The settings menu for minesweeper (made with BorderPane)
 */
class SettingsGraphics extends BorderPane {

    private Font common = Font.font(14);

    private Button exit = new Button("Exit");
    private Button play = new Button("Play");

    private ToggleButton easy = new ToggleButton("Easy");
    private ToggleButton med = new ToggleButton("Medium");
    private ToggleButton hard = new ToggleButton("Hard");

    private Spinner rowspinner;
    private Slider rows;

    private Spinner colspinner;
    private Slider cols;

    private int numrows = 5;
    private int numcols = 5;

    public String difficulty = null;

    /**
     * Constructor that makes the layout of the menu
     */
    public SettingsGraphics() {
        VBox vbox = new VBox(setRowSliders(), setColSliders());

        VBox buttons = setDifficultyButtons();
        HBox button2 = setOtherButtons();

        this.setLeft(vbox);
        this.setRight(buttons);
        this.setBottom(button2);
    }

    /**
     * Gets the number of Rows chosen
     * @return number of rows
     */
    public int getRows(){
        return numrows;
    }
    /**
     * Gets the number of Columns chosen
     * @return number of columns
     */
    public int getCols(){
        return numcols;
    }

    /**
     * Gets the number of board spaces
     * @return int board spaces
     */
    public int getSpaces(){
        return numrows * numcols;
    }

    /**
     * Gets the number of board spaces
     * @return int board spaces
     */
    public String getDifficulty(){
        return difficulty;
    }

    private VBox setRowSliders(){
        VBox rtn = new VBox();

        Label NoR = new Label("# Rows");
        NoR.setPrefWidth(100);
        NoR.setFont(common);

        rows = new Slider();
        rows.setMin(1);
        rows.setMax(10);
        rows.setValue(5);
        rows.setOrientation(Orientation.VERTICAL);

        rowspinner = new Spinner();
        rowspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        rowspinner.getValueFactory().setValue(5);
        rowspinner.setPrefWidth(100);

        rows.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * Every time the value of the rows slider changes, it sets the value of the spinner to match and updates numrows
             * @param observableValue generates change event (with Number types)
             * @param oldValue the previous value of the slider
             * @param newValue the new value of the slider
             */
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                rowspinner.getValueFactory().setValue(newValue.intValue());
                numrows = newValue.intValue();
            }
        });
        rowspinner.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * Every time the value of the rows spinner changes, it sets the value of the slider to match
             * @param observableValue generates change event (with Number types)
             * @param oldValue the previous value of the spinner
             * @param newValue the new value of the spinner
             */
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                rows.setValue(newValue.intValue());
            }
        });

        rows.setPadding(new Insets(10, 30, 0, 30));
        NoR.setPadding(new Insets(10, 10, 0, 20));
        setMargin(rtn, new Insets(10, 10, 10, 10));

        rtn.getChildren().addAll(rowspinner, rows, NoR);
        rtn.setPrefWidth(120);
        rtn.setMaxWidth(120);
        rtn.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;"
        );

        return rtn;
    }

    private VBox setColSliders(){
        VBox rtn = new VBox();

        Label NoC = new Label("# Columns");
        NoC.setPrefWidth(100);
        NoC.setFont(common);

        cols = new Slider();
        cols.setMin(1);
        cols.setMax(10);
        cols.setValue(5);
        cols.setOrientation(Orientation.VERTICAL);

        colspinner = new Spinner();
        colspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        colspinner.getValueFactory().setValue(5);
        colspinner.setPrefWidth(100);

        cols.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * Every time the value of the rows slider changes, it sets the value of the spinner to match and updates numcols
             * @param observableValue generates change event (with Number types)
             * @param oldValue the previous value of the slider
             * @param newValue the new value of the slider
             */
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                colspinner.getValueFactory().setValue(newValue.intValue());
                numcols = newValue.intValue();
            }
        });
        colspinner.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * Every time the value of the rows spinner changes, it sets the value of the slider to match
             * @param observableValue generates change event (with Number types)
             * @param oldValue the previous value of the spinner
             * @param newValue the new value of the spinner
             */
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                cols.setValue(newValue.intValue());
            }
        });

        cols.setPadding(new Insets(10, 30, 0, 30));
        NoC.setPadding(new Insets(10, 5, 0, 15));
        setMargin(rtn, new Insets(10, 10, 10, 10));

        rtn.getChildren().addAll(colspinner, cols, NoC);
        rtn.setPrefWidth(120);
        rtn.setMaxWidth(120);
        rtn.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;"
        );

        return rtn;
    }

    private VBox setDifficultyButtons(){
        VBox rtn = new VBox(10);
        Insets insets = new Insets(10, 0, 10, 0);
        ToggleGroup toggleGroup = new ToggleGroup();

        easy.setToggleGroup(toggleGroup);
        med.setToggleGroup(toggleGroup);
        hard.setToggleGroup(toggleGroup);

        easy.setPrefWidth(360);
        easy.setFont(common);
        easy.setPadding(insets);
        easy.setOnAction(new enablePlay());
        med.setPrefWidth(360);
        med.setFont(common);
        med.setPadding(insets);
        med.setOnAction(new enablePlay());
        hard.setPrefWidth(360);
        hard.setFont(common);
        hard.setPadding(insets);
        hard.setOnAction(new enablePlay());

        rtn.getChildren().addAll(easy,med,hard);

        setMargin(rtn, new Insets(10, 10, 10, 10));
        return rtn;
    }

    private HBox setOtherButtons(){
        HBox rtn = new HBox(10);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        exit.setPadding(new Insets(5, 10, 5, 10));
        exit.setFont(common);
        exit.setOnAction(new exit());
        play.setPadding(new Insets(5, 10, 5, 10));
        play.setFont(common);
        play.setOnAction(new play());
        play.setDisable(true);

        setMargin(rtn, new Insets(10, 10, 10, 0));


        rtn.getChildren().addAll(spacer,exit,play);
        return rtn;
    }

    private class exit implements EventHandler<ActionEvent> {
        /**
         * Runs if the button is pressed, exits the program
         * @param e source of the button
         */
        public void handle(ActionEvent e) {
            System.exit(0);
        }
    }

    private class play implements EventHandler<ActionEvent> {
        /**
         * Runs if the button is pressed, switches scene (through Main) to BoardGraphics
         * @param e source of the button
         */
        public void handle(ActionEvent e) {
            Scene scene = new Scene(new BoardGraphics(numrows, numcols, getDifficulty()), numcols*100, numrows*100);
            Main.switchScenes(scene);
        }
    }

    private class enablePlay implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e){
            ToggleButton source = (ToggleButton) e.getSource();
            difficulty = source.getText();
            if (easy.isSelected() || med.isSelected() || hard.isSelected()){
                play.setDisable(false);
            } else {
                play.setDisable(true);
            }

        }
    }


}
