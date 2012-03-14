package password;

class Dictionary {
    private InputStream wordStream = getClass().classLoader.getResourceAsStream("wordlist.txt")
    private Set<String> words
    
    
    private void init() {
        if (words == null) {
            words = wordStream.readLines() as Set
        }
    }
    
    boolean contains(String word) {
        init()
        return words.contains(word)
    }     
    
    Collection findAll(Closure predicate) {
        init()
        return words.findAll(predicate)
    }
}
