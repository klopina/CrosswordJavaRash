package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;
/*
Кроссворд
*/
class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{{'f', 'd', 'e', 'r', 'l', 'k'},
                                        {'u', 's', 'a', 'm', 'e', 'o'},
                                        {'l', 'n', 'g', 'r', 'o', 'v'},
                                        {'m', 'l', 'p', 'r', 'r', 'h'},
                                        {'p', 'o', 'e', 'e', 'j', 'j'}};

//          detectAllWords(crossword, "home", "same");
        for (Word x : detectAllWords(crossword, "home", "same"))
            System.out.println(x);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> allWords = new ArrayList<>();
        for (String st : words) {
            Word word = new Word(st);
            int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int row = 0; row < crossword.length; row++) {
                for (int col = 0; col < crossword[0].length; col++) {
                    if (crossword[row][col] == st.charAt(0)) {
                        int len = st.length();
                        for (int dir = 0; dir < x.length; dir++) {
                            boolean thisWord = true;
                            int  rd = row + x[dir], cd = col + y[dir];
                            for (int k = 1; k < len; k++) {
                                if (rd >= crossword.length || rd < 0 || cd >= crossword[0].length || cd < 0 || crossword[rd][cd] != st.charAt(k)) {
                                    thisWord = false;
                                    break;
                                }
                                if (k < len - 1) {
                                    rd += x[dir];
                                    cd += y[dir];
                                }
                            }
                            if (thisWord) {
                                word.setStartPoint(col, row);
                                word.setEndPoint(cd, rd);
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
