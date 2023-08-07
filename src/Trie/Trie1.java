package Trie;

/*
Implement Trie (Prefix Tree)

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and
retrieve keys in a dataset of strings.
There are various applications of this data structure, such as autocomplete and spellchecker.
Implement the Trie1 class:
Trie1() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true
if there is a previously inserted string word that has the prefix prefix, and false otherwise.
Example 1:
Input
["Trie1", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]
Explanation
Trie1 trie = new Trie1();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
*/
public class Trie1 {
    static class Node {

        protected static final int ALPHABET_SIZE = 26;
        Node[] links = new Node[ALPHABET_SIZE];

        boolean flag = false;

        public Node() {
        }

        public boolean containsKey(char ch) {
            return (links[ch - 'a'] != null);
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            flag = true;
        }

        public boolean isEnd() {
            return flag;
        }
    }

    private final Node root;

    public Trie1() {
        root = new Node();
    }

    //This method inserts a given word into trie
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                //creating a new reference trie node for the letter if it does not exist at current index
                node.put(word.charAt(i), new Node());
            }
            //navigating to newly created trie node reference
            node = node.get(word.charAt(i));
        }
        //marking end of word as true (by setting flag as true) to denote word ends here
        node.setEnd();
    }

    //This method searches if given word exists in trie data structure
    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                //current letter does not exist, so this word would also not exist
                return false;
            }
            //moving on to current letters pointing reference trie node
            node = node.get(word.charAt(i));
        }
        //at end of word, flag should be compulsorily true which denotes word already stored in trie ends here
        //if it was false, the word found matching till now is part of another word(current word is prefix to another word)
        return node.isEnd();
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.containsKey(prefix.charAt(i))) {
                //current letter does not exist, so this word would also not exist
                return false;
            }
            //moving on to current letters pointing reference trie node
            node = node.get(prefix.charAt(i));
        }
        //as we are looking for prefix only using startsWith method, exact trie node structure matched, so returning true
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        //for the sake of understanding trie1 data structure, we are creating type array, where its type values can be 1,2,3
        //type value 1 denotes insert a given word
        //type value 2 denotes search a given word if its exists or not
        //type value 3 denotes search if a given prefix exists or not
        int[] type = {1, 1, 2, 3, 2};
        String[] value = {"hello", "help", "help", "hel", "hel"};
        Trie1 trie1 = new Trie1();
        for (int i = 0; i < n; i++) {
            if (type[i] == 1) {
                trie1.insert(value[i]);
            } else if (type[i] == 2) {
                if (trie1.search(value[i])) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            } else {
                if (trie1.startsWith(value[i])) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            }
        }
    }
}