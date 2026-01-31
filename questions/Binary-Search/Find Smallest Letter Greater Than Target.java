//approach binary search
//time complexity=o(logn)
//space complexity=O(1)

class Solution {
    //linear search
    // public char nextGreatestLetter(char[] letters, char target) {
    //     int n=letters.length;
    //     for(int i=0;i<n;i++){
    //         if(letters[i]>target){
    //             return letters[i];
    //         }
    //     }
    //     return letters[0];
    // }

    //binary search
    public char nextGreatestLetter(char[] letters,char target){
        int low=0;
        int high=letters.length-1;
        char ans=letters[0];
        while(low<=high){
            int mid=low+(high-low)/2;
            if (letters[mid] > target) {
                ans = letters[mid];   // possible answer
                high = mid - 1;      // search left
            } else {
                low = mid + 1;       // search right
            }
        }
        return ans;
    }
}
