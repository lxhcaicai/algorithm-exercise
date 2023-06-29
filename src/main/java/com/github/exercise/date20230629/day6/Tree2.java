package com.github.exercise.date20230629.day6;

import java.util.Scanner;

public class Tree2 {

    static final int N = (int) (1e5 + 100);

    static int a[] = new int[N];
    static int c[] = new int[N];

    static int n,m;

    static void add(int x, int val) {
        for(; x <= n; x += x&-x) {
            c[x] += val;
        }
    }

    static int query(int x) {
        int res = 0;
        for(; x > 0; x -= x&-x) {
            res += c[x];
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for(int i = 1; i <= n; i ++) {
            a[i] = scanner.nextInt();
            add(i, a[i] - a[i - 1]);
        }

        for(; m >0; m --) {
            int op =scanner.nextInt();
            int x = scanner.nextInt();

            if(op == 1) {
                int y = scanner.nextInt();
                int k = scanner.nextInt();
                add(x, k);
                add(y + 1, -k);
            } else {
                System.out.println(query(x));;
            }
        }
    }
}
