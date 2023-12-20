import java.util.*;

public class DecodeString {

    public static void main(String[] args) {
        String message = "t2e1st This i1s f1irs1t";
        String decodedMessage = decodeMessage(message);
        System.out.println("input->" + message);
        System.out.println("output->" + decodedMessage);
    }

    private static String decodeMessage(String message) {
        String[] messageArr = message.split("\\s+");
        Map<Integer, String> map = new TreeMap<>();
        for (String word : messageArr) {
            int length = word.length();
            int key = 0;
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char ch = word.charAt(i);
                if (ch >= 48 && ch <= 57) {
                    key += ch - 48;
                } else {
                    value.append(ch);
                }
            }
            map.put(key, value.toString());
        }
        StringBuilder newString = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            newString.append(entry.getValue()).append(" ");
        }
        return newString.substring(0, newString.length() - 1);
    }
}