package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс для представления и управления игровым полем.
 */
public class GameBoard {
    private int[] boardState;

    public GameBoard(int[] boardState) {
        this.boardState = boardState;
    }

    /**
     * Кодирует состояние игрового поля в одно целое число.
     *
     * @вернуть закодированное состояние игрового поля
     */
    public int encodeBoard() {
        int encodedBoard = 0;
        for (int i = 0; i < boardState.length; i++) {
            encodedBoard = encodedBoard << 2;
            encodedBoard = encodedBoard | boardState[i];
        }
        return encodedBoard;
    }

    /**
     * Декодирует состояние игрового поля из одного целого числа.
     *
     * @param encodedBoard — закодированное состояние игрового поля.
     */
    public void decodeBoard(int encodedBoard) {
        for (int i = boardState.length - 1; i >= 0; i--) {
            int cellState = encodedBoard & 3;
            encodedBoard = encodedBoard >> 2;
            boardState[i] = cellState;
        }
    }

    /**
     * Записывает состояние игрового поля в файл.
     *
     * @param fileName имя файла для записи
     * @throws IOException, если возникает ошибка ввода/вывода
     */
    public void writeToFile(String fileName) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(encodeBoard());
        }
    }

    /**
     * Читает состояние игрового поля из файла.
     *
     * @param fileName имя файла для чтения
     * @throws IOException, если возникает ошибка ввода/вывода
     */
    public void readFromFile(String fileName) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            int readBoard = dis.readInt();
            decodeBoard(readBoard);
        }
    }

    /**
     * Выводит состояние игрового поля на консоль.
     */
    public void printBoard() {
        for (int i = 0; i < boardState.length; i++) {
            char cellSymbol;
            switch (boardState[i]) {
                case 0:
                    cellSymbol = '.';
                    break;
                case 1:
                    cellSymbol = 'x';
                    break;
                case 2:
                    cellSymbol = 'o';
                    break;
                default:
                    cellSymbol = '?'; // неизвестное состояние ячейки
                    break;
            }
            // Печатает в строчке по три символа
            System.out.print(cellSymbol + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}
