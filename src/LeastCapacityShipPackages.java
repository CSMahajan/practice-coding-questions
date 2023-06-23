/*
Capacity To Ship Packages Within D Days

Given an array arr[] of N weights. Find the least weight capacity of a boat to ship all weights within D days.
The ith weight has a weight of arr[i]. Each day, we load the boat with weights given by arr[i].
We may not load more weight than the maximum weight capacity of the ship.
Note: You have to load weights on the boat in the given order.

Example 1:
Input:
N = 3
arr[] = {1, 2, 1}
D = 2
Output:
3
Explanation:
We can ship the weights in 2 days
in the following way:-
Day 1- 1,2
Day 2- 1

Example 2:
Input:
N = 3
arr[] = {9, 8, 10}
D = 3
Output:
10
Explanation:
We can ship the weights in 3 days
in the following way:-
Day 1- 9
Day 2- 8
Day 3- 10
*/
public class LeastCapacityShipPackages {

    public static int leastWeightCapacity(int[] arr, int N, int D) {
        // code here
        //Here we would require at least the capacity of maximum element of array
        //because any weight can not be shipped partially in any given day
        //so maximumWeightToGetMinimumCapacityRequired will be the minimum days required for us to ship all weights
        //this will act as our lower range
        int maximumWeightToGetMinimumCapacityRequired = Integer.MIN_VALUE;
        //We will calculate sumOfWeights to find maximum capacity that we might require
        //taking sum as maximum will enable us to load/ship all the packages in one day only
        //as the sum of weights is capacity can all be loaded on same day into the ship
        //this will act as our upper range
        int sumOfWeights = 0;
        for (int weight : arr) {
            maximumWeightToGetMinimumCapacityRequired = Math.max(weight, maximumWeightToGetMinimumCapacityRequired);
            sumOfWeights += weight;
        }
        //We will consider minimum days required to load all packages initially as worst case
        int minimumShipCapacityRequiredToLoadAllPackages = sumOfWeights;
        //Our answer would lie within low and high
        //we can find the least capacity which fulfills our condition using binary search
        int low = maximumWeightToGetMinimumCapacityRequired, high = sumOfWeights;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int daysRequiredByTakingMidCapacity = getDaysRequiredToLoadAllWeights(arr, mid, N);
            if(daysRequiredByTakingMidCapacity <= D) {
                //This means by taking "mid" as capacity it is possible to all packages
                //But we will further try to reduce the capacity of ship
                minimumShipCapacityRequiredToLoadAllPackages = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minimumShipCapacityRequiredToLoadAllPackages;
    }

    private static int getDaysRequiredToLoadAllWeights(int[] weight, int capacity, int noOfWeights) {
        //We wil initialize noOfDaysRequired because we are starting from first day that day is also to be filled
        int noOfDaysRequired = 1;
        int totalWeightLoaded = 0;
        for (int i = 0; i < noOfWeights; i++) {
            //Below if condition for overflow condition to check if current weight is considered to be loaded in today,
            //will it surpasses our capacity or not
            //so we will load current weight into new day by increasing noOfDaysRequired by 1
            //and the weight loaded to be started as initial weight for that new day
            if (totalWeightLoaded + weight[i] > capacity) {
                totalWeightLoaded = weight[i];
                noOfDaysRequired++;
            } else {
                //otherwise we will keep on loading weights into current day only as we are still within our capacity
                totalWeightLoaded += weight[i];
            }
        }
        return noOfDaysRequired;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,1};
        int N= arr.length;
        int maximumDays = 2;
        System.out.println(leastWeightCapacity(arr,N,maximumDays));
    }
}
