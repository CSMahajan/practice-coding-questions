package BinarySearch;

/*
K-th element of two sorted Arrays

Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K.
The task is to find the element that would be at the kth position of the final sorted array.
Example 1:
Input:
arr1[] = {2, 3, 6, 7, 9}
arr2[] = {1, 4, 8, 10}
k = 5
Output:6
Explanation:
The final sorted array would be - 1, 2, 3, 4, 6, 7, 8, 9, 10
The 5th element of this array is 6.
Example 2:
Input:
arr1[] = {100, 112, 256, 349, 770}
arr2[] = {72, 86, 113, 119, 265, 445, 892}
k = 7
Output:256
Explanation:
Final sorted array is - 72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892
7th element of this array is 256.
*/
public class KthElementTwoSortedArrays {

    //TC:log(min(n,m)), where n & m are sizes of first and second arrays respectively
    //SC:O(1)
    public static long kthElement(int[] arr1, int[] arr2, int n, int m, int k) {
        if (n > m) {
            return kthElement(arr2, arr1, m, n, k);
        }
            /*
            n is length of first array which is smaller and m is length is second array which is larger
            We are doing partition on the k-th index
            Here low is maximum of 0 & (k - m) because if we have a case where we don't have sufficient elements in second array
            where m < k and if we pick low = 0 that means we need to pick all elements from second array and even if we pick all
            elements from second array, we will be short of few elements if m < k, so we need at least (k - m) elements from first array
            because at maximum we can have all the elements from first array, so at least to compensate remaining (k-m) are required.

            High is minimum of n and k as we want to make partition on k-th index, partition is done on k-th index is done because it will simplify us
            in finding k-th element which will be largest among left1 and left2 in binary search
            */
        int low = Math.max(0, k - m), high = Math.min(n, k);
        while (low <= high) {
            //cut1 is mid in binary search where the partition is done on k-th index
            int cut1 = (low + high) / 2;
            //cut2 means index for number of elements picked from second array which can be easily calculated as remaining elements
            //from difference among k and cut1
            int cut2 = k - cut1;
            /*
            left1 and left2 denotes nearest element to partition on k-th index on left side of first and second array respectively.
            right1 and right2 denotes nearest element to partition on k-th index on right side of first and second array respectively.
            Partition is done on cut1 and cut2 indexes so left side elements indexes will be cut1-1 and cut2-1 of first and second array respectively
            and right side elements indexes will be cut1 and cut2 of first and second array respectively
            */
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int right1 = cut1 == n ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = cut2 == m ? Integer.MAX_VALUE : arr2[cut2];
            if (left1 <= right2 && left2 <= right1) {
                //As we are having our cut/partition on k-th index, the element which is more near to partition
                //after merging is larger among left1 and left2 and is going to be K-th element
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                //extra elements on left side of first array are picked so need to decrease elements
                //on left side of first array
                high = cut1 - 1;
            } else {
                //less elements on left side of first array are picked so need to increase elements
                //on left side of first array
                low = cut1 + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array1 = {2, 3, 6, 7, 9};
        int[] array2 = {1, 4, 8, 10};
        int n = array1.length;
        int m = array2.length;
        int k = 5;
        System.out.println(kthElement(array1, array2, n, m, k));
    }
}