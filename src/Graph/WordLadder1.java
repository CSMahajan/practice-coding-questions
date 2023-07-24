package Graph;

import java.util.*;

/*
Word Ladder I

Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths.
Find the length of the shortest transformation sequence from startWord to targetWord.
Keep the following conditions in mind:
A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList
The second part of this problem can be found here.
Note: If no possible way to transform sequence from startWord to targetWord return 0
Example 1:
Input:
wordList = {"des","der","dfr","dgt","dfs"}
startWord = "der", targetWord= "dfs",
Output: 3
Explanation:
The length of the smallest transformation
sequence from "der" to "dfs" is 3
i,e "der" -> "dfr" -> "dfs".
Example 2:
Input:
wordList = {"geek", "gefk"}
startWord = "gedk", targetWord= "geek",
Output: 2
Explanation:
gedk -> geek
Example 3:
Input:
wordList = {"poon", "plee", "same", "poie","plea","plie","poin"}
startWord = "toon", targetWord= "plea",
Output: 7
Explanation:
toon -> poon -> poin -> poie -> plie -> plee -> plea
*/
public class WordLadder1 {

    //Time Complexity: O(N * M * 26)
    //Where N = size of wordList Array and M = word length of words present in the wordList.
    //Note that, hashing operations in an unordered set takes O(1) time, but if you want to use set here,
    //then the time complexity would increase by a factor of log(N) as hashing operations in a set take O(log(N)) time.
    //Space Complexity:  O( N ) { for creating an unordered set and copying all values from wordList into it }
    //Where N = size of wordList Array.
    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        // Code here
        //We need to perform the BFS traversal in order to change 1 character at a time and
        //check if it exists in the given list of words
        //which is nothing but level order traversal(BFS),
        //pair stores the word reached and the number of steps needed to reach that level
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startWord, 1));
        //We require set in order to find the word if it exists in the given list of words
        Set<String> set = new HashSet<>(Arrays.asList(wordList));
        set.remove(startWord);
        //Performing BFS traversal on given list of words
        while (!queue.isEmpty()) {
            String word = queue.peek().word;
            int steps = queue.peek().steps;
            queue.remove();
            //if we find the targetWord from the queue, will return the number of steps
            if (word.equals(targetWord)) {
                return steps;
            }
            //for each character in the word from queue polled, we will replace with letters from a to z check if it exists set of words
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    //below 3 steps are done just to replace one character in the word
                    char[] replacedWordArr = word.toCharArray();
                    replacedWordArr[i] = ch;
                    String replacedWord = new String(replacedWordArr);
                    if (set.contains(replacedWord)) {
                        //adding the character replaced new word with increased steps in the queue
                        queue.add(new Pair(replacedWord, steps + 1));
                        //need to remove the matching new replaced character word because in future other occurrence, the same word might appear,
                        //but we require the minimum number of steps needed to reach targetWord
                        set.remove(replacedWord);
                    }
                }
            }
        }
        return 0;
    }

    static class Pair {
        String word;
        int steps;
        public Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        String[] wordList = {"poon", "plee", "same", "poie","plea","plie","poin"};
        String startWord = "toon", targetWord= "plea";
        WordLadder1 wl1 = new WordLadder1();
        System.out.println(wl1.wordLadderLength(startWord,targetWord,wordList));
    }
}