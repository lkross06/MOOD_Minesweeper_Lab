package org.headroyce.lross2024.minesweeper;

import javafx.scene.control.Button;

import java.lang.constant.Constable;
import java.util.*;

public class BoardLogic {

    /**
     * 1-9 = number of mines around space (0 mines = 1, 1 mine = 2, etc)
     * 10 = mine
     * negative 1-10 = covered space (*-1 when clicked)
     */

    public int[][] board;
    private int numrows;
    private int numcols;
    private String difficulty;
    private boolean win = false;

    int numSafe;
    int numMines = 0;
    int numPicked = 0;

    public BoardLogic(int rows, int cols, String difficulty){
        numrows = rows;
        numcols = cols;
        this.difficulty = difficulty;
        board = new int[rows][cols];
        reset();
    }

    public void reset(){
        for (int i = 0; i < board.length; i++){ Arrays.fill(board[i], -1); }
        numMines = 0;
        numPicked = 0;
        win = false;
        addMines();
        makeBoard();
        numSafe = (numrows * numcols) - numMines;
    }

    public boolean winCondition(){
        isGameover();
        return win;
    }

    public boolean isGameover(){
        for (int rows = 0; rows < board.length; rows++){
            for (int cols = 0; cols < board[rows].length; cols++){
                if (board[rows][cols] == 9){
                    win = false;
                    return true;
                } else if (numSafe == numPicked){
                    win = true;
                    return true;
                }
            }
        }
        return false;
    }


    private void addMines(){
        int prob = 15;
        switch (difficulty) {
            case "Medium" -> prob = 20;
            case "Hard" -> prob = 25;
        }
        for (int i = 0; i < board.length; i++){
            for (int k = 0; k < board[i].length; k++){
                Random rand = new Random();
                if (rand.nextInt(100) <= prob){
                    board[i][k] = -10;
                    numMines++;
                }
            }
        }
    }

    private void makeBoard(){
        /**
         * check every mine and update its periphery
         */
        //check every tile
        for (int rows = 0; rows < board.length; rows++){
            for (int cols = 0; cols < board[rows].length; cols++){
                if (board[rows][cols] == -10){
                    for (int i = rows-1; i <= rows+1; i++){
                        for (int k = cols-1; k <= cols+1; k++){
                            if (i < board.length && i >=0 && k < board[i].length && k >=0 && board[i][k] != -10) {
                                board[i][k]--;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getSpace(int row, int col){
        return board[row][col];
    }

    public boolean pickSpace(int row, int col){
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length){
            return false;
        }
        if (board[row][col] >= 0){
            return false;
        }
        board[row][col] *= -1;
        board[row][col]--;
        numPicked++;
        if (board[row][col] == 0){
            for (int i = row-1; i <= row+1; i++){
                for (int k = col-1; k <= col+1; k++){
                    pickSpace(i, k);
                }
            }
        }
        return true;
    }
}