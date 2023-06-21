import java.util.Arrays;

/*
Koko Eating Bananas

Given N piles of bananas, the ith pile has piles[i] bananas and H hours time until guards return (N <= H).
Find the minimum (S) bananas to eat per hour such that Koko can eat all the bananas within H hours.
Each hour, Koko chooses some pile of bananas and eats S bananas from that pile.
If the pile has less than S bananas, then she consumes all of them,
and wont eat any more bananas during that hour.

Example 1:
Input:
n = 4
piles = [3, 6, 7, 11]
H = 8
Output:
4
Example 2:
Input:
n = 5
piles = [30, 11, 23, 4, 20]
H = 5
Output:
30
*/
public class KokoEatingBananas {

    public static int Solve(int N, int[] piles, int H) {
        // code here
        int maxElement = Arrays.stream(piles).max().getAsInt();
        int minimumPossibleBananasPerHour = Integer.MAX_VALUE;
        int low = 1, high = maxElement;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalHoursTaken = getTotalHoursTakenByEatingMidBananas(piles, mid, N);
            if (totalHoursTaken <= H) {
                minimumPossibleBananasPerHour = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        //here instead of minimumPossibleBananasPerHour we can also return low
        //as it gives the same value without requiring new variable
        //How-consider {3, 6, 7, 11} or any array with binary search to verify that
        //when low > high, while loop ends and low points to minimumPossibleBananasPerHour
        //and high points to 1 index before low
        return minimumPossibleBananasPerHour;
    }

    private static int getTotalHoursTakenByEatingMidBananas(int[] piles, int bananasPerHour, int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.ceil((double) piles[i] / (double) bananasPerHour);
        }
        return result;
    }

    public static void main(String[] args) {
        //int arr[] = {3, 6, 7, 11};
        int arr[] = {30, 11, 23, 4, 20};
        //System.out.println(Solve(arr.length, arr, 8));
        System.out.println(Solve(arr.length, arr, 5));
    }
}
