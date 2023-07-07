package BinarySearch;

/*
K-th missing element

Given an increasing sequence a[],
we need to find the K-th missing contiguous element in the increasing sequence
which is not present in the sequence. If no k-th missing element is there output -1.

Example 1:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

Example 3:
Input : arr[ ] = {1, 3, 4, 5, 7} and K = 2
Output : 6
Explanation:
K = 2, We need to find the 2nd missing
number in the array. Missing numbers are
2 and 6. So 2nd missing number is 6.

Example 2:
Input : arr[ ] = {1, 2, 3, 4, 5, 6} and K = 1
Output :  -1
Explanation: no numbers are missing in this sequence
*/
public class KthMissingNumber {

    public static int KthMissingElement(int a[], int n, int k) {
        // Complete the function
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            /*Missing is calculated as sequence should start from 1 to n
            and our low to high is starting from 0 to n-1
            So at "mid" index the total missing numbers can be derived using below formula
            Example: arr = [2,3,4,7,11], k = 5
            for lets say this array indexing starts from 0 to n-1
            and our sequence should start from 1 to n.
            So, consider element 7 in an array, the array after adding all missing numbers can be considered as
            (1),2,3,4,(5),(6),7 in order to complete the sequence,
            where numbers in bracket represent missing numbers in an actual array.
            As 7 is at 3rd index(0-based indexing i.e.0 to n-1).
            So total missing numbers here are 7 - (3 + 1) = 3
            Here +1 is done to adjust to 1 to n sequence completion on 0-based indexing.
            That's how the formula can be generated
             */
            int totalMissingNumbers = a[mid] - (mid + 1);
            if (totalMissingNumbers < k) {
                //Example:
                //If 3 were totalMissingNumbers till "mid" index and we need to find 5th index,
                //so it would be on the right side
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        /*To derive the Kth missing number considered below simple maths again with previous example
        arr = [2,3,4,7,11], k = 5, after considering missing numbers
        (1),2,3,4,(5),(6),7,(8),(9),(10),11
        After binary search loop ends, we have high = 3, low = 4
        We have arr[high] = 7 and till 7 or arr[high], we only had 3 missing numbers
        So here we basically require something "more" to find or reach till 5th missing number(because k=5),
        so more = k - totalMissingNumbers ( 5-3) , so more becomes 2,
        so our required answer becomes arr[high] + more, from our derived previous formula for totalMissingNumbers
        ans = arr[high] + more
        = arr[high] + k - totalMissingNumbers
        = arr[high] + k - (arr[high] - (high + 1))
        = arr[high] + k - (arr[high] - high - 1)
        = arr[high] + k - arr[high] + high + 1
        = k + high + 1
        as low = high + 1
        ans = low + 1 or k + high + 1
        Here we can also return low + 1 as when low crosses high at the end of while loop (opposite polarity)
        low becomes one step ahead of high, so low = high + 1
         */
        return k + high + 1;
    }

    public static void main(String[] args) {
        int arr[] = {2,3,4,7,11};
        int n = arr.length;
        int k = 5;
        System.out.println(KthMissingElement(arr,n,k));
    }
}
