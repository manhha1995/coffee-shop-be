package com.dap.coffee;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static int totalSubArray(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[] mergeTwoArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }

        return c;
    }

    public static int binarySearch(int[] arr, int n) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < n) {
                start = mid + 1;
            } else if (arr[mid] > n) {
                end = mid - 1;
            } else
                return mid;
        }
        return -1;
    }

    public static int totalSubArrayHavenSumEqualsGivenNumber(int[] arr, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int j : arr) {
            sum += j;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;

    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        System.out.println(totalSubArray(arr));
        System.out.println(totalSubArrayHavenSumEqualsGivenNumber(arr, 7));
    }
}
