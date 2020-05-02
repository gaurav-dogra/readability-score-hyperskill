package readability;

public class FleschKincaid implements IScoreStrategy {

    @Override
    public double calculate(String text) {
        final InputReader inputReaderObject = new InputReader(text);
        final int words = inputReaderObject.getWords();
        final int sentences = inputReaderObject.getSentences();
        final int syllables = inputReaderObject.getSyllables();

        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }
}
