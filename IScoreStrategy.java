package readability;
/* Common interface for all strategies */
public interface IScoreStrategy {
    double calculate(String text);
}
