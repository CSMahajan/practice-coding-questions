package Trie;

/*
Ninja developed a love for arrays and strings so this time his teacher gave him an array of strings, “A” of size N.
Each element of this array is a string.
The teacher taught Ninja about prefixes in the past, so he wants to test his knowledge.
A string is called a complete string if every prefix of this string is also present in the array °A". Ninja is challenged
to find the longest complete string in the array "A" there are multiple strings with the same length,
return the lexicographically smallest one and if no string exists, return "None".
Note :
String 'P' is lexicographically smaller than string ‘Q’, if
1. There exists some index i’ such that for all 3’ < ©
“P[3] = Q[3]’ and “P[i] < Q[i]’. E.g. “ninja” < “noder”
2. If ‘P* is a prefix of string Q, e.g. “code” < “coder”.
Example :
Explanation :
Only prefix of the string “a” is “a” which is present in array ‘A’. So, it is one of the possible strings.
Prefixes of the string “ab” are “a” and “ab” both of which are present in array ‘A’. So, it is one of the possible strings.
Prefixes of the string “bp” are “b” and “bp”. “b” is not present in array ‘A’. So, it cannot be a valid string.
Prefixes of the string “abc” are “a”, “ab” and “abc” all of which are present in array ‘A’. So, it is one of the possible strings.
We need to find the maximum length string, so “abc” is the required string.
Example:
Sample Input 1 :
2
6
n ni nin ninj ninja ninga
2
ab bc
Sample Output 1 :
ninja
None
Explanation Of Sample Input 1 :
For test case 1 we have,
All the prefixes of “ninja” -> “n”, “ni”, “nin”, “ninj” and “ninja” are present in array ‘A’.
So, “ninja” is a valid answer whereas for “ninga” , the prefix “ning” is not present in array ‘A’.
So we output “ninja”.
For test case 2 we have,
The prefixes of “ab” are “a” and “ab”. “a” is not present in array ‘A’. So, “ab” is not a valid answer.
The prefixes of “bc” are “b” and “bc”. “b” is not present in array ‘A’. So, “ab” is not a valid answer.
Since none of the strings is a valid answer we output “None”.
*/
public class Trie3 {

    static class Node {

        protected static final int ALPHABET_SIZE = 26;
        Node[] links = new Node[ALPHABET_SIZE];
        boolean endOfWord = false;

        public boolean containsKey(char ch) {
            return (links[ch - 'a'] != null);
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        public void setEndOfWord() {
            endOfWord = true;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }
    }

    private final Node root;

    Trie3() {
        root = new Node();
    }

    public static String completeString(int n, String[] a) {
        // Write your code here.
        Trie3 trie = new Trie3();
        String longest = "";
        for (String word : a) {
            trie.insert(word);
        }
        for (String word : a) {
            if (trie.checkIfAllPrefixExists(word)) {
                if (longest.length() < word.length()) {
                    longest = word;
                } else if (longest.length() == word.length() && word.compareTo(longest) < 0) {
                    longest = word;
                }
            }
        }

        if (longest.isEmpty()) {
            return "None";
        }
        return longest;
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEndOfWord();
    }

    private boolean checkIfAllPrefixExists(String word) {
        Node node = root;
        boolean flag = true;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
                if (!node.isEndOfWord()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String[] arr = {"n", "ninj","nin", "ni", "ninja", "ninga"};
        int n = arr.length;
        System.out.println(completeString(n, arr));
    }
}