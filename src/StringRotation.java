public class StringRotation {

    /*
    Check if string is rotated by two places

    Given two strings a and b. The task is to find if the string 'b' can be obtained by rotating another string 'a' by exactly 2 places.

    Example 1:

    Input:
    a = amazon
    b = azonam
    Output: 1
    Explanation: amazon can be rotated anticlockwise by two places, which will make it as azonam.*/

    // Method to check if string2 is obtained by string 1
    //Function to check if a string can be obtained by rotating
    //another string by exactly 2 places.
    public static boolean isRotated(String str1, String str2) {
        // Your code here
        if (str1.length() != str2.length())
            return false;
        if (str1.length() < 2) {
            return str1.equals(str2);
        }

        String clockwiseRotation = "";
        String anticlockwiseRotation = "";
        int len = str2.length();

        // Initialize string as anti-clockwise rotation
        anticlockwiseRotation = anticlockwiseRotation +
                str2.substring(len - 2, len) +
                str2.substring(0, len - 2);

        // Initialize string as clock wise rotation
        clockwiseRotation = clockwiseRotation +
                str2.substring(2) +
                str2.substring(0, 2);

        // check if any of them is equal to string1
        return (str1.equals(clockwiseRotation) ||
                str1.equals(anticlockwiseRotation));
    }
    //-----------Other solutions-----------//

    //Method to check if string2 is obtained by string1
    public static boolean isRotated1(String str1, String str2)
    {
        int n = str1.length();
        int m = str2.length();
        if (n != m) //check both the string length equal or not
            return false;
        boolean clockwise = true;
        boolean anticlockwise = true;
        //check clockwise rotation is possible or not
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i)!= str2.charAt((i + 2) % n)) {
                clockwise = false;
                break;
            }
        }
        // check anticlockwise rotation is possible or not
        for (int i = 0; i < m; i++) {
            if (str1.charAt((i + 2) % n)!= str2.charAt(i)) {
                anticlockwise = false;
                break;
            }
        }
        return (clockwise || anticlockwise);
    }
}
