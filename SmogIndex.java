package readability;

public class SmogIndex implements IScoreStrategy {

    @Override
    public double calculate(String text) {
        final InputReader inputReaderObject = new InputReader(text);
        final double polySyllables = inputReaderObject.getPolySyllables();
        final double sentences = inputReaderObject.getSentences();

        return 1.043 * Math.sqrt((polySyllables * 30) / sentences) + 3.1291;
    }
}
