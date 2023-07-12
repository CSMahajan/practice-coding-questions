package BinarySearch;

/*
Split Array Largest Sum

Given an integer array nums and an integer k,
split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
Return the minimized largest sum of the split.
A subarray is a contiguous part of the array.
Example 1:
Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:
Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
*/
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        //This problem is identical/similar to book allocation problem
        return findPages(nums, nums.length, k);
    }

    public static int findPages(int[] A, int N, int M) {
        //Your code here
        //Impossible case where number of students are more than total number of books available to read as
        //every student needs to read at least 1 book
        if (N < M) {
            return -1;
        }
        int sumOfAllElements = 0;
        int maximumOfElements = Integer.MIN_VALUE;
        int minimumNoOfPages = 0;
        for (int number : A) {
            sumOfAllElements += number;
            maximumOfElements = Math.max(maximumOfElements, number);
        }
        /*Minimum number of pages that can be read by any student will be max element
        because the max number of pages book should be read by one student exactly
        as 1 book can not be shared between several student to read
        Maximum number of pages that can be read by any student will be sum of all elements of an array
        because if number of students is 1 then that student has to read all book which basically sums up as a summation of all pages
        */
        int low = maximumOfElements, high = sumOfAllElements;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int numberOfStudentReadWithMidAsMaximumPagesPerStudent = getTotalNoOfStudentsReadWithMidAsMaximumPagesPerStudent(A, mid);
            if (numberOfStudentReadWithMidAsMaximumPagesPerStudent <= M) {
                minimumNoOfPages = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        /*
        Note: Opposite polarity in binary search always holds, if low starts from impossible case which is max possible,
        ends up at possible index and high starts from possible case, ends up at impossible index
        Vice versa case for low and high also exist for possible low to impossible low which occurs
        for binary search to find min possible and high at impossible to possible case
        */
        //We can also return low, as at the end of binary search low and minimum possible pages remains same
        return minimumNoOfPages;
    }

    private static int getTotalNoOfStudentsReadWithMidAsMaximumPagesPerStudent(int[] books, int pagesPerStudent) {
        int noOfStudentsReadTheBook = 1;
        int noOfPagesPerStudentRead = 0;
        for (int i = 0; i < books.length; i++) {
            if (noOfPagesPerStudentRead + books[i] <= pagesPerStudent) {
                noOfPagesPerStudentRead += books[i];
            } else {
                noOfStudentsReadTheBook++;
                noOfPagesPerStudentRead = books[i];
            }
        }
        return noOfStudentsReadTheBook;
    }

    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int k = 2;
        SplitArrayLargestSum sals = new SplitArrayLargestSum();
        System.out.println(sals.splitArray(nums,k));
    }
}
