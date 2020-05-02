package readability;

public class InputReader {

    private final String text;

    public InputReader(String text) {
        this.text = text.toLowerCase();
    }

    public int getWords() {
        return text.split("\\s+").length;
    }

    public int getSentences() {
        String text = this.text.trim();
        return text.split("[.!?]").length;
    }


    public int getCharacters() {
        return text.replaceAll("\\s+", "").length();
    }

    public int getSyllables() {
        int syllables = 0;
        String[] words = text.split("\\s+|\\.\\s+|\\?\\s+|!\\s+");

        for (String word : words) {
            int currentSyllables = getSyllablesOfOneWord(word);
            currentSyllables = currentSyllables > 0 ? currentSyllables : 1;
            syllables += currentSyllables;
        }
        return syllables;
    }

    private int getSyllablesOfOneWord(String word) {
        String vowels = "aeiouy";

        int syllables = 0;
        boolean flag = false;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (i == word.length() - 1 && word.charAt(i) == 'e') {
                break; // if last char is 'e' it is not counted
            }
            // the below if else ensure more than one consecutive vowels are counted as one syllable
            if (vowels.indexOf(currentChar) >= 0) {
                if (!flag) {
                    syllables++;
                }
                flag = true;
            } else {
                flag = false;
            }
        }
        return syllables;
    }

    public int getPolySyllables() {
        int polySyllables = 0;
        String[] words = text.split("\\s+|\\.\\s+|\\?\\s+|!\\s+");

        for (String word : words) {
            int currentSyllables = getSyllablesOfOneWord(word);
            currentSyllables = currentSyllables > 2 ? 1 : 0;
            polySyllables += currentSyllables;
        }
        return polySyllables;
    }

}

