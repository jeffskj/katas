package dictionary;

import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    
    private Set<String> words;

    public Dictionary(Set<String> words) {
        this.words = words;
    }
    
    public Set<String> getWords() {
       return words;
    }

    public Set<String> getSixCharacterWords() {
        Set<String> sixCharWords = new HashSet<String>();
        for(String word: words){
            if(word.length() == 6){
                sixCharWords.add(word);
            }
        }
        return sixCharWords;
    }
}
