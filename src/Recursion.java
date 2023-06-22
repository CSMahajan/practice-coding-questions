public class Recursion {

    public static int isPalindrome(String S) {
        // code here
        boolean isPal = checkPalindrome(S.toLowerCase().replaceAll("[^a-zA-Z0-9]", ""), 0);
        return isPal ? 1 : 0;
    }

    private static boolean checkPalindrome(String s, int i) {
        if(i >= s.length() / 2) {
            return true;
        }
        if(s.charAt(i) != s.charAt(s.length() - i - 1)) {
            return false;
        }
        return checkPalindrome(s, i + 1);
    }




    public static void main(String[] args) {
        Recursion r = new Recursion();
        //r.printNos(5);
        System.out.println(isPalindrome("abcbae"));
    }
}
