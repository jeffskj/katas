package password;

class PasswordGenerator {
    private static final def LEFT_HAND_KEYS = 'qwertasdfgzxcvb123456!@#$%^'
    private static final def RIGHT_HAND_KEYS = 'yuiop[]\\hjkl;\'nm,./7890-=<>?:"{}_+'
    private static final def SPECIAL_CHARS = '1234567890[]\\-_=+()*&^%$#@!`;:\'",<.>/?1234567890 '
    
    static Random rand = new Random()
    
    static void main(args) {
        Dictionary dict = new Dictionary()
        def words = dict.findAll { it.length() > 6 && it.length() < 10 && it[0] == it[0].toLowerCase() }
        
        words = words.findAll { evenHands(it, 3) } as List
        
        20.times {
            try {
                def password = nextPassword(words)
                println password         
            } catch (Exception e) {
            }
        }
    }
    
    static String nextPassword(words) {
        def specials = random(SPECIAL_CHARS) + random(SPECIAL_CHARS)
        while (!evenHands(specials, 2)) {
            specials = random(SPECIAL_CHARS) + random(SPECIAL_CHARS)
        }
        
        return cap1(random(words)) + specials //+ random(words)
    }
    
    static def random(x) {
        return x[rand.nextInt(x.size())]
    }
    
    static boolean requiresShift(String s) {
        return s == s.toUpperCase() || '!@#$%^&*()_+{}:"<>?|~'.contains(s)
    }
    
    static boolean evenHands(String s, int numRepeats) {
        def hands = []
        for (c in s) {
            if (LEFT_HAND_KEYS.contains(c)) {
                hands += 'L'
            }
            if (RIGHT_HAND_KEYS.contains(c)) {
                hands += 'R'
            }
            if (requiresShift(c)) {
                hands[-1] = 'S' + hands[-1]
            }
            if (hands.size() > numRepeats) {
                if (true) {
                    
                }
            }
        }
        
        def joined = hands.join('')
        return !joined.contains('L' * numRepeats) && !joined.contains('R' * numRepeats) && !joined.contains('LSL') && 
            !joined.contains('SLL') && !joined.contains('RSR') && !joined.contains('SRR')
    }
    
    static String cap1(String s) {
        int i = rand.nextInt(s.length())
        return (i > 0 ? s[0..i-1] : '') + s[i].toUpperCase() + ( i < s.length()-1 ? s[i+1..-1] : '')
    } 
    
}
//piCkery$_