package dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class WordTest {
    
    Dictionary dictionary;
    @Before
    public void setErUp(){
        Set<String> words = new HashSet<String>();
        words.add("howdy");
        words.add("doody");
        words.add("tea");
        words.add("Mississippi");
        words.add("teapot");
        words.add("pot");
        dictionary = new Dictionary(words);
    }

    @Test
    public void testWordsThatBreakDown(){
        WordFinder wf = new WordFinder(dictionary);
        List<Result> list = wf.getCompositeWordsList();
        System.out.println(list);
        assertTrue(list.size() == 1);
    }
    
    @Test
    public void testGetWordList() {        
        Set<String> wordList = dictionary.getWords();
        assertNotNull(wordList);
        assertTrue(wordList.size() > 0);
        assertTrue(dictionary.getSixCharacterWords().size() > 0);
    }
    
    @Test
    public void testGetSixCharacterWords() {
        Set<String> sixCharacterWords = dictionary.getSixCharacterWords();
        assertFalse(sixCharacterWords.isEmpty());
        
        for(String word : sixCharacterWords) {
            assertTrue(word.length() == 6);
        }
    }
}
