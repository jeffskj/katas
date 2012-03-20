package anagram;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.AbstractIterator;

public class Trie implements Iterable<String> {
    
    private Node root = new Node();
    
    public void add(String s) {
        Node n = root;
        
        for (char c : s.toCharArray()) {
            Node child = n.getChild(c);
            if (child == null) {
                n = n.addChild(c);
            } else {
                n = child;
            }
        }
        
        n.isWord = true;
    }

    public boolean contains(String s) {
        return findNode(s) != null;
    }

    private Node findNode(String s) {
        Node n = root;
        for (char c : s.toCharArray()) {
            n = n.getChild(c);
            if (n == null) {
                return null;
            }
        }
        return n;
    }
    
    public Iterator<String> iterator(String prefix) {
        final Node start = findNode(prefix);
        return new AbstractIterator<String>() {
            Deque<Node> parents = new LinkedList<Node>();
            Deque<Iterator<Character>> childIterators = new LinkedList<Iterator<Character>>();
            {
                parents.push(start);
                childIterators.push(start.childKeys().iterator());
            }
            
            @Override
            protected String computeNext() {
                while (parents.peek() != root && !childIterators.peek().hasNext()) {
                    parents.pop();
                    childIterators.pop();
                }
                
                while (childIterators.peek().hasNext()) {
                    parents.push(parents.peek().getChild(childIterators.peek().next()));
                    childIterators.push(parents.peek().childKeys().iterator());
                }
                
                if (parents.peek().isWord) {
                    String string = walkParents();
                    parents.pop();
                    childIterators.pop();
                    return string.substring(1);
                }
                
                return endOfData();
            }

            private String walkParents() {
                StringBuilder sb = new StringBuilder();
                for (Iterator<Node> it = parents.descendingIterator(); it.hasNext();) {
                    sb.append(it.next().getValue());
                }
                
                return sb.toString();
            }
          };
    }
    
    @Override
    public Iterator<String> iterator() {
        return iterator("");
    }
    
    private class Node {
        
        private Map<Character, Node> children = new HashMap<Character, Trie.Node>();
        private char c;
        boolean isWord = false;
        
        Node getChild(char c) {
            return children.get(c);
        }
        
        boolean hasChildren() {
            return !children.isEmpty();
        }
        
        char getValue() {
            return c;
        }
        
        Set<Character> childKeys() {
            return children.keySet();
        }
        
        Node addChild(char c) {
            Node node = new Node();
            node.c = c;
            children.put(c, node);
            return node;
        }
        
        @Override
        public String toString() {
            return String.valueOf(c);
        }
    }

}
