package BitManipulation;

/*
Set the rightmost unset bit

Given a non-negative number N.
The problem is to set the rightmost unset bit in the binary representation of N.
If there are no unset bits, then just leave the number as it is.
Example 1:
Input:
N = 6
Output:
7
Explanation:
The binary representation of 6 is 110.
After setting right most bit it becomes 111 which is 7.
Example 2:
Input:
N = 15
Output:
15
Explanation:
The binary representation of 15 is 1111.
As there is no unset bit it remains the same.
*/

public class SetRightmostUnsetBit {

    public static int setBit(int N){
        //If (N&(N+1))==0 means next number is 2 to the power. Hence this number has only 1's.
        //If it is !=0  in that case we can do the 'OR' operation on this number and one successor number.
        return (N&(N+1))!=0?(N|(N+1)):N;
    }

    public static void main(String[] args) {
        System.out.println(setBit(6));
    }
}
