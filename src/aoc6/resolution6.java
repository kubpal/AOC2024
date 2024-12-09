package aoc6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class resolution6 {
    static File data = new File("src/aoc6/data");
    static ArrayList<String> rawData = new ArrayList<>();
    static MapField[][] fieldMatrix;
    static int xSize;
    static int ySize;
    static int xStart;
    static int yStart;
    static int openedFields;

    public static void main(String[] args) {
        readData(data);
        calculateDataSize(rawData);
        fieldMatrix = new MapField[ySize][xSize];
        fillMap(rawData);
        goUp(xStart, yStart);
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

    public static void fillMap(ArrayList<String> rawData) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                char c = (rawData.get(y)).charAt(x);
                if (c == '.') {
                    fieldMatrix[y][x] = new MapField(x, y, false);
                } else if (c == '#') {
                    fieldMatrix[y][x] = new MapField(x, y, true);
                } else {
                    fieldMatrix[y][x] = new MapField(x, y, false);
                    xStart = x;
                    yStart = y;
                    fieldMatrix[y][x].isOpen = true;
                    openedFields++;
                }
            }
        }
    }

    public static void goUp(int x, int y) {
        if ((y - 1) < 0) {
            finish();
        } else {
            if (!fieldMatrix[y - 1][x].isObstacle) {
                if (!fieldMatrix[y - 1][x].isOpen) {
                    fieldMatrix[y - 1][x].isOpen = true;
                    openedFields++;
                }
                goUp(x, y - 1);
            } else {
                goRight(x, y);
            }
        }
    }

    public static void goRight(int x, int y) {
        if ((x + 1) == xSize) {
            finish();
        } else {
            if (!fieldMatrix[y][x + 1].isObstacle) {
                if (!fieldMatrix[y][x + 1].isOpen) {
                    fieldMatrix[y][x + 1].isOpen = true;
                    openedFields++;
                }
                goRight(x + 1, y);
            } else {
                goDown(x, y);
            }
        }
    }

    public static void goDown(int x, int y) {
        if ((y + 1) == ySize) {
            finish();
        } else {
            if (!fieldMatrix[y + 1][x].isObstacle) {
                if (!fieldMatrix[y + 1][x].isOpen) {
                    fieldMatrix[y + 1][x].isOpen = true;
                    openedFields++;
                }
                goDown(x, y + 1);
            } else {
                goLeft(x, y);
            }
        }
    }

    public static void goLeft(int x, int y) {
        if ((x - 1) < 0) {
            finish();
        } else {
            if (!fieldMatrix[y][x - 1].isObstacle) {
                if (!fieldMatrix[y][x - 1].isOpen) {
                    fieldMatrix[y][x - 1].isOpen = true;
                    openedFields++;
                }
                goLeft(x - 1, y);
            } else {
                goUp(x, y);
            }
        }
    }

    public static void finish() {
        System.out.println("Result of first part is: " + openedFields);
    }
}
