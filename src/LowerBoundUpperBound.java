public class LowerBoundUpperBound {

    //Lower Bound is smallest index in sorted array such that arr[i] >= x
    public static int lowerBound(int []arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    //The upper bound is the smallest index, ind, where arr[i] > x
    public static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 9, 9, 15, 19};
        int n = arr.length, x = 9;
        int ind = upperBound(arr, x, n);
        System.out.println("The upper bound is the index: " + ind);
    }
}
