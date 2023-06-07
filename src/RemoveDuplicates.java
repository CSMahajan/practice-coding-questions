public class RemoveDuplicates {

    /*
    Remove Duplicates

    Given a string without spaces, the task is to remove duplicates from it.
    Note: The original order of characters must be kept the same.
    Example 1:

    Input: S = "zvvo"
    Output: "zvo"
    Explanation: Only keep the first occurrence*/

    public static String removeDups(String S) {
        // code here
        boolean[] isUniqueCharacter = new boolean[26];
        StringBuilder uniqueCharacters = new StringBuilder();
        for (char ch : S.toCharArray()) {
            int indexFromCharacter = ch - 'a';
            if (!isUniqueCharacter[indexFromCharacter]) {
                isUniqueCharacter[indexFromCharacter] = true;
                uniqueCharacters.append(ch);
            }
        }
        return new String(uniqueCharacters);
    }

    //------Other solution------------------
    public static String removeDups1(String S) {
        // code here
        StringBuilder uniqueCharacters = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (uniqueCharacters.toString().indexOf(S.charAt(i)) == -1) {
                uniqueCharacters.append(S.charAt(i));
            }
        }
        return uniqueCharacters.toString();
    }
}
