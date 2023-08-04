package GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/*
Fractional Knapsack

Given weights and values of N items,
we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item.
Example 1:
Input:
N = 3, W = 50
values[] = {60,100,120}
weight[] = {10,20,30}
Output:
240.00
Explanation:Total maximum value of item we can have is 240.00 from the given capacity of sack.
Example 2:
Input:
N = 2, W = 50
values[] = {60,100}
weight[] = {10,20}
Output:
160.00
Explanation:
Total maximum value of item we can have is 160.00 from the given capacity of sack.
*/
public class FractionalKnapsack {

    static class ItemComparator implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            double firstItemRatio = (double) o1.value / (double) o1.weight;
            double secondItemRatio = (double) o2.value / (double) o2.weight;
            if (firstItemRatio > secondItemRatio) {
                return -1;
            } else if (firstItemRatio < secondItemRatio) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }

    //Time Complexity: O(n log n + n). O(n log n) to sort the items and
    //O(n) to iterate through all the items for calculating the answer
    //Space Complexity: O(1), no additional data structure has been used.
    public double fractionalKnapsack(int W, Item[] arr, int n) {
        // Your code here
        //sort the items array according to (value/weight) in descending order
        Arrays.sort(arr, new ItemComparator());
        int currentlyPickedWeight = 0;
        double pickedItemsValue = 0;
        for (int i = 0; i < n; i++) {
            if (currentlyPickedWeight + arr[i].weight <= W) {
                //our current weight can all be picked
                currentlyPickedWeight += arr[i].weight;
                pickedItemsValue += arr[i].value;
            } else {
                //pick the remaining weight
                int remainingWeight = W - currentlyPickedWeight;
                pickedItemsValue += (double) arr[i].value / (double) arr[i].weight * remainingWeight;
                break;
            }
        }
        return pickedItemsValue;
    }

    public static void main(String[] args) {
        int N = 3, W = 50;
        int[] values = {60, 100, 120};
        int[] weight = {10, 20, 30};
        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(values[i], weight[i]);
        }
        FractionalKnapsack fk = new FractionalKnapsack();
        System.out.println(fk.fractionalKnapsack(W, items, N));
    }
}