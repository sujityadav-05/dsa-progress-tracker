//approach:Brute force
//Time Complexity: O(∑nums[i]) (worst case O(n·M))
//Space Complexity: O(n)

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n=nums.size();
        int ans[]=new int[n];
        
        for(int i=0;i<n;i++){
            int candidate=-1;
            for(int j=1;j<nums.get(i);j++){
                if((j | (j+1)) == nums.get(i)){
                    candidate=j;
                    break;
                }
            }
            ans[i]=candidate;
        }
        return ans;
    }
}
