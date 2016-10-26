package leetcode;

public class Guess_Number_Higher_or_Lower {
    /* The guess API is defined in the parent class GuessGame.
    @param num, your guess
    @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
       int guess(int num); */

 public class Solution extends GuessGame {
     public int guessNumber(int n) {
         if(guess(n) == 0) return n;
         int fs = 1;
         int ed = n;
         while(fs < ed) {
             int mid = fs + ((ed-fs)>>>1);
             if(guess(mid) == 0){
                 return mid;
             }else if(guess(mid) == 1){
                 fs = mid;
             }else if(guess(mid) == -1){
                 ed = mid;
             }
         }
         return 0;
     }

    private int guess(int n) {
        return 0;
    }
 }
}
