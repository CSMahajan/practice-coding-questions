package Trie;

/*
1) Trie():
Ninja has to initialize the object of this “TRIE” data structure.

2) insert(“WORD®):
Ninja has to insert the String “WORD” into this “TRIE” data structure.

3) countWordsEqualTo(“WORD”):
Ninja has to return how many times this “WORD” is present in this “TRIE”.

4) countWordsStartingWith(“PREFIX”):
have to return how many words are there in this “TRIE” that have the string “PREFIX” as a prefix.

5) erase(“WORD”):
Ninja has to delete one occurrence of the string “WORD” from the “TRIE”.
*/
public class Trie2 {

    static class Node {
        protected static final int ALPHABET_SIZE = 26;
        protected Node[] links = new Node[ALPHABET_SIZE];
        protected int countEndsWith = 0;
        protected int countPrefix = 0;

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        public void increaseCountEndsWith() {
            countEndsWith++;
        }

        public void increaseCountPrefix() {
            countPrefix++;
        }

        public void reduceCountEndsWith() {
            countEndsWith--;
        }

        public void reduceCountPrefix() {
            countPrefix--;
        }

        public int getCountEndsWith() {
            return countEndsWith;
        }

        public int getCountPrefix() {
            return countPrefix;
        }
    }

    private final Node root;

    public Trie2() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
            node.increaseCountPrefix();
        }
        node.increaseCountEndsWith();
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return 0;
            }
        }
        return node.getCountEndsWith();
    }

    public int countWordsStartingWith(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return 0;
            }
        }
        return node.getCountPrefix();
    }

    public void erase(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
                node.reduceCountPrefix();
            } else {
                return;
            }
        }
        node.reduceCountEndsWith();
    }

    public static void main(String[] args) {
        Trie2 trie = new Trie2();
        trie.insert("apple");
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("apps");
        String word1 = "apps";
        System.out.println("Count Words Equal to " + word1 + " " + trie.countWordsEqualTo(word1));
        String word2 = "abc";
        System.out.println("Count Words Equal to " + word2 + " " + trie.countWordsEqualTo(word2));
        String word3 = "ap";
        System.out.println("Count Words Starting With " + word3 + " " + trie.countWordsStartingWith
                (word3));
        String word4 = "appl";
        System.out.println("Count Words Starting With " + word4 + " " + trie.countWordsStartingWith
                (word4));
        trie.erase(word1);
        System.out.println("Count Words equal to " + word1 + " " + trie.countWordsEqualTo(word1));
    }
}
