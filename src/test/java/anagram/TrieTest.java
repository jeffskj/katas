package anagram;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrieTest {

    @Test
    public void test() {
        Trie t = new Trie();
        t.add("foo");
        assertTrue(t.contains("foo"));
        assertFalse(t.contains("bar"));
        
        t.add("##thing");
        assertTrue(t.contains("##thing"));
     
        t.add("bar");
        
        for (String s : t) {
            System.out.println(s);
        }
    }

}
