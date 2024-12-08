package aoc3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class resolution3 {

    static File data = new File("src/aoc3/data");
    static ArrayList<String> mulStrings = new ArrayList<>();
    static ArrayList<String> mulStringsSelected = new ArrayList<>();


    public static void main(String[] args) {
        analyzeData(data);
        System.out.println("First part result: " + countSumOfMultiplications(mulStrings));
        System.out.println("Second part result: " + countSumOfMultiplications(mulStringsSelected));

    }

    public static void analyzeData(File dataFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            StringBuilder longString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                longString.append(line);
            }
            analyzeTextLine(longString.toString(), mulStrings);
            analyzeTextLineBetter(longString.toString(), mulStringsSelected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void analyzeTextLine(String textLine, ArrayList<String> results) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcher = pattern.matcher(textLine);
        while (matcher.find()) {
            results.add(matcher.group());
        }
    }

    public static void analyzeTextLineBetter(String textLine, ArrayList<String> results) {
        String[] doSplitedStrings = textLine.split("do\\(\\)");
        for (String s : doSplitedStrings) {
            String[] dontSplitedStrings = s.split("don't\\(\\)");
            analyzeTextLine(dontSplitedStrings[0], results);
        }
    }

    public static int countSumOfMultiplications(ArrayList<String> stringsArrayList) {
        int sum = 0;
        for (String s : stringsArrayList) {
            int[] multiplicationFactors = new int[2];
            int iterator = 0;
            Pattern pattern = Pattern.compile("\\d{1,3}");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                multiplicationFactors[iterator] = Integer.parseInt(matcher.group());
                iterator++;
            }
            int tempSum = multiplicationFactors[0] * multiplicationFactors[1];
            sum += tempSum;
        }
        return sum;
    }

}

