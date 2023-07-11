package org.example;

import java.io.IOException;

/**
 * Основной класс, использующий класс GameBoard для демонстрации
 * кодирование, запись, чтение, декодирование и печать состояния игрового поля.
 */
public class Main {
    public static void main(String[] args) {
        int[] initialState = {2, 1, 0, 2, 0, 2, 0, 1, 3};
        GameBoard gameBoard = new GameBoard(initialState);

        try {
            gameBoard.writeToFile("gameBoard.bin");
            gameBoard.readFromFile("gameBoard.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameBoard.printBoard();
    }
}