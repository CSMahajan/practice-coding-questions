package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Alien Dictionary

Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary.
Find the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order and
output will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.
Example 1:
Input:
N = 5, K = 4
dict = {"baa","abcd","abca","cab","cad"}
Output:
1
Explanation:
Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted
and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
Similarly, we can find other orders.
Example 2:
Input:
N = 3, K = 3
dict = {"caa","aaa","aab"}
Output:
1
Explanation:
Here order of characters is 'c', 'a', 'b' Note that words are sorted
and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output.
Similarly, we can find other orders.
*/
public class AlienDictionary {

    //Time Complexity: O(N*len)+O(K+E), where N is the number of words in the dictionary,
    //‘len’ is the length up to the index where the first inequality occurs, K = no. of nodes, and E = no. of edges.
    //Space Complexity: O(K) + O(K)+O(K)+O(K) ~ O(4K), O(K) for the indegree array,
    //and O(K) for the queue data structure used in BFS(where K = no.of nodes),
    //O(K) for the answer array and O(K) for the adjacency list used in the algorithm.
    public String findOrder(String[] dict, int N, int K) {
        // Write your code here
        /*
        Firstly, we will create the adjacency list from given array of words in the alien dictionary.
        This is done by identifying distinguishing characters(letters of alphabets in dictionary) as the directed edges in the graph.
        Which further makes us simple application of topological sort algorithm.
        Here we have to find topological sort of first K characters in dictionary.
        Convert the received topological order into the character string.

        The topological order of characters is not possible under below 2 conditions:
        1.If all the characters of any two words match but word with more characters is placed before word with fewer characters.
        e.g. s1="abcd", s2="abc".
        Here all the three letters ('a','b','c') match but abcd(length = 4) is placed before abc(length = 2)
        in the alien's dictionary which generally does not happen
        2.If we find the cyclic dependency or having cycle in the directed graph that we created from given words.
        e.g.s1="abc",s2="bca",s3="acb". Here while comparing s1 and s2, we can find 'a' appears before 'b',
        but while comparing s2 and s3, we get that 'b' appears before 'a'.
        This causes cyclic dependency in the directed graph
        */
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            String firstWord = dict[i];
            String secondWord = dict[i + 1];
            int length = Math.min(firstWord.length(), secondWord.length());
            for (int j = 0; j < length; j++) {
                if (firstWord.charAt(j) != secondWord.charAt(j)) {
                    //We found non-matching character, so we will add the directed edge from character of first word to the character of second word
                    adj.get(firstWord.charAt(j) - 'a').add(secondWord.charAt(j) - 'a');
                    break;
                }
            }
        }
        //finding topological sort of the first K characters in the dictionary
        List<Integer> topologicalSortList = topoSort(K, adj);
        StringBuilder topologicalOrder = new StringBuilder();
        for (int vertex : topologicalSortList) {
            topologicalOrder.append((char) (vertex + (int) 'a'));
        }
        return topologicalOrder.toString();
    }

    public static List<Integer> topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // add your code here
        Queue<Integer> queue = new LinkedList<>();
        //in-degree array is incoming edge array for all the vertices
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int adjacentNode : adj.get(i)) {
                //increasing the count of every incoming edge to that vertex for every adjacent node of our current node
                inDegree[adjacentNode]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> topologicalSortList = new ArrayList<>();
        //queue contains the elements whose in-degree is 0 and its order is maintained
        //so adding it to topological sort order
        while (!queue.isEmpty()) {
            int node = queue.peek();
            topologicalSortList.add(node);
            queue.remove();
            //node is now in our topological order, so remove it from in-degree
            for (int adjacentNode : adj.get(node)) {
                inDegree[adjacentNode]--;
                //after reducing the inDegree for the current adjacentNode, check if inDegree is 0 means that can now be part of topological sort order
                if (inDegree[adjacentNode] == 0) {
                    queue.add(adjacentNode);
                }
            }
        }
        return topologicalSortList;
    }

    public static void main(String[] args) {
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        AlienDictionary ad = new AlienDictionary();
        String ans = ad.findOrder(dict, N, K);
        for (int i = 0; i < ans.length(); i++) {
            System.out.print(ans.charAt(i) + " ");
        }
    }
}
