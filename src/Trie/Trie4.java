package Trie;

/*
Count of distinct substrings

Given a string of length N of lowercase alphabet characters.
The task is to complete the function countDistinctSubstring(),
which returns the count of total number of distinct substrings of this string.
Input:
The first line of input contains an integer T,
denoting the number of test cases. Then T test cases follow. Each test case contains a string str.
Output:
For each test case in a new line,
output will be an integer denoting count of total number of distinct substrings of this string.
*/
public class Trie4 {

    static class Node {

        protected static final int ALPHABET_SIZE = 26;
        Node[] links = new Node[ALPHABET_SIZE];

        public boolean containsKey(char ch) {
            return (links[ch - 'a'] != null);
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }
    }

    public static int countDistinctSubstrings(String word) {
        int count = 0;
        Node root = new Node();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            Node node = root;
            for (int j = i; j < n; j++) {
                char ch = word.charAt(j);
                if (!node.containsKey(ch)) {
                    node.put(ch, new Node());
                    count++;
                }
                node = node.get(ch);
            }
        }
        //1 added more for empty substring
        return count + 1;
    }

    public static void main(String[] args) {
        String word = "abab";
        System.out.println(countDistinctSubstrings(word));
    }
}