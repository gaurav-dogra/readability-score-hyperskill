package readability;

public class ColemanLiauIndex implements IScoreStrategy {

    @Override
    public double calculate(String text) {
        final InputReader inputReaderObject = new readability.InputReader(text);
        final double words = inputReaderObject.getWords();
        final double sentences = inputReaderObject.getSentences();
        final double characters = inputReaderObject.getCharacters();

        final double L = characters / words * 100.0;
        final double S = 100 / (words / sentences);

        return 0.0588 * L - 0.296 * S - 15.8;
    }
}
