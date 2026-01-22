//approach:Simulation
//Time Complexity = O(n²)
//space complexity=O(1)

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int count = 0;

        while (n > 1) {
            boolean isAscending = true;
            int minSum = Integer.MAX_VALUE;
            int idx = -1;

            for (int i = 0; i < n - 1; i++) {
                if (nums[i] > nums[i + 1]) isAscending = false;

                int sum = nums[i] + nums[i + 1];
                if (sum < minSum) {
                    minSum = sum;
                    idx = i;
                }
            }

            if (isAscending) break;

            // merge pair at idx
            nums[idx] = minSum;
            for (int i = idx + 1; i < n - 1; i++) {
                nums[i] = nums[i + 1];
            }
            n--; // array size decreases
            count++;
        }

        return count;
    }
}
