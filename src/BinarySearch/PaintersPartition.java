package BinarySearch;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaintersPartition {

    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
        //    Write your code here.
        //This problem is identical/similar to book allocation problem
        return findPages(boards, boards.size(), k);
    }

    public static int findPages(ArrayList<Integer> A, int N, int M) {
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

    private static int getTotalNoOfStudentsReadWithMidAsMaximumPagesPerStudent(ArrayList<Integer> books, int pagesPerStudent) {
        int noOfStudentsReadTheBook = 1;
        int noOfPagesPerStudentRead = 0;
        for (int i = 0; i < books.size(); i++) {
            if (noOfPagesPerStudentRead + books.get(i) <= pagesPerStudent) {
                noOfPagesPerStudentRead += books.get(i);
            } else {
                noOfStudentsReadTheBook++;
                noOfPagesPerStudentRead = books.get(i);
            }
        }
        return noOfStudentsReadTheBook;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        ArrayList<Integer> boards = IntStream.of(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(PaintersPartition.findLargestMinDistance(boards, k));
    }
}
