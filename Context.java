package readability;

public class Context {
    readability.IScoreStrategy strategy;

    public Context(IScoreStrategy strategy) {
        this.strategy = strategy;
    }

    public double getScore(String text) {
        return strategy.calculate(text);
    }
}
