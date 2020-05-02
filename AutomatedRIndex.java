package readability;

public class AutomatedRIndex implements IScoreStrategy {
    @Override
    public double calculate(String text) {
        final InputReader inputReaderObject = new InputReader(text);
        final double characters = inputReaderObject.getCharacters();
        final double words = inputReaderObject.getWords();
        final double sentences = inputReaderObject.getSentences();
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }
}
