package password;

import static org.junit.Assert.*

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder


class DictionaryTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()
    
    @Test
    public void shouldReadWordsFromFile() {
        def f = tmp.newFile("wordlist.txt")
        def words = ['some', 'test', 'word']
        f.write(words.join('\n'))
        
        Dictionary dict = new Dictionary()
        dict.wordStream = f.newInputStream()
        words.each { assertTrue(dict.contains(it)) }
        def found = dict.findAll { it.startsWith('w') }
        assertEquals(1, found.size())
        println found
    }
}
