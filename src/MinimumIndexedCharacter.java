public class MinimumIndexedCharacter {

    /*
    Minimum indexed character

    Given a string str and another string patt. Find the minimum index of the character in str that is also present in patt.
    Example 1:

    Input:
    str = geeksforgeeks
    patt = set
    Output: 1
    Explanation: e is the character which is present in given str "geeksforgeeks"
    and is also presen in patt "set". Minimum index of e is 1.*/

    //Function to find the minimum indexed character.
    public static int minIndexChar(String str, String patt)
    {
        // Your code here
        int[] hashPatt = new int[26];
        for (int i = 0; i < patt.length(); i++) {
            hashPatt[patt.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashPatt[str.charAt(i) - 'a'] >= 1) {
                return i;
            }
        }
        return -1;
    }
}
