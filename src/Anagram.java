public class Anagram {

/*
    Anagram

    Given two strings a and b consisting of lowercase characters.
    The task is to check whether two given strings are an anagram of each other or not. An anagram of a string is another string that contains the same characters, only the order of characters can be different. For example, act and tac are an anagram of each other.
    Note:-
    If the strings are anagrams you have to return True or else return False
    |s| represents the length of string s.
    Example 1:

    Input:a = geeksforgeeks, b = forgeeksgeeks
    Output: YES
    Explanation: Both the string have same characters with
    same frequency. So, both are anagrams.*/

    //Function is to check whether two strings are anagram of each other or not.
    public static boolean isAnagram(String a,String b)
    {
        // Your code here
        if(a.length()!=b.length())
        {
            return false;
        }
        int[] aArray = new int[26];
        int[] bArray = new int[26];
        for(int i=0;i<a.length();i++)
        {
            aArray[a.charAt(i)-'a']++;
            bArray[b.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++)
        {
            if(aArray[i]!=bArray[i])
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("cat","act"));
    }
}
