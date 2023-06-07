public class MobileNumericKeypadSequence {

    /*
    Convert a sentence into its equivalent mobile numeric keypad sequence

    Given a sentence in the form of a string in uppercase,
    convert it into its equivalent mobile numeric keypad sequence.
    Please note there might be spaces in between the words in a sentence and we can print spaces by pressing 0.

    Note: Basically make old phones keypad of mobiles

    Input:
    S = "GFG"
    Output: 43334
    Explanation: For 'G' press '4' one time.
    For 'F' press '3' three times.*/

    public static String printSequence(String S)
    {
        // code here
        String arr[]
                = { "2",    "22",  "222", "3",   "33", "333",
                "4",    "44",  "444", "5",   "55", "555",
                "6",    "66",  "666", "7",   "77", "777",
                "7777", "8",   "88",  "888", "9",  "99",
                "999",  "9999" };
        String output = "";

        // length of input string
        int n = S.length();
        for (int i = 0; i < n; i++) {
            // Checking for space
            if (S.charAt(i) == ' ')
                output = output + "0";
            else {
                // Calculating index for each
                // character
                int position = S.charAt(i) - 'A';
                output = output + arr[position];
            }
        }
        // Output sequence
        return output;
    }

}
