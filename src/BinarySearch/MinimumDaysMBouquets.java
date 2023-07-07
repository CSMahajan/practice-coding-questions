package BinarySearch;

import java.util.Arrays;

/*
Minimum days to make M bouquets

To make one bouquet we need K adjacent flowers from the garden.
Here the garden consists of N different flowers, the ith flower will bloom in the bloomDay[i].
Each flower can be used inside only one bouquets.
We have to find the minimum number of days need to wait to make M bouquets from the garden.
If we cannot make m bouquets, then return -1.

The first line of input contains two integers M and K.
The second line contains N space-separated integers of bloomDay[i] array.

Example 1:
Input:
M = 2, K = 3
bloomDay = [5, 5, 5, 5, 10, 5, 5]
Output:
10
Explanation:
As we need 2 (M = 2) bouquets and each should have 3 flowers,
After day 5: [x, x, x, x, _, x, x], we can make one bouquet of
the first three flowers that bloomed, but cannot make another bouquet.
After day 10: [x, x, x, x, x, x, x], Now we can make two bouquets,
taking 3 adjacent flowers in one bouquet.
Example 2:

Input:
M = 3, K = 2
bloomDay = [1, 10, 3, 10, 2]
Output:
-1
Explanation:
As 3 bouquets each having 2 flowers are needed, that means we need 6 flowers.
But there are only 5 flowers so it is impossible to get the needed bouquets
therefore -1 will be returned.
*/
public class MinimumDaysMBouquets {

    public static int solve(int M, int K, int[] bloomDay) {
        int N = bloomDay.length;
        //Below edge case is taken to consider if total bouquets to be made
        //Consider Example 2 given above to understand
        long multiplication = (long) M * K;
        if (N < multiplication) {
            return -1;
        }
        /*
        below min and max here needs to be calculated using Arrays stream min and max methods like given below
        int minimumNoOfDaysToBloomAnyFlower = Arrays.stream(bloomDay).min().getAsInt();
        int maximumNoOfDaysToBloomAnyFlower = Arrays.stream(bloomDay).max().getAsInt();
        */
        int minimumNoOfDaysToBloomAnyFlower = Integer.MAX_VALUE;
        int maximumNoOfDaysToBloomAnyFlower = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            minimumNoOfDaysToBloomAnyFlower = Math.min(minimumNoOfDaysToBloomAnyFlower, bloomDay[i]);
            maximumNoOfDaysToBloomAnyFlower = Math.max(maximumNoOfDaysToBloomAnyFlower, bloomDay[i]);
        }
        //Our required answer of minimumDaysRequired lies in the range of minimum to maximum
        //so that range is sorted, and we can implement binary search
        int low = minimumNoOfDaysToBloomAnyFlower;
        int high = maximumNoOfDaysToBloomAnyFlower;
        int minimumDaysRequired = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossibleToBloomOnDay(bloomDay, mid, M, K)) {
                minimumDaysRequired = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        //Here we can also return low as when binary search ends both low and minimum points to same thing
        //and we can eliminate introducing 1 extra variable
        //Learn the concept of opposite polarity (for pointing) for binary search to get the intuition
        return minimumDaysRequired;
    }

    public static boolean isPossibleToBloomOnDay(int arr[], int day, int noOfBouquetsToMake, int noOfFlowersPerBouquet) {
        int count = 0;
        int noOfBouquetsPossibleOnDay = 0;
        for (int i = 0; i < arr.length; i++) {
            //If at given day, if that day can be considered for our bouquet formation
            //count is increased because we can only take consecutive days for flowers to be picked to make bouquets
            if (arr[i] <= day) {
                count++;
            }
            //Else that day for our consecutive count stops,
            // we need to calculate total bouquets possible till that day and reset count to 0
            else {
                noOfBouquetsPossibleOnDay += (count / noOfFlowersPerBouquet);
                count = 0;
            }
        }
        //At last we have to take for the last count possible to make bouquet as our loop ends
        noOfBouquetsPossibleOnDay += (count / noOfFlowersPerBouquet);
        return noOfBouquetsPossibleOnDay >= noOfBouquetsToMake;
    }

    public static void main(String[] args) {
        int bloomDay[] = {5, 5, 5, 5, 10, 5, 5};
        int M = 2;
        int K = 3;
        System.out.println(solve(M, K, bloomDay));
    }
}
