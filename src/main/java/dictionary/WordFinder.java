package dictionary;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

    private final Dictionary dictionary;

    public WordFinder(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Result> getCompositeWordsList() {
        List<Result> results = new ArrayList<Result>();
        for (String word : dictionary.getSixCharacterWords()) {
            for (int splitPosition = 1; splitPosition < 6; splitPosition++) {
                String a = word.substring(0, splitPosition);
                String b = word.substring(splitPosition, word.length());
                if (dictionary.getWords().contains(a) && dictionary.getWords().contains(b)) {
                    Result r = new Result();
                    r.setA(a);
                    r.setB(b);
                    r.setWord(word);
                    results.add(r);
                }
            }
        }
        return results;
    }

}
