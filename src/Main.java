import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Solution solution = new Solution();
        /*ArrayList<Integer> arr  = new ArrayList<Integer>();
        arr.add(2);
        arr.add(8);
        System.out.println(arr);*/

        long A[] = {-3, 1, 1};
        long B[] = {-6, 5, 4};
        int nums[] = {6, 4, 3, 5, 2, 1};
        //int[][] accounts = {{1,2,3},{3,2,1}};
        int[][] accounts = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        //System.out.println(solution.firstRepChar("geeksforgeeks"));
        //System.out.println(Solution.minIndexChar("geeksforgeeks","set"));
        //System.out.println(Solution.strstr("Techie Delight", "light"));
        //System.out.println(solution.fibonacci(9));
        System.out.println(Arrays.toString(solution.getCountOf7In1to1000()));
        //solution.bubbleSort(nums);
        //System.out.println(Arrays.toString(nums));
    }

    public int firstElementKTime(int[] a, int n, int k) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i]) + 1);
            } else {
                map.put(a[i], 1);
            }
        }
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) == k) {
                return i;
            }
        }
        return -1;
    }
}

class pair {
    long first, second;

    public pair(long first, long second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}

class Solution {

    public boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0) return false;
        return true;
    }

    public int[] getTwoLargestNumbers(int[] a) {
        int max = a[0];
        int secondMax = a[1];
        if (max < secondMax) {
            int temp = secondMax;
            secondMax = max;
            max = temp;
        }
        for (int i = 2; i < a.length; i++) {
            if (max < a[i]) {
                secondMax = max;
                max = a[i];
            } else if (secondMax < a[i] && a[i] != max) {
                secondMax = a[i];
            }
        }
        return new int[]{max, secondMax};
    }

    public void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int getOccurencesOf7(int n) {
        int count = 0;
        while(n > 0) {
            if(n % 10 == 7){
                count++;
            }
            n /= 10;
        }
        return count;
    }

    public int[] getCountOf7In1to1000(){
        int a[] = new int[1000];
        for(int i = 0; i < 1000; i++){
            a[i] = getOccurencesOf7(i+1);
        }
        return a;
    }

    public int maximumWealth(int[][] accounts) {
        int sum = 0, rowSum = 0;
        for (int i = 0; i < accounts.length; i++) {
            rowSum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                rowSum += accounts[i][j];
            }
            sum = Math.max(sum, rowSum);
        }
        return sum;
    }


    public int[] runningSum(int[] nums) {
        int[] sumArray = new int[nums.length];
        sumArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArray[i] = nums[i] + sumArray[i - 1];
        }
        return sumArray;
    }

    public static String removeDups(String S) {
        // code here
        StringBuilder uniqueCharacters = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (uniqueCharacters.toString().indexOf(S.charAt(i)) == -1) {
                uniqueCharacters.append(S.charAt(i));
            }
        }
        return uniqueCharacters.toString();
    }

    public static int longestSubstrDistinctChars(String S) {
        // code here
        int[] str = new int[26];
        int start = -1;
        int max = 0;
        Arrays.fill(str, -1);
        for (int i = 0; i < S.length(); i++) {
            if (str[S.charAt(i) - 'a'] > start) {
                start = str[S.charAt(i) - 'a'];
            }
            str[S.charAt(i) - 'a'] = i;
            max = Math.max(max, i - start);
        }
        return max;
    }

    // Finds decimal value of a given roman numeral
    public int romanToDecimal(String str) {
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

    public static String reverseWords(String S) {
        // code here
        String str[] = S.split("[.]");
        String st = "";
        for (int i = str.length - 1; i >= 0; i--) {
            st = st + str[i] + ".";
        }
        return st.substring(0, st.length() - 1);
    }

    public static int strstr(String X, String Y) {
        // if `X` is null or if X's length is less than that of Y's
        if (X == null || Y.length() > X.length()) {
            return -1;
        }

        // if `Y` is null or is empty
        if (Y == null || Y.length() == 0) {
            return 0;
        }

        for (int i = 0; i <= X.length() - Y.length(); i++) {
            int j;
            for (j = 0; j < Y.length(); j++) {
                if (Y.charAt(j) != X.charAt(i + j)) {
                    break;
                }
            }

            if (j == Y.length()) {
                return i;
            }
        }

        return -1;
    }

    public static int minIndexChar(String str, String patt) {
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

    public String firstRepChar(String s) {
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

    String UncommonChars(String A, String B) {
        // code here
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (char x : A.toCharArray()) {
            arr1[x - 'a']++;
        }
        for (char x : B.toCharArray()) {
            arr2[x - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr1[i] == 0 && arr2[i] > 0) {
                sb.append((char) ('a' + i));
            } else if (arr1[i] > 0 && arr2[i] == 0) {
                sb.append((char) ('a' + i));
            }
        }
        if (sb.length() == 0) {
            return "-1";
        }
        char[] arr = sb.toString().toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public boolean isAnagram(String a, String b) {
        // Your code here
        if (a.length() != b.length()) {
            return false;
        }
        int[] aArray = new int[26];
        int[] bArray = new int[26];
        for (int i = 0; i < a.length(); i++) {
            aArray[a.charAt(i) - 'a']++;
            bArray[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (aArray[i] != bArray[i]) {
                return false;
            }
        }
        return true;
    }

    public long minValue(long a[], long b[], long n) {
        // Your code goes here
        Arrays.sort(a);
        Arrays.sort(b);
        long minimumSumOfProduct = 0;
        for (long i = 0; i < n; i++) {
            minimumSumOfProduct += a[(int) i] * b[(int) (n - i - 1)];
        }
        return minimumSumOfProduct;
    }

    public pair[] allPairs(long A[], long B[], long N, long M, long X) {
        // Your code goes here
        Arrays.sort(A);
        Set<Long> bArraySet = new LinkedHashSet<>();
        ArrayList<pair> pairList = new ArrayList<>();
        for (long number : B) {
            bArraySet.add(number);
        }
        for (long number : A) {
            if (bArraySet.contains(X - number)) {
                pairList.add(new pair(number, X - number));
            }
        }
        int counter = 0;
        pair[] pairArray = new pair[pairList.size()];
        for (pair pairObject : pairList) {
            pairArray[counter++] = pairObject;
        }
        return pairArray;
    }

    public String isSubset(long a1[], long a2[], long n, long m) {
        Map<Long, Integer> map1 = new HashMap<>();
        Map<Long, Integer> map2 = new HashMap<>();
        for (int i = 0; i < a1.length; i++) {
            if (map1.containsKey(a1[i])) {
                map1.put(a1[i], map1.get(a1[i]) + 1);
            } else {
                map1.put(a1[i], 1);
            }
        }
        for (int i = 0; i < a2.length; i++) {
            if (map2.containsKey(a2[i])) {
                map2.put(a2[i], map2.get(a2[i]) + 1);
            } else {
                map2.put(a2[i], 1);
            }
        }
        for (Map.Entry<Long, Integer> entry : map2.entrySet()) {
            if (!map1.containsKey(entry.getKey()) || (map1.get(entry.getKey()) < entry.getValue())) {
                return "No";
            }
        }
        return "Yes";
    }

    public static ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int buy = 0; //assuming we buy on day 1
        int sell = 1; // and selling on day 2

        while (sell < n) {
            if (A[buy] > A[sell]) { //if stock value is less on sell we update the buy and sell
                buy = sell;
                sell++;
            } else {
                while (sell + 1 < n && A[sell + 1] >= A[sell]) { //else we will find the maximum sell value
                    sell++;
                }
                ArrayList<Integer> li = new ArrayList<>();
                li.add(buy);
                li.add(sell);
                ans.add(li); // and add the buy and sell in our array
                buy = sell + 1; //don't forget to update the buy and sell again.
                sell = buy + 1;
            }
        }

        return ans;
    }

    //Function to reverse every sub-array group of size k.
    static void reverse(ArrayList<Integer> arr, int low, int high) {
        while (low < high) {
            Collections.swap(arr, low, high);
            low++;
            high--;
        }
    }

    public static void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here
        int temp = n, j = 0;
        while (temp >= k) {
            reverse(arr, j, j + k - 1);
            j += k;
            temp -= k;
        }
        reverse(arr, j, j + temp - 1);
    }

    public static int equilibriumPoint(long arr[], int n) {
        // Your code here
        int leftSum = 0, rightSum = 0, totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }
        rightSum += totalSum;
        for (int i = 0; i < n; i++) {
            rightSum -= arr[i];
            if (leftSum == rightSum) {
                return i + 1;
            }
            leftSum += arr[i];
        }
        return -1;
    }

    static ArrayList<Integer> leaders(int arr[], int n) {
        // Your code here
        ArrayList<Integer> leaders = new ArrayList<>();
        //Stack<Integer> stack = new Stack<>();
        //stack.push(arr[n-1]);
        leaders.add(arr[n - 1]);
        int max = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= max) {
                max = arr[i];
                leaders.add(arr[i]);
            }
        }
        Collections.reverse(leaders);
        /*while(!stack.isEmpty()){
            leaders.add(stack.pop());
        }*/
        return leaders;
    }

    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        // Your code here
        ArrayList<Integer> sumArrayList = new ArrayList<>();
        int sum = 0, leftIndex = 0;
        if (s == 0) {
            sumArrayList.add(-1);
            return sumArrayList;
        }
        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];
            while (sum > s) {
                sum -= arr[leftIndex];
                leftIndex++;
            }
            if (sum == s) {
                sumArrayList.add(leftIndex + 1);
                sumArrayList.add(i + 1);
                return sumArrayList;
            }
        }
        sumArrayList.add(-1);
        return sumArrayList;
    }
}
