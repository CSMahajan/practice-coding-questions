public class FirstRepeatedCharacter {

    /*
    Find first repeated character

    Given a string S. The task is to find the first repeated character in it.
    We need to find the character that occurs more than once and whose index of second occurrence is smallest.
    S contains only lowercase letters.
    Example 1:

    Input: S="geeksforgeeks"
    Output: e
    Explanation: 'e' repeats at third position.*/

    public static String firstRepChar(String s) {
        // code here
        int hash[] = new int[26];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count = s.charAt(i) - 'a';
            hash[count]++;
            if (hash[count] > 1) {
                return String.valueOf((char) ('a' + count));
            }
        }
        return "-1";
    }
}
