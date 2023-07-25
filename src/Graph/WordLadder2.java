package Graph;

import java.util.*;

/*
Word Ladder II

Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths.
Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.
Keep the following conditions in mind:
A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList.
Return an empty list if there is no such transformation sequence.
The first part of this problem can be found here.
Example 1:
Input:
startWord = "der", targetWord = "dfs",
wordList = {"des","der","dfr","dgt","dfs"}
Output:
der dfr dfs
der des dfs
Explanation:
The length of the smallest transformation is 3.
And the following are the only two ways to get
to targetWord:-
"der" -> "des" -> "dfs".
"der" -> "dfr" -> "dfs".
Example 2:
Input:
startWord = "gedk", targetWord = "geek",
wordList = {"geek", "gefk"}
Output:
"gedk" -> "geek"
*/
public class WordLadder2 {

    //Time Complexity and Space Complexity: It cannot be predicted for this particular algorithm
    //because there can be multiple sequences of transformation from startWord to targetWord depending upon the example,
    //so we cannot define a fixed range of time or space in which this program would run for all the test cases.
    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
        // Code here
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Queue<ArrayList<String>> queue = new LinkedList<>();
        ArrayList<String> firstWordList = new ArrayList<>();
        firstWordList.add(startWord);
        queue.add(firstWordList);
        Set<String> set = new HashSet<>(Arrays.asList(wordList));
        int level = 0;
        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(startWord);
        // BFS traversal with pushing the new formed sequence in queue
        // when after a transformation, a word is found in wordList.
        while (!queue.isEmpty()) {
            ArrayList<String> vec = queue.peek();
            queue.remove();
            // Now, erase all words that have been
            // used in the previous levels to transform
            if (vec.size() > level) {
                level++;
                for (String s : usedOnLevel) {
                    set.remove(s);
                }
            }
            //get the last word from current transformation
            String word = vec.get(vec.size() - 1);
            // store the answers if the end word matches with targetWord.
            if (word.equals(targetWord)) {
                // the first sequence where we reached the end.
                if (result.size() == 0) {
                    result.add(vec);
                } else if (result.get(0).size() == vec.size()) {
                    result.add(vec);
                }
            }
            for (int i = 0; i < word.length(); i++) {
                // Now, replace each character of ‘word’ with char
                // from a-z then check if ‘word’ exists in wordList.
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replaceWordArr = word.toCharArray();
                    replaceWordArr[i] = ch;
                    String replacedWord = new String(replaceWordArr);
                    if (set.contains(replacedWord)) {
                        vec.add(replacedWord);
                        // otherwise if you remove word from vec in next lines, it will
                        // remove from everywhere
                        ArrayList<String> temp = new ArrayList<>(vec);
                        queue.add(temp);
                        // mark as visited on the level
                        usedOnLevel.add(replacedWord);
                        vec.remove(vec.size() - 1);
                    }
                }
            }
        }
        return result;
    }

    String b;
    HashMap<String, Integer> mpp;
    ArrayList<ArrayList<String>> ans;


    public ArrayList<ArrayList<String>> findSequencesOptimised(String startWord, String targetWord, String[] wordList) {
        // Code here
        Set<String> set = new HashSet<>(Arrays.asList(wordList));
        Queue<String> queue = new LinkedList<>();
        b = startWord;
        queue.add(startWord);
        mpp = new HashMap<>();
        mpp.put(startWord, 1);
        int size = startWord.length();
        set.remove(startWord);
        while (!queue.isEmpty()) {
            String word = queue.peek();
            queue.remove();
            int steps = mpp.get(word);
            if (word.equals(targetWord)) {
                break;
            }
            for (int i = 0; i < size; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArr = word.toCharArray();
                    replacedCharArr[i] = ch;
                    String replacedWord = new String(replacedCharArr);
                    if (set.contains(replacedWord)) {
                        queue.add(replacedWord);
                        set.remove(replacedWord);
                        mpp.put(replacedWord, steps + 1);
                    }
                }
            }
        }
        ans = new ArrayList<>();
        if (mpp.containsKey(targetWord)) {
            List<String> seq = new ArrayList<>();
            seq.add(targetWord);
            dfs(targetWord, seq);
        }
        return ans;
    }

    private void dfs(String word, List<String> seq) {
        if (word.equals(b)) {
            ArrayList<String> duplicate = new ArrayList<>(seq);
            Collections.reverse(duplicate);
            ans.add(duplicate);
            return;
        }
        int steps = mpp.get(word);
        int size = word.length();
        for (int i = 0; i < size; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char[] replacedCharArr = word.toCharArray();
                replacedCharArr[i] = ch;
                String replacedWord = new String(replacedCharArr);
                if (mpp.containsKey(replacedWord) && mpp.get(replacedWord) + 1 == steps) {
                    seq.add(replacedWord);
                    dfs(replacedWord, seq);
                    seq.remove(seq.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        WordLadder2 wl2 = new WordLadder2();
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        System.out.println(wl2.findSequences(startWord, targetWord, wordList));
        System.out.println(wl2.findSequencesOptimised(startWord, targetWord, wordList));
    }
}
