import java.util.Arrays;

/*
You are given an array consisting of n integers which denote the position of a stall. You are also given an integer k which denotes the number of aggressive cows. You are given the task of assigning stalls to k cows such that the minimum distance between any two of them is the maximum possible.
The first line of input contains two space-separated integers n and k.
The second line contains n space-separated integers denoting the position of the stalls.
Example 1:
Input:
n=5
k=3
stalls = [1 2 4 8 9]
Output:3
Explanation:
The first cow can be placed at stalls[0], the second cow can be placed at stalls[2] and
the third cow can be placed at stalls[3].
The minimum distance between cows, in this case, is 3, which also is the largest among all possible ways.
Example 2:
Input:
n=5
k=3
stalls = [10 1 2 7 5]
Output:4
Explanation:
The first cow can be placed at stalls[0], the second cow can be placed at stalls[1] and
the third cow can be placed at stalls[4].
The minimum distance between cows, in this case, is 4,which also is the largest among all possible ways.
*/
public class AggressiveCows {

    /*TC:O(N*log(N)) + O(log(maxElement-minElement))
      N * log(N) for sorting the given array and
      O(log(maxElement-minElement)) for binary search for finding
      minimum distance between any counter which is max possible
      SC:O(1)
      We are not using any extra space
    */
    public static int solve(int n, int k, int[] stalls) {
        //sorting will help us in placing cows at appropriate counters in sequential and all cows to be placed manner
        Arrays.sort(stalls);
        /*After sorting our required minimum distance between any counter which is max possible,
        so minimum distance between any counters can be 1 because 2 cows can not be placed at same counter
        and maximum distance can be maximum element of the array,
        e.g. no of cows = 2, then first cow placed at starting counter and second cow placed at ending counter
        so low = 1 and high = maxElement - minElement
         */

        int low = 1, high = stalls[n - 1] - stalls[0];
        int maximumPossibleDistance = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canWePlaceCowsWithMidAsMinimumDistanceBetweenCounters(stalls, mid, k)) {
                maximumPossibleDistance = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        /*
        Note: Opposite polarity in binary search always holds, if low starts from possible case, ends up at impossible index
        and high starts from impossible case, ends up at possible index which is max possible
        Vice versa case for low and high also exist for impossible low to possible low which occurs
        for binary search to find min possible and high at possible to impossible case
        */
        //We can also return high, as at the end of binary search high and max distance remains same
        return maximumPossibleDistance;
    }

    private static boolean canWePlaceCowsWithMidAsMinimumDistanceBetweenCounters(int[] stalls, int minimumDistanceBetweenCounters, int totalNoOfCowsToPlace) {
        int noOfCowsPlaced = 1, lastCowPlacedCounterPosition = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastCowPlacedCounterPosition >= minimumDistanceBetweenCounters) {
                lastCowPlacedCounterPosition = stalls[i];
                noOfCowsPlaced++;
            }
        }
        return noOfCowsPlaced >= totalNoOfCowsToPlace;
    }

    public static void main(String[] args) {
        int[] stalls = {1, 2, 4, 8, 9};
        int n = stalls.length;
        int totalCows = 3;
        System.out.println(solve(n, totalCows, stalls));
    }
}
