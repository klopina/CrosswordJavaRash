package com.company;

import java.util.*;

public class Main {
    /*
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании.
7. И всякая хрень.
  */

    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        for (Word x : detectAllWords(crossword, "home", "same"))
            System.out.println(x);
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> allWords = new ArrayList<>();
        Word word;
        for (String w : words) {
            word = new Word(w);
            //create 8 directions and designate them with coordinates. For example: (-1, 1) - diagonal to the left, (0, -1) - horizontal to the left.
            int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1},
                    dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};

            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if ((char) crossword[y][x] == w.charAt(0)) {
                        for (int i = 0; i < dx.length; i++) {
                            boolean thisWord = true;
                            for (int c = 1; c < w.length(); c++) {
                                //y+c*dy[i] - is a formula for determining direction, where "c" is equal to the number of a letter in a word.
                                if (y+c*dy[i] < 0 || y+c*dy[i] >= crossword.length || x+c*dx[i] < 0 || x+c*dx[i] >= crossword[y+c*dy[i]].length ||
                                        (char) crossword[y+c*dy[i]][x+c*dx[i]] != w.charAt(c)) {
                                    thisWord = false;
                                    break;
                                }
                            }
                            if (thisWord) {
                                word.setStartPoint(x, y);
                                word.setEndPoint(x+dx[i]*(w.length()-1), y+(w.length() - 1)*dy[i]);
                                allWords.add(word);
                            }
                        }
                    }
                }
            }
        }
        return allWords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
