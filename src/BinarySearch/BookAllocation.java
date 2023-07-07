package BinarySearch;

/*
Allocate minimum number of pages

You have N books, each with Ai number of pages. M students need to be allocated contiguous books,
with each student getting at least one book. Out of all the permutations, the goal is to find the permutation
where the student with the most pages allocated to him gets the minimum number of pages, out of all possible permutations.
Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order
(see the explanation for better understanding).
Example 1:
Input:
N = 4
A[] = {12,34,67,90}
M = 2
Output:113
Explanation:Allocation can be done in
following ways:
{12} and {34, 67, 90} Maximum Pages = 191
{12, 34} and {67, 90} Maximum Pages = 157
{12, 34, 67} and {90} Maximum Pages =113.
Therefore, the minimum of these cases is 113,which is selected as the output.
Example 2:
Input:
N = 3
A[] = {15,17,20}
M = 2
Output:32
Explanation: Allocation is done as {15,17} and {20}
*/
public class BookAllocation {

    /*TC:O(N * log(sumOfAllElements-maxElement))
      N *  for called function for possibility of pages read by student for range [maxElement, sumOfAllElements] and
      log(sumOfAllElements-maxElement) for binary search for finding
      maximum pages read by any student which is minimum possible
      SC:O(1)
      We are not using any extra space
    */
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
        int[] books = {12, 34, 67, 90};
        int n = books.length;
        int totalStudents = 2;
        System.out.println(findPages(books, n, totalStudents));
    }
}
