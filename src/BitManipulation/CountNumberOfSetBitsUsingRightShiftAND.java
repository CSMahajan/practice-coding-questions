package BitManipulation;

/*
Count number of set bits

Given a number n, count the number of set bits.
A bit is said to be set if it has 1 at given k-th bit when number is represented in binary format
*/
public class CountNumberOfSetBitsUsingRightShiftAND {

    //Solution 1
    //TC:O(log2 (n))=>O(31) for integer values
    public int countSetBitsUsingRightShiftAndANDOperator(int n) {
        int count = 0;
        while (n > 1) {
            //n&1 boolean condition can be used to check odd number
            count += (n & 1);
            n = n >> 1;
        }
        if (n == 1) {
            count += 1;
        }
        return count;
    }

    //Solution 2
    //TC:O(number of set bits)=>O(31) for integer values
    public int countSetBitsUsingANDOperator(int n) {
        int count = 0;
        //below approach removes the last set bit till we get 0
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        CountNumberOfSetBitsUsingRightShiftAND cnsb = new CountNumberOfSetBitsUsingRightShiftAND();
        System.out.println(cnsb.countSetBitsUsingRightShiftAndANDOperator(29));
        System.out.println(cnsb.countSetBitsUsingANDOperator(31));
    }
}
