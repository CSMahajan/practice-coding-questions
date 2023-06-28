/*
Median of 2 Sorted Arrays of Different Sizes

Given two sorted arrays array1 and array2 of size m and n respectively. Find the median of the two sorted arrays.
Example 1:
Input:
m = 3, n = 4
array1[] = {1,5,9}
array2[] = {2,3,6,7}
Output: 5
Explanation: The middle element for
{1,2,3,5,6,7,9} is 5
Example 2:
Input:
m = 2, n = 4
array1[] = {4,6}
array2[] = {1,2,3,5}
Output: 3.5
*/
public class MedianTwoSortedArrays {

    //GeeksForGeeks
    //TC:O(log(min(m,n)))
    //SC:O(1)
    public static double medianOfArrays(int n, int m, int a[], int b[]) {
        // Your Code Here
        //if (m > n) return medianOfArrays(m, n, b, a);
        int n1 = a.length;
        int n2 = b.length;
        //We are making sure that we will perform binary search only on the smaller length array
        //so calling with parameters reversed if not given as so
        if(n1 > n2) return medianOfArrays(n1, n2, b, a);
        //Here we want to make a partition from both arrays but perform binary search on first array only
        //and deduce the subsequent values of second array using first array
        //low is 0 because at min no elements can be picked while merging these two sorted arrays
        //this would happen when even the smallest element of first array is larger than largest element of the second array
        //high is length of first array because at max we can pick all elements of first array
        //this would happen when even the smallest element of second array is larger than largest element of the first array
        int low = 0;
        int high = n1;
        while (low <= high) {
            //cut1 and cut2 represents the partition of both array after merging to decide
            //how many elements of each array would come to left side and right side of partition
            int cut1 = (low + high) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;
            //below 4 conditional/ternary operator is used for handling edge case of
            //either 0 or all elements of first and second arrays are considered while merging
            //left1 and left2 denotes the largest element on left side of partition from first and second array respectively
            //right1 and right2 denotes the largest element on right side of partition from first and second array respectively
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
            int right1 = cut1 == n1 ? Integer.MAX_VALUE : a[cut1];
            int right2 = cut2 == n2 ? Integer.MAX_VALUE : b[cut2];
            //below condition checks validity of possible merging
            //we can have all other checks as well but these two checks suffice our requirements for validity
            if (left1 <= right2 && left2 <= right1) {
                //if total elements are even then we need to have the average
                //we are taking max of left1 and left2 because max between these two would be closest to the partition and
                //similary we are taking min of right1 and right2 because min between these two would be closest to the partition
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    //if total elements are odd then we only take max of left1 and left2 because only middle element is
                    //the median of merged sorted array made up from given two arrays, so no need to consider right1 and right2
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                //this case happens when max element chosen from left side from first array in partition is bigger than
                //the smallest element from right side from second array in partition
                //so we need to reduce elements from first array (on the left side)
                //and add/consider more elements from (on the right side towards left side) the second array
                high = cut1 - 1;
            } else {
                //this case happens when max element chosen from left side from second array in partition is bigger than
                //the smallest element from right side from first array in partition
                //so we need to reduce elements from second array (on the left side)
                //and add/consider more elements from (on the right side towards left side) the first array
                low = cut1 + 1;
            }
        }
        //mandatory return statement for program execution to avoid ambiguity
        //created due to many conditional statements inside while loop
        return 0.0;
    }

    //LeetCode
    //TC:O(log(min(n1,n2)))
    //SC:O(1)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        //We are making sure that we will perform binary search only on the smaller length array
        //so calling with parameters reversed if not given as so
        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
        //Here we want to make a partition from both arrays but perform binary search on first array only
        //and deduce the subsequent values of second array using first array
        //low is 0 because at min no elements can be picked while merging these two sorted arrays
        //this would happen when even the smallest element of first array is larger than largest element of the second array
        //high is length of first array because at max we can pick all elements of first array
        //this would happen when even the smallest element of second array is larger than largest element of the first array
        int low = 0;
        int high = n1;
        while (low <= high) {
            //cut1 and cut2 represents the partition of both array after merging to decide
            //how many elements of each array would come to left side and right side of partition
            int cut1 = (low + high) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;
            //below 4 conditional/ternary operator is used for handling edge case of
            //either 0 or all elements of first and second arrays are considered while merging
            //left1 and left2 denotes the largest element on left side of partition from first and second array respectively
            //right1 and right2 denotes the largest element on right side of partition from first and second array respectively
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];
            //below condition checks validity of possible merging
            //we can have all other checks as well but these two checks suffice our requirements for validity
            if (left1 <= right2 && left2 <= right1) {
                //if total elements are even then we need to have the average
                //we are taking max of left1 and left2 because max between these two would be closest to the partition and
                //similary we are taking min of right1 and right2 because min between these two would be closest to the partition
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    //if total elements are odd then we only take max of left1 and left2 because only middle element is
                    //the median of merged sorted array made up from given two arrays, so no need to consider right1 and right2
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                //this case happens when max element chosen from left side from first array in partition is bigger than
                //the smallest element from right side from second array in partition
                //so we need to reduce elements from first array (on the left side)
                //and add/consider more elements from (on the right side towards left side) the second array
                high = cut1 - 1;
            } else {
                //this case happens when max element chosen from left side from second array in partition is bigger than
                //the smallest element from right side from first array in partition
                //so we need to reduce elements from second array (on the left side)
                //and add/consider more elements from (on the right side towards left side) the first array
                low = cut1 + 1;
            }
        }
        //mandatory return statement for program execution to avoid ambiguity
        //created due to many conditional statements inside while loop
        return 0.0;
    }

    public static void main(String[] args) {
        int m = 3, n = 4;
        int array1[] = {1,5,9};
        int array2[] = {2,3,6,7};
        System.out.println(medianOfArrays(n, m, array1,array2));
        System.out.println(findMedianSortedArrays(array1,array2));
    }
}
