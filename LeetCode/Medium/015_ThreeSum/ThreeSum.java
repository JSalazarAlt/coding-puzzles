import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeSum {
    
    /**
     * Solves the "3Sum" problem
     * (https://leetcode.com/problems/3sum/).
     *
     * Given an integer array nums, returns all unique triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Approach (Sort Array - Two Pointers):
     * 1. Sort the array to enable two-pointer technique and handle duplicates.
     * 2. For each element nums[i], use two pointers (left and right) to find pairs
     *    that sum to -nums[i].
     * 3. Skip duplicate values to avoid duplicate triplets in the result.
     * 4. When sum equals 0, add triplet and move both pointers while skipping duplicates.
     * 5. When sum < 0, move left pointer right to increase sum.
     * 6. When sum > 0, move right pointer left to decrease sum.
     *
     * Time Complexity: O(n²)
     * - O(n log n) for sorting + O(n²) for nested iteration with two pointers.
     *
     * Space Complexity: O(1)
     * - Only using constant extra space (not counting the output list).
     *
     * @param nums The input array of integers.
     * @return List of all unique triplets that sum to zero.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        
        // Length of the array
        int n = nums.length;

        // Declare and initialize the list of triplets to return
        List<List<Integer>> triplets = new ArrayList<>();

        // Sort the array of numbers
        Arrays.sort(nums);

        // Iterate over the array to establish a target reference nums[i]
        for (int i = 0; i < n; i++) {
            
            // Skip duplicate elements for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Initialize the left and right pointers to apply the two pointers technique
            int left = i + 1;
            int right = n - 1;

            // Iterate over the array using left and right pointers
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // If the sum satisfies the condition, add the triplet and skip duplicates
                if (sum == 0) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // Skip duplicates for left pointer
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicates for right pointer
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        // Return all the unique triplets that add up to zero
        return triplets;

    }

    public static void main(String[] args) {

        try {
            
            // Declare the Scanner object to read the file
            File file = new File("test_cases.txt");
            Scanner scanner = new Scanner(file);

            // Read the number of test cases
            int t = scanner.nextInt();

            for (int test = 1; test <= t; test++) {
                
                // Read the number of elements of the list
                int n = scanner.nextInt();

                // Read the elements of the list
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    nums[i] = scanner.nextInt();
                }

                // Print the number of test case
                System.out.print("Test Case #" + test + ": ");

                // Print the input array
                System.out.print("nums = [");
                for (int i = 0; i < n - 1; i++) {
                    System.out.print(nums[i] + ", ");
                }
                System.out.println(nums[n - 1] + "]");

                // Find all the unique triplets that add up to zero
                List<List<Integer>> triplets = threeSum(nums);
                
                // Print all the unique triplets
                System.out.println("Triplets that sum to zero: triplets = " + triplets);
                System.out.println();
                
            }
            
            // Close the Scanner object
            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found: Open 015_ThreeSum as source directory.");

        }

    }

}
