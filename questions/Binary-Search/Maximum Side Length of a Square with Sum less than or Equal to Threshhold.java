//Approach-1 (Using 2D Prefix Sum and finding best side square)
//T.C : O(rows * cols * min(rows, cols))
//S.C : O(rows * cols)
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[][] prefix = new int[rows][cols];

        // Build prefix sum
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefix[i][j] = mat[i][j]
                        + (i > 0 ? prefix[i - 1][j] : 0)
                        + (j > 0 ? prefix[i][j - 1] : 0)
                        - (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }

        int best = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = best; k < Math.min(rows - i, cols - j); k++) {
                    //bottom right cell (r2, c2)
                    int r2 = i + k;
                    int c2 = j + k;

                    int sum = prefix[r2][c2];
                    if (i > 0) sum -= prefix[i - 1][c2];
                    if (j > 0) sum -= prefix[r2][j - 1];
                    if (i > 0 && j > 0) sum += prefix[i - 1][j - 1];

                    if (sum <= threshold) {
                        best = k + 1;
                    } else {
                        break;
                    }
                }
            }
        }

        return best;
    }
}


//Approach-2 (Using 2D Prefix Sum and binarysearch the square side)
//T.C : O(rows * cols * log(min(rows, cols)))
//S.C : O(rows * cols)
class Solution {
    private boolean check(int side, int rows, int cols,
                           int threshold, int[][] prefix) {
        if (side == 0)
            return true;

        for (int i = 0; i + side - 1 < rows; i++) {
            for (int j = 0; j + side - 1 < cols; j++) {

                int r2 = i + side - 1;
                int c2 = j + side - 1;

                int sum = prefix[r2][c2];
                if (i > 0) sum -= prefix[i - 1][c2];
                if (j > 0) sum -= prefix[r2][j - 1];
                if (i > 0 && j > 0) sum += prefix[i - 1][j - 1];

                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Prefix sum without padding
        int[][] prefix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefix[i][j] =
                        mat[i][j]
                        + (i > 0 ? prefix[i - 1][j] : 0)
                        + (j > 0 ? prefix[i][j - 1] : 0)
                        - (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }

        int lo = 1, hi = Math.min(rows, cols);
        int result = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, rows, cols, threshold, prefix)) {
                result = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return result;
    }
}
