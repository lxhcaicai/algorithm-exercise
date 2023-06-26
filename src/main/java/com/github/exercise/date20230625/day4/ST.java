package com.github.exercise.date20230625.day4;

import java.util.Scanner;

public class ST {

    static final int N = (int) (1e5 + 100);
    static int f[][] = new int[N][30];
    static int n, m;


    static void pre() {
        int t = (int) (Math.log(n) / Math.log(2) + 1);
        for(int j = 1;  j < t; j ++) {
            for(int i = 1; i <= n - (1 << j) + 1;i ++) {
                f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    static int query(int l, int r) {
        int k = (int) (Math.log(r - l + 1) / Math.log(2));
        return Math.max(f[l][k], f[r - (1 << k) + 1][k]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for(int i = 1; i <= n; i ++) {
            f[i][0] = scanner.nextInt();
        }
        pre();
        while(m -- > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            System.out.println(query(l, r));
        }
    }
}
