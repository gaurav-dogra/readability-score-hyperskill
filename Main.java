package readability;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String ARI = "ari";
    private static final String FK = "fk";
    private static final String SMOG = "smog";
    private static final String CL = "cl";
    private static final String ALL = "all";

    public static void main(String[] args) {

        if (args[0] != null) {
            String text = getText(args[0]); // getting all the text from the file into the String
            printStatistics(text);

            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            final Scanner scanner = new Scanner(System.in);
            final String input = scanner.next().toLowerCase();

            switch (input) {
                case ARI:
                    ari(text);
                    break;
                case FK:
                    fk(text);
                    break;
                case SMOG:
                    smog(text);
                    break;
                case CL:
                    cl(text);
                    break;
                case ALL:
                    all(text);
                    break;

                default:
                    System.out.println("Wrong input entered. Program exiting..................");
            }

        }
    }

    private static void all(String text) {
        double score = getScore(text, new AutomatedRIndex());
        double totalAge = getAge(score);
        System.out.format("%nAutomated Readability Index: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new FleschKincaid());
        totalAge += getAge(score);
        System.out.format("%nFlesch–Kincaid readability tests: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new SmogIndex());
        totalAge += getAge(score);
        System.out.format("%nSimple Measure of Gobbledygook: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new ColemanLiauIndex());
        totalAge += getAge(score);
        System.out.format("%nColeman–Liau index: %.2f (about %d year olds).", score, getAge(score));

        System.out.format("%n%nThis text should be understood in average by %.2f year olds.", totalAge / 4);

    }

    private static void cl(String text) {
        double score = getScore(text, new ColemanLiauIndex());
        System.out.format("%nColeman-Liau index: %.2f (about %d year olds).", score, getAge(score));

    }

    private static void smog(String text) {
        double score = getScore(text, new SmogIndex());
        System.out.format("%nSimple Measure of Gobbledygook: %.2f (about %d year olds).", score, getAge(score));
    }

    private static void fk(String text) {
        double score = getScore(text, new FleschKincaid());
        System.out.format("%nFlesch-Kincaid readability tests: %.2f (about %d year olds).", score, getAge(score));
    }

    private static void ari(String text) {
        double score = getScore(text, new AutomatedRIndex());
        System.out.format("%nAutomated Readability Index: %.2f (about %d year olds).", score, getAge(score));
    }

    private static void printStatistics(String text) {
        InputReader inputReaderObject = new InputReader(text);

        System.out.println("The text is:\n" + text);
        System.out.println();
        System.out.println("Words: " + inputReaderObject.getWords());
        System.out.println("Sentences: " + inputReaderObject.getSentences());
        System.out.println("Characters: " + inputReaderObject.getCharacters());
        System.out.println("Syllables: " + inputReaderObject.getSyllables());
        System.out.println("Polysyllables: " + inputReaderObject.getPolySyllables());
    }

    private static double getScore(String text, IScoreStrategy strategy) {
        Context context = new Context(strategy);
        return context.getScore(text);
    }

    private static String getText(String fileName) {
        StringBuilder text = new StringBuilder();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return text.toString();
    }

    private static int getAge(double score) {
        int[] levels = {6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24};

        int level = (int) Math.round(score);
        level = level < 1 ? 1 : Math.min(level, 13);
        return levels[level - 1];
    }
}
