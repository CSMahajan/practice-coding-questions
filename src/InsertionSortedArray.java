/*
 * We have sorted array arr and number m.
 * Find the correct position for m where it can be inserted in the array so that it remains sorted.
 * Example:
 *           Input: arr = {1,2,4,7}
 *                  m = 6
 *           Output: 3
 * Explanation: At 3rd position if 6 is inserted then array becomes {1,2,4,6,7} and remains sorted.
 * */
public class InsertionSortedArray {

    public static int searchInsert(int[] arr, int m) {
        // Write your code here.
        int l=0;
        int n = arr.length;
        int h = n-1;
        int ans = n;
        while(l<=h){
            int mid=l+(h-l)/2;
            if(arr[mid]>=m){
                ans=mid;
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a[] = {3,6,12};
        int m = 7;
        System.out.println(searchInsert(a,m));
    }
}
