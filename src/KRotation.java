public class KRotation {

    public static int findKRotation(int arr[], int n) {
        // code here
        int low = 0, high = n - 1, minimum = Integer.MAX_VALUE, index = -1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(arr[low] <= arr[mid]) {
                //Left Half Sorted
                if(minimum > arr[low]) {
                    minimum = arr[low];
                    index = low;
                }
                low = mid + 1;
            } else {
                if(minimum > arr[mid]) {
                    minimum = arr[mid];
                    index = mid;
                }
                high = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int arr[] = {4 ,5 , 1 ,2 ,3};
        int n = arr.length;
        System.out.println(findKRotation(arr,n));
    }
}
