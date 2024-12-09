package aoc4;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class resolution4 {
    static File data = new File("src/aoc4/data");
    static ArrayList<String> rawData = new ArrayList<>();
    static ArrayList<String> finalStrings = new ArrayList<>();
    static ArrayList<String> xStrings = new ArrayList<>();
    static char[][] charMatrix;
    static int xSize;
    static int ySize;

    public static void main(String[] args) {
        readData(data);
        calculateDataSize(rawData);
        charMatrix = new char[ySize][xSize];
        fillCharMatrix(rawData);
        fillFinalStrings();
        System.out.println("Number of XMAS in data: " + countXmass());
        fillXStrings();
        System.out.println("Number of X-MAS in data: " + calculateCorrectXStrings());
    }

    public static void readData(File data) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rawData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calculateDataSize(ArrayList<String> rawData) {
        xSize = (rawData.get(0)).length();
        ySize = rawData.size();
    }

    public static void fillCharMatrix(ArrayList<String> rawData) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                charMatrix[y][x] = (rawData.get(y)).charAt(x);
            }
        }
    }

    public static void fillFinalStrings() {
        fillByHorizontalStrings();
        fillByVerticalStrings();
        fillByDiagonalAStrings();
        fillByDiagonalBStrings();
    }

    public static void fillByHorizontalStrings() {
        for (int y = 0; y < ySize; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < xSize; x++) {
                stringBuilder.append(charMatrix[y][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }
    }

    public static void fillByVerticalStrings() {
        for (int x = 0; x < xSize; x++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int y = 0; y < ySize; y++) {
                stringBuilder.append(charMatrix[y][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }
    }

    public static void fillByDiagonalAStrings() {
        for (int i = 0; i < xSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = i; x < xSize; x++) {
                stringBuilder.append(charMatrix[x - i][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }

        for (int i = 1; i < xSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < xSize - i; x++) {
                stringBuilder.append(charMatrix[x + i][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }
    }

    public static void fillByDiagonalBStrings() {
        for (int i = 0; i < xSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = xSize - 1; x >= i; x--) {
                stringBuilder.append(charMatrix[xSize - x - 1 + i][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }

        for (int i = 1; i < xSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = xSize - 1 - i; x >= 0; x--) {
                stringBuilder.append(charMatrix[xSize - x - 1 - i][x]);
            }
            addNormalAndReversedToFinalStrings(stringBuilder);
        }
    }

    public static void addNormalAndReversedToFinalStrings(StringBuilder stringBuilder) {
        finalStrings.add(stringBuilder.toString());
        finalStrings.add(stringBuilder.reverse().toString());
    }

    public static int countXmass() {
        int counter = 0;
        for (String s : finalStrings) {
            Pattern pattern = Pattern.compile("XMAS");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                counter++;
            }
        }
        return counter;
    }

    public static void fillXStrings() {
        for (int y = 1; y < ySize - 1; y++) {
            for (int x = 1; x < xSize - 1; x++) {
                if ('A' == charMatrix[y][x]) {
                    String stringBuilder = String.valueOf(charMatrix[y - 1][x - 1]) +
                            charMatrix[y - 1][x + 1] +
                            charMatrix[y + 1][x - 1] +
                            charMatrix[y + 1][x + 1];
                    xStrings.add(stringBuilder);
                }
            }
        }
    }

    public static int calculateCorrectXStrings() {
        int counter = 0;
        for (String s : xStrings) {
            if (s.equals("MMSS") || s.equals("MSMS") || s.equals("SSMM") || s.equals("SMSM")) {
                counter++;
            }
        }
        return counter;
    }
}
