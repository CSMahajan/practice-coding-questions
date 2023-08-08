package Trie;

/*
Bitwise Basic Operations

You are given six positive integers, ‘a’, 'b’ ’c', 'd', 'e’, 'f' and
You will be given three functions ‘getXOR' ‘getBit, and 'setBit’
Your task is to do the following:
In function ‘getXOR', you will be given two parameters, ‘a’, and ‘b'. Your tasks to return the XOR of ‘a’ and 'b'.
In the function ‘getBit', you will be given two parameters, ‘c’ and ‘d'.
Your task is to return 1 if ‘Cth bit of ‘d" is set. Otherwise, return 0.
In the function 'setBit’, you will be given two parameters,’e' and 'f'.
Your task is to set the ‘e'-th bit in 'f' if it is not set. At last, return integer 'f'.
The expected time complexity is "0(1)’ and the expected space complexity is '0(1)"
Note: In the output, you will see the output of the three functions in the order of ‘getXOR’, ‘getBit’, and ‘setBit’.
Example:
Input: ‘a’ = 1, ‘b’ = 2, ‘c’ = 1, ‘d’ = 3, ‘e’ = 2, ‘f’ = 3
Output: 3 1 7
Explanation: On XORing the integers ‘a’ and ‘b’, we get ‘3’.
On checking the ‘c’th bit in ‘d’, since it is present, we returned 1.
On making the ‘e’th bit in ‘f’ set, the integer becomes 7.
*/
public class Trie5 {

    public static int getXOR(int a, int b) {
        // Write Your Code Here
        return a^b;
    }
    public static int getBit(int c, int d) {
        // Write Your Code Here
        //Our task is to check if 'c'-th bit of 'd' is set. if yes, then return 1, otherwise return 0.
        //To check if i-th bit is set means that,
        //i-th bit should have 1 when counting from right to left in 32-bit binary representation system
        //another way is ((d>>c) & 1) != 0 ? 1: 0;
        //generic way [if(((num>>i) & 1) != 0)] then it is set otherwise not set
        return ((1<<c) & d) != 0 ? 1: 0;
    }
    public static int setBit(int e, int f) {
        // Write Your Code Here
        //Our task is to set the 'e'-th bit in 'f' if it is not set. At last, return integer 'f'.
        //To set i-th bit means that,
        //i-th bit should be made 1 when counting from right to left in 32-bit binary representation system
        f = ((1<<e) | f);
        return f;
    }

    public static void main(String[] args) {
        System.out.println(getXOR(3,5));
        System.out.println(getBit(3,5));
        System.out.println(setBit(3,5));
    }
}