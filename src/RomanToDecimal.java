import java.util.HashMap;

public class RomanToDecimal {

    /*Roman Number to Integer

    Given a string in roman no format (s)  your task is to convert it to an integer.
    Various symbols and their values are given below.
    I 1
    V 5
    X 10
    L 50
    C 100
    D 500
    M 1000

    Example 1:

    Input:
    s = V
    Output: 5*/

    // Finds decimal value of a given roman numeral
    public static int romanToDecimal(String str) {
        // code here
        int ans = 0;
        int[] roman = new int[128];
        roman['I'] = 1;
        roman['V'] = 5;
        roman['X'] = 10;
        roman['L'] = 50;
        roman['C'] = 100;
        roman['D'] = 500;
        roman['M'] = 1000;
        for (int i = 0; i + 1 < str.length(); ++i)
            if (roman[str.charAt(i)] < roman[str.charAt(i + 1)])
                ans -= roman[str.charAt(i)];
            else
                ans += roman[str.charAt(i)];
        return ans + roman[str.charAt(str.length() - 1)];
    }

    //--------Other solution --------------------//
    public static int romanToDecimal1(String str) {
        // code here
        HashMap<Character, Integer> al = new HashMap<>();
        al.put('I', 1);
        al.put('V', 5);
        al.put('X', 10);
        al.put('L', 50);
        al.put('C', 100);
        al.put('D', 500);
        al.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (al.get(str.charAt(i)) < al.get(str.charAt(i + 1))) {
                ans -= al.get(str.charAt(i));
            } else {
                ans += al.get(str.charAt(i));
            }
        }
        ans += al.get(str.charAt(str.length() - 1));
        return ans;
    }
}