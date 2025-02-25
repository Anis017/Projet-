// Register.java

public class Register {
    /*@ requires n >= 0 && sizes != null && testCases != null && sizes.length == n 
      @    && testCases.length == n;
      @ ensures \result != null && \result.length == n;
      @ ensures (\forall int i; 0 <= i && i < n; 
      @         \result[i] == null || 
      @         (\exists int count; count > sizes[i]/2; 
      @          (\num_of int j; 0 <= j && j < sizes[i]; 
      @           testCases[i][j].equals(\result[i])) == count)));
      @*/
    public static String[] findMostCommonNames(int n, int[] sizes, String[][] testCases) {
        String[] results = new String[n];
        for (int i = 0; i < n; i++) {
            results[i] = findMajority(testCases[i], 0, sizes[i]);
        }
        return results;
    }

    /*@ requires arr != null && 0 <= start && start <= end && end <= arr.length;
      @ ensures \result == null || 
      @         (\exists int count; count > (end - start)/2; 
      @          (\num_of int i; start <= i && i < end; arr[i].equals(\result)) == count));
      @*/
    private static String findMajority(String[] arr, int start, int end) {
        if (start >= end) return null;
        if (end - start == 1) return arr[start];

        int mid = start + (end - start) / 2;
        String leftMajority = findMajority(arr, start, mid);
        String rightMajority = findMajority(arr, mid, end);

        if (leftMajority != null && leftMajority.equals(rightMajority)) return leftMajority;

        int leftCount = countOccurrences(arr, start, end, leftMajority);
        int rightCount = countOccurrences(arr, start, end, rightMajority);

        if (leftCount > (end - start) / 2) return leftMajority;
        if (rightCount > (end - start) / 2) return rightMajority;
        return null;
    }

    private static int countOccurrences(String[] arr, int start, int end, String candidate) {
        if (candidate == null) return 0;
        int count = 0;
        for (int i = start; i < end; i++) {
            if (arr[i].equals(candidate)) count++;
        }
        return count;
    }
}