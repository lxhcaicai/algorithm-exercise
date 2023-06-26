package com.github.exercise.date20230625.day4;

import java.util.Scanner;

public class QuickSort {

    final static int N = (int) (1e5 + 100);
    static int n = 0;

    static int a[] = new int[N];

    static void qsort(int l, int r) {
        int mid = a[(l + r) >> 1];
        int i = l;
        int j = r;
        while(i < j) {
            while(a[i] < mid) i ++;
            while(a[j] > mid) j --;

            if(i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i ++;
                j --;
            }
        }
        if(i < r) qsort(i, r);
        if(l < j) qsort(l, j);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i = 1; i <= n; i ++) {
            a[i] = scanner.nextInt();
        }
        qsort(1, n);
        for(int i = 1; i <= n; i ++) {
            System.out.printf("%d ", a[i]);
        }
    }
}
